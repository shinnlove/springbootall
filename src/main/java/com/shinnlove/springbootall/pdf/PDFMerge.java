/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.pdf;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shinnlove.springbootall.pdf.consts.MergeConsts;
import com.shinnlove.springbootall.pdf.model.FilePartition;
import com.shinnlove.springbootall.pdf.util.MergeUtil;
import com.shinnlove.springbootall.pdf.util.ParseUtil;
import com.shinnlove.springbootall.pdf.util.ScanUtil;

/**
 * @author Tony Zhao
 * @version $Id: PDFMerge.java, v 0.1 2022-05-12 11:29 AM Tony Zhao Exp $$
 */
public class PDFMerge {

    /** 1.1.2 => 1 => 1 => file list */
    private static Map<Integer, Map<Integer, List<FilePartition>>> mergeBuckets = new HashMap<>();

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("路径必传，请使用命令[java -jar 包含pdf的文件夹路径]");
            return;
        }

        String path = args[0];
        String mergedPath = path + MergeConsts.PATH_SLASH + MergeConsts.MERGED
                            + MergeConsts.PATH_SLASH;

        // delete old files and mkdir
        File directory = new File(mergedPath);
        if (directory.exists()) {
            File[] listFiles = directory.listFiles();
            for (File file : listFiles) {
                // System.out.println("Deleting " + file.getName());
                file.delete();
            }
        } else {
            boolean success = directory.mkdir();
            if (!success) {
                System.out.println("路径目录没有写文件权限，请开放权限");
            }
        }

        for (String s : ScanUtil.scanPDFFiles(path)) {
            ParseUtil.parseFileName(s, mergeBuckets);
        }

        // System.out.println(mergeBuckets);

        int total = MergeUtil.doMergePDFs(mergedPath, path, mergeBuckets);
        System.out.println("合并完成，总共合并成了" + total + "个文件");
    }

}
