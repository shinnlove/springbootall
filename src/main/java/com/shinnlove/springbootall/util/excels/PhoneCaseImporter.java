/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.excels;

import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.*;
import java.security.MessageDigest;
import java.util.*;

/**
 * 移动手机壳Excel处理。
 *
 * @author Tony Zhao
 * @version $Id: PhoneCaseImporter.java, v 0.1 2025-07-24 19:21 Tony Zhao Exp $$
 */
public class PhoneCaseImporter {

    private static final String FILE_ROOT_DIR = "/Users/zhaochensheng/";
    private static final String URL_PREFIX_CDN_DOMAIN = "https://qdreaderpic-1252317822.image.myqcloud.com";
    private static final String CDN_BIZ_RESOURCES_DIR = "/cashbuy/yg/";

    private static final String ENV_NAME = "Prod";

    public static void main(String[] args) throws Exception {
        // Warning：pls 替换为你本地的实际路径
        String excelPath = FILE_ROOT_DIR + "Downloads/yinge3rd/需要生成的手机壳型号_2025_07_24_正确导入版本Ver3.0.xlsx";
        String rootImageDir = FILE_ROOT_DIR + "Downloads/yinge3rd/phone-20250716-154611";
        String outputImageDir = FILE_ROOT_DIR + "Downloads/yinge3rd/outputImages" + ENV_NAME + "/";
        String ymlOutputPath = FILE_ROOT_DIR + "Downloads/yinge3rd/output_" + ENV_NAME + ".yml";

        // 前缀策略：当前默认“黑色-”
        FolderPrefixResolver prefixResolver = new BlackPrefixResolver();

        List<PhoneCaseSku> skuList = readExcel(excelPath);
        List<Map<String, Object>> skuInfoList = new ArrayList<>();

        for (PhoneCaseSku sku : skuList) {
            int uniqueId = generateUniqueId(sku.getYgSkuId());
            sku.setUniqueId(uniqueId);

            if (!copyImages(sku, rootImageDir, outputImageDir, prefixResolver)) {
                continue;
            }

            Map<String, Object> skuMap = new LinkedHashMap<>();
            skuMap.put("uniqueId", uniqueId);
            skuMap.put("skuId", sku.getSkuId());
            skuMap.put("ygSkuId", sku.getYgSkuId());
            skuMap.put("skuName", sku.getSkuName());
            skuMap.put("brandName", sku.getBrandName());
            skuMap.put("width", sku.getWidth());
            skuMap.put("height", sku.getHeight());
            skuMap.put("editAreaX", sku.getEditAreaX());
            skuMap.put("editAreaY", sku.getEditAreaY());
            skuMap.put("editAreaWidth", sku.getEditAreaWidth());
            skuMap.put("bgPicUrl", sku.getCdnPrefix() + uniqueId + "-bg.png");
            skuMap.put("maskPicUrl", sku.getCdnPrefix() + uniqueId + "-mask.png");
            skuMap.put("extend", "");
            skuInfoList.add(skuMap);
        }

        Map<String, Object> root = new LinkedHashMap<>();
        Map<String, Object> spu = new LinkedHashMap<>();
        spu.put("spuId", 16);
        spu.put("skuInfoList", skuInfoList);
        root.put("spuInfoList", Collections.singletonList(spu));

        // 开始生成yaml文件
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);
        try (Writer writer = new FileWriter(ymlOutputPath)) {
            yaml.dump(root, writer);
        }

        System.out.println("✅ YML 配置文件生成成功: " + ymlOutputPath);
    }

    public static List<PhoneCaseSku> readExcel(String path) throws Exception {
        List<PhoneCaseSku> result = new ArrayList<>();
        try (InputStream is = new FileInputStream(path);
             Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                PhoneCaseSku sku = new PhoneCaseSku();
                sku.setSpuName(getString(row, 0));
                sku.setSkuId(getInt(row, 1));
                // staging环境是第3列
//                sku.setYgSkuId(getInt(row, 2));
                // 生产环境是第4列
                sku.setYgSkuId(getInt(row, 3));
                sku.setSkuName(getString(row, 5));
                sku.setBrandName(getString(row, 6));
                sku.setWidth(getString(row, 7));
                sku.setHeight(getString(row, 8));
                sku.setEditAreaX(getString(row, 9));
                sku.setEditAreaY(getString(row, 10));
                sku.setEditAreaWidth(getString(row, 11));

                if (!sku.getSkuName().equalsIgnoreCase("#N/A") && sku.getYgSkuId() > 0) {
                    result.add(sku);
                }
            }
        }
        return result;
    }

    public static int generateUniqueId(int ygSkuId) throws Exception {
        String raw = "20250724-" + ygSkuId;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(raw.getBytes());
        return Math.abs(Arrays.hashCode(digest)) % 1_000_000_000;
    }

    public static boolean copyImages(PhoneCaseSku sku, String rootImageDir, String outputDir, FolderPrefixResolver resolver) {
        String folder = resolver.resolve(sku.getBrandName(), sku.getSkuName());
        String brandDir = sku.getBrandName().trim();

        Path bgSrc = Paths.get(rootImageDir, brandDir, folder, "bg.png");
        Path maskSrc = Paths.get(rootImageDir, brandDir, folder, "mask.png");

        if (!Files.exists(bgSrc) || !Files.exists(maskSrc)) {
            System.err.println("❌ 缺失图片 - 型号: " + sku.getSkuName()
                    + "，品牌: " + sku.getBrandName()
                    + "，路径: " + Paths.get(rootImageDir, brandDir, folder));
            return false;
        }

        try {
            Path outDir = Paths.get(outputDir);
            Files.createDirectories(outDir);

            Path bgDst = outDir.resolve(sku.getUniqueId() + "-bg.png");
            Path maskDst = outDir.resolve(sku.getUniqueId() + "-mask.png");

            Files.copy(bgSrc, bgDst, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(maskSrc, maskDst, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("✅ 图片已拷贝: " + sku.getSkuName() + " → " + bgDst.getFileName());
            return true;
        } catch (IOException e) {
            System.err.println("❌ 图片拷贝失败: " + sku.getSkuName() + "，原因: " + e.getMessage());
            return false;
        }
    }

    private static String getString(Row row, int col) {
        Cell cell = row.getCell(col);
        return cell == null ? "" : cell.toString().trim();
    }

    private static int getInt(Row row, int col) {
        try {
            return (int) row.getCell(col).getNumericCellValue();
        } catch (Exception e) {
            return 0;
        }
    }

    @Data
    static class PhoneCaseSku {
        String spuName;
        int skuId;
        int ygSkuId;
        int uniqueId;
        String skuName;
        String brandName;
        String width;
        String height;
        String editAreaX;
        String editAreaY;
        String editAreaWidth;
        final String cdnPrefix = URL_PREFIX_CDN_DOMAIN + CDN_BIZ_RESOURCES_DIR + ENV_NAME + "/";
    }

    /**
     * 前缀策略接口
     */
    public interface FolderPrefixResolver {
        String resolve(String brandName, String skuName);
    }

    /**
     * 默认“黑色-”前缀
     */
    public static class BlackPrefixResolver implements FolderPrefixResolver {
        @Override
        public String resolve(String brandName, String skuName) {
            return "黑色-" + skuName.trim();
        }
    }

}