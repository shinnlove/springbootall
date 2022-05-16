/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.pdf.util;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.CollectionUtils;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.shinnlove.springbootall.pdf.consts.MergeConsts;
import com.shinnlove.springbootall.pdf.model.FilePartition;

/**
 * @author Tony Zhao
 * @version $Id: MergeUtil.java, v 0.1 2022-05-15 5:02 PM Tony Zhao Exp $$
 */
public class MergeUtil {

    /**
     * @param mergedPath        new file generate path
     * @param originPath        original file path
     * @param mergeBuckets      merged pdf results.
     * @return
     */
    public static int doMergePDFs(String mergedPath, String originPath,
                                  Map<Integer, Map<Integer, List<FilePartition>>> mergeBuckets) {

        int sum = 0;

        for (Map.Entry<Integer, Map<Integer, List<FilePartition>>> entry : mergeBuckets
            .entrySet()) {

            int firstChapter = entry.getKey();
            Map<Integer, List<FilePartition>> subChapters = entry.getValue();

            for (Map.Entry<Integer, List<FilePartition>> subEntry : subChapters.entrySet()) {
                int secondChapter = subEntry.getKey();
                List<FilePartition> partitions = subEntry.getValue();

                if (CollectionUtils.isEmpty(partitions)) {
                    continue;
                }

                // handle file merges

                FilePartition fp = partitions.get(0);

                // fetch first fileName and generate new one
                String fn = fp.getFileName();
                int len = fn.length();
                int pos = fn.indexOf(MergeConsts.UNDERLINE);
                String originalName = fn.substring(pos + 1, len);
                String prefix = firstChapter + MergeConsts.PERIOD + secondChapter
                                + MergeConsts.UNDERLINE;
                String newFileName = prefix + originalName;

                String newFileNameWithPath = mergedPath + newFileName;

                List<String> ps = new ArrayList<>();
                for (FilePartition partition : partitions) {
                    ps.add(originPath + MergeConsts.PATH_SLASH + partition.getFileName());
                }

                //                System.out.println(
                //                    "输出文件:" + prefix + ", " + originalName + "file size=" + partitions.size());
                //
                //                System.out.println("文件来源：" + ps);

                MergeUtil.mergePDFFiles(ps.toArray(new String[ps.size()]), newFileNameWithPath);

                // 合并一份文件
                sum += 1;
            }

        }

        return sum;
    }

    /**
     * 合并pdf
     *
     * @param files 需要合并的pdf路径
     * @param newFile
     * @return
     */
    public static boolean mergePDFFiles(String[] files, String newFile) {
        boolean retvalue = false;
        Document Document = null;
        PdfCopy copy = null;
        PdfReader reader = null;
        try {
            Document = new Document(new PdfReader(files[0]).getPageSize(1));
            copy = new PdfCopy(Document, new FileOutputStream(newFile));
            Document.open();
            for (int i = 0; i < files.length; i++) {
                reader = new PdfReader(files[i]);
                int n = reader.getNumberOfPages();
                for (int j = 1; j <= n; j++) {
                    Document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
            }
            retvalue = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (copy != null) {
                copy.close();
            }
            if (Document != null) {
                Document.close();
            }
        }
        return retvalue;
    }

    /**
     * String name1 = "1.4.2.1_汕头市澄海区越聪玩具厂.pdf";
     * String name2 = "1.4.2_汕头市澄海区越聪玩具厂.pdf";
     * String name3 = "1.3_汕头市澄海区越聪玩具厂.pdf";
     * String name4 = "1_汕头市澄海区越聪玩具厂.pdf";
     * String name5 = "汕头市澄海区越聪玩具厂.pdf";
     * String name6 = "1.4.2_";
     *
     * boolean r1 = match(name1);
     * boolean r2 = match(name2);
     * boolean r3 = match(name3);
     * boolean r4 = match(name4);
     * boolean r5 = match(name5);
     * boolean r6 = match(name6);
     *
     * System.out.println(r1);
     * System.out.println(r2);
     * System.out.println(r3);
     * System.out.println(r4);
     * System.out.println(r5);
     * System.out.println(r6);
     *
     * true
     * true
     * true
     * true
     * false
     * false
     *
     * @param fileName
     * @return
     */
    public static boolean match(String fileName) {
        String regex = "\\d[.\\d]{0,2}_.*[.pdf]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(fileName);
        return m.find();
    }

}