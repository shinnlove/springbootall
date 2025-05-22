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
        excelExport.exportCsvFiles("testa.csv", "tony");
    }

    public void exportCsvFiles(String fileName, String userAccount) {
        List<List<String>> exportedList = generateData();

        // create empty local file
        File csvFile = createLocalFile(fileName, userAccount);
        if (csvFile == null) {
            logger.warn("导出实体月票订单查询 csv 文件创建失败");
            return;
        }

        // write header and data into file
        writeCSV(csvFile, exportedList);

        // third compress the Excel file
        File zipFile = compressFile(csvFile);
        if (zipFile == null) {
            logger.warn("文件压缩失败");
        }
    }

    public List<List<String>> generateData() {
        return Arrays.asList(
                Arrays.asList("Header1", "Header2", "Header3"),
                Arrays.asList("Normal", "Data", "Without comma"),
                Arrays.asList("With,comma", "25", "New,York"),
                Arrays.asList("With\"quote", "30", "Boston")
        );
    }

    private File createLocalFile(String fileName, String userName) {
        File csvFile = null;
        try {
            String filePath = String.format("csv%s%s%s%s", File.separator, userName, File.separator, fileName);
            csvFile = new File(filePath);
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                logger.error("createLocalFile 创建文件夹失败");
                return null;
            }
            if (!csvFile.createNewFile()) {
                logger.error("createLocalFile 创建文件失败");
                return null;
            }
            logger.info("csv absolutePath: {}，path: {}", csvFile.getAbsolutePath(), csvFile.getPath());
        } catch (IOException ex) {
            logger.error("createLocalFile 创建文件异常", ex);
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
        } catch (IOException e) {
            logger.error("写入文件导出Excel CSV 文件写入异常", e);
        }

        // 不需要 finally 块关闭资源，try-with-resources 会自动处理
    }

    private File compressFile(File srcFile) {
        logger.info("写入文件导出Excel 开始压缩文件，absolutePath：{}", srcFile.getAbsolutePath());

        File zipFile = new File(srcFile.getPath() + ".zip");
        try (InputStream input = new FileInputStream(srcFile);
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {

            // 这里的 / 很重要，ZipEntry 的 name 必须以 / 开头，如果以其他的方式开头会导致解压失败
            zos.putNextEntry(new ZipEntry(srcFile.getName()));  // 移除了前导斜杠

            int count;
            byte[] data = new byte[1024];

            while ((count = input.read(data)) != -1) {
                zos.write(data, 0, count);
            }
            zos.closeEntry();

            return zipFile;
        } catch (IOException e) {
            logger.error("写入文件导出Excel 压缩文件异常", e);
            return null;
        }
    }

}