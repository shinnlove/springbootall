/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.third.party;

import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Tony Zhao
 * @version $Id: ExcelExport.java, v 0.1 2025-04-02 11:11 Tony Zhao Exp $$
 */
public class ExcelExport {

    private static final Logger logger = LoggerFactory.getLogger(ExcelExport.class);

    public static void main(String[] args) {
        ExcelExport excelExport = new ExcelExport();
        excelExport.exportCsvFiles("test.csv", "tony");
    }

    public void exportCsvFiles(String fileName, String userAccount) {

        List<List<String>> exportedList = generateData();

        // create empty local file
        File csvFile = createLocalFile(fileName, userAccount);
        if (Objects.isNull(csvFile)) {
            logger.warn("导出实体月票订单查询 csv 文件创建失败");
            return;
        }

        // write header and data into file
        writeCSV(csvFile, exportedList);

        // third compress the Excel file
        File zipFile = compressedFile(csvFile);
    }

    public List<List<String>> generateData() {
        List<List<String>> data = Arrays.asList(
                Arrays.asList("Header1", "Header2", "Header3"),
                Arrays.asList("Normal", "Data", "Without comma"),
                Arrays.asList("With,comma", "25", "New,York"),
                Arrays.asList("With\"quote", "30", "Boston")
        );

        return data;
    }

    private File createLocalFile(String fileName, String userName) {

        File csvFile = null;
        try {
            // 服务器上的地址：
            // /dockerdata/csv/操作人/文件名.csv
            String filePath = String.format("csv%s%s%s%s", File.separator, userName, File.separator, fileName);
            csvFile = new File(filePath);
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                logger.error("createLocalFile 创建文件夹失败");
            }
            if (!csvFile.createNewFile()) {
                logger.error("createLocalFile 创建文件失败");
            }
            logger.info("csv absolutePath: {}，path: {}", csvFile.getAbsolutePath(), csvFile.getPath());
        } catch (Exception ex) {
            logger.error("createLocalFile 创建文件异常, ex=" + ex.getMessage(), ex);
        }

        return csvFile;
    }

    private void writeCSV(File csvFile, List<List<String>> contentList) {

        logger.info("写入文件导出Excel 开始写入文件，absolutePath：{}，contentListSize：{}",
                csvFile.getAbsolutePath(), contentList.size());

        // 使用 try-with-resources 自动关闭资源
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(csvFile, true), StandardCharsets.UTF_8);
             CSVWriter csvWriter = new CSVWriter(writer)) {

            for (List<String> content : contentList) {
                // 将 List<String> 转换为 String[]，OpenCSV 会自动处理包含逗号的字段
                String[] lineArray = content.toArray(new String[0]);
                csvWriter.writeNext(lineArray);
            }

            // OpenCSV 会自动刷新缓冲区
            // csvWriter.flush();
        } catch (Exception e) {
            logger.error("写入文件导出Excel CSV 文件写入异常", e);
        }

        // 不需要 finally 块关闭资源，try-with-resources 会自动处理
    }

    private File compressedFile(File srcFile) {

        logger.error("写入文件导出Excel 开始压缩文件，absolutePath：{}", srcFile.getAbsolutePath());

        try {
            File zipFile = new File(srcFile.getPath() + ".zip");
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));
            // 这里的 / 很重要，ZipEntry 的 name 必须以 / 开头，如果以其他的方式开头会导致解压失败
            zos.putNextEntry(new ZipEntry("/" + srcFile.getName()));

            int count;
            int bufferLen = 1024;
            byte[] data = new byte[bufferLen];

            InputStream input = new FileInputStream(srcFile);
            while ((count = input.read(data, 0, bufferLen)) != -1) {
                zos.write(data, 0, count);
            }
            input.close();
            zos.close();

            return zipFile;
        } catch (IOException e) {
            logger.error("写入文件导出Excel 压缩文件异常", e);
            return null;
        }
    }

}