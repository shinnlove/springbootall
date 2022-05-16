/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.pdf.util;

import java.util.*;

import com.shinnlove.springbootall.pdf.consts.MergeConsts;
import com.shinnlove.springbootall.pdf.model.FilePartition;

/**
 * @author Tony Zhao
 * @version $Id: ParseUtil.java, v 0.1 2022-05-15 8:44 PM Tony Zhao Exp $$
 */
public class ParseUtil {

    public static void parseFileName(String fileName,
                                     Map<Integer, Map<Integer, List<FilePartition>>> buckets) {

        // indexOf是第一个index of
        int pos = fileName.indexOf(MergeConsts.UNDERLINE);
        String sequence = fileName.substring(0, pos);
        int sLen = sequence.length();

        int first = sequence.indexOf(MergeConsts.PERIOD);
        int last = sequence.lastIndexOf(MergeConsts.PERIOD);

        int bigChapter = MergeConsts.DEFAULT_CHAPTER;
        int secondChapter = MergeConsts.DEFAULT_CHAPTER;
        int thirdChapter = MergeConsts.DEFAULT_CHAPTER;

        if (first < 0 || last < 0) {
            // 1_

            bigChapter = safeParse(sequence);
        }

        if (first < last) {
            // 1.2.1

            String num1 = sequence.substring(0, first);
            String num2 = sequence.substring(first + 1, last);
            String num3 = sequence.substring(last + 1, sLen);

            bigChapter = safeParse(num1);
            secondChapter = safeParse(num2);
            thirdChapter = safeParse(num3);

        } else if (first == last) {
            // 1.2

            String num1 = sequence.substring(0, first);
            String num2 = sequence.substring(first + 1, sLen);

            bigChapter = safeParse(num1);
            secondChapter = safeParse(num2);
        }

        if (bigChapter < 0) {
            return;
        }

        FilePartition fp = new FilePartition(fileName);

        // init first
        if (!buckets.containsKey(bigChapter)) {
            Map<Integer, List<FilePartition>> bucket = new HashMap<>();
            buckets.put(bigChapter, bucket);
        }

        Map<Integer, List<FilePartition>> bucket = buckets.get(bigChapter);

        // init second
        if (!bucket.containsKey(secondChapter)) {
            List<FilePartition> partitions = new ArrayList<>();
            bucket.put(secondChapter, partitions);
        }

        // 1_x或1_x_x
        List<FilePartition> partitions = bucket.get(secondChapter);
        fp.setLastChapter(thirdChapter);
        partitions.add(fp);

        Collections.sort(partitions);

        return;
    }

    public static int safeParse(String sequence) {
        int chapter = MergeConsts.DEFAULT_CHAPTER;
        try {
            chapter = Integer.parseInt(sequence);
        } catch (Exception e) {

        }
        return chapter;
    }

}