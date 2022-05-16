/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.pdf.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

/**
 * @author Tony Zhao
 * @version $Id: ScanUtil.java, v 0.1 2022-05-15 5:03 PM Tony Zhao Exp $$
 */
public class ScanUtil {

    /**
     * Scan PDF files with 1.x.x_ prefix.
     *
     * @param path
     * @return
     */
    public static List<String> scanPDFFiles(String path) {
        List<String> scans = new ArrayList<>();
        File file = new File(path);
        File[] fs = file.listFiles();
        for (File f : fs) {
            if (!f.isDirectory()) {
                String fileName = f.getName();
                String fe = FilenameUtils.getExtension(fileName);
                // System.out.println("File extension is : " + fe);
                if (MergeUtil.match(fileName)) {
                    scans.add(fileName);
                }
            }
        }

        return scans;
    }

}