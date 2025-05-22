/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.concurrency.impl;

import com.shinnlove.springbootall.service.concurrency.ConcurrencyService;
import com.shinnlove.springbootall.util.threads.ThreadUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Tony Zhao
 * @version $Id: ConcurrencyServiceImpl.java, v 0.1 2025-05-07 14:38 Tony Zhao Exp $$
 */
@Service
public class ConcurrencyServiceImpl implements ConcurrencyService {

    private static final Logger logger = LoggerFactory.getLogger(ConcurrencyServiceImpl.class);

    private static final Executor pool = ThreadUtils
            .createPool("my-diy-thread-pool", 30, 30, 100);

    private static void async(Runnable r) {
        CompletableFuture.runAsync(r, pool);
    }

    public static <T> List<T> genericConcurrentQuery(int calculateTimes, final Function<Integer, List<T>> requestForOnePage) {
        // concurrent fetch all pages data
        final CountDownLatch countDownLatch = new CountDownLatch(calculateTimes);
        final Map<Integer, List<T>> concurrentResultMap = new ConcurrentHashMap<>();
        for (int i = 1; i <= calculateTimes; i++) {
            final int page = i;
            async(() -> {
                try {
                    List<T> onePageResult = requestForOnePage.apply(page);
                    if (CollectionUtils.isNotEmpty(onePageResult)) {
                        concurrentResultMap.put(page, onePageResult);
                    }
                } catch (Exception e) {
                    logger.error("Error occurred while fetching page {}: {}", page, e.getMessage(), e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        // Wait for all threads to complete
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            logger.error("Thread interrupted while waiting for concurrent tasks to complete", e);
            Thread.currentThread().interrupt();
            return Collections.emptyList();
        }

        // Process the results from concurrentResultMap
        List<T> allResults = new ArrayList<>();
        for (int i = 1; i <= calculateTimes; i++) {
            List<T> pageResults = concurrentResultMap.get(i);
            if (CollectionUtils.isNotEmpty(pageResults)) {
                allResults.addAll(pageResults);
            }
        }

        return allResults;
    }

    public static <T> List<T> genericConcurrentQueryPro(int calculateTimes, final Function<Integer, List<T>> requestForOnePage) {

        // Submit all tasks and collect Future objects
        List<CompletableFuture<List<T>>> futures = IntStream.rangeClosed(1, calculateTimes)
                .mapToObj(page -> CompletableFuture.supplyAsync(() -> requestForOnePage.apply(page), pool).exceptionally(e -> {
                    logger.error("Error fetching page {}: {}", page, e.getMessage(), e);
                    return Collections.emptyList();
                }))
                .collect(Collectors.toList());

        // Combine all results
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .filter(CollectionUtils::isNotEmpty)
                        .flatMap(List::stream)
                        .collect(Collectors.toList()))
                .join();
    }

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(3);

        // 创建多个 CompletableFuture
        CompletableFuture<List<String>> future1 = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            List<String> result = new ArrayList<>();
            result.add("result-1");
            result.add("finished");
            return result;
        }, pool).exceptionally(e -> {
            System.out.println("Exception occurred in Future 1: " + e.getMessage());
            return Collections.emptyList();
        });

        CompletableFuture<List<String>> future2 = CompletableFuture.supplyAsync(() -> {
            sleep(2000);
            List<String> result = new ArrayList<>();
            result.add("result-2");
            result.add("finished");
            return result;
        }, pool).exceptionally(e -> {
            System.out.println("Exception occurred in Future 2: " + e.getMessage());
            return Collections.emptyList();
        });

        CompletableFuture<List<String>> future3 = CompletableFuture.supplyAsync(() -> {
            sleep(1500);
            List<String> result = new ArrayList<>();
            result.add("result-3");
            result.add("finished");
            return result;
        }, pool).exceptionally(e -> {
            System.out.println("Exception occurred in Future 3: " + e.getMessage());
            return Collections.emptyList();
        });

        List<CompletableFuture<List<String>>> futures = new ArrayList<>();
        futures.add(future1);
        futures.add(future2);
        futures.add(future3);

        // Combine all results
        List<String> collectResult = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .filter(CollectionUtils::isNotEmpty)
                        .flatMap(List::stream)
                        .collect(Collectors.toList()))
                .join();

//        // 使用 allOf 等待所有 Future 完成
//        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2, future3);
//
//        // 阻塞等待所有任务完成
//        allFutures.join();  // 或 allFutures.get()
//
//        // 获取各个 Future 的结果
//        try {
//            List<String> result1 = future1.get();  // "Result 1"
//            List<String> result2 = future2.get();  // "Result 2"
//            List<String> result3 = future3.get();  // "Result 3"
//            System.out.println(result1 + ", " + result2 + ", " + result3);
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }

        System.out.println(collectResult);

        pool.shutdown();
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}