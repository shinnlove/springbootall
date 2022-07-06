/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tony Zhao
 * @version $Id: MatchTester.java, v 0.1 2022-07-06 3:05 PM Tony Zhao Exp $$
 */
public class MatchTester {

    private static final String ANDROID_GAME_URL_DEMOTION_PREFIX     = "https://www.biligame.com/detail/?id=";

    private static final String ANDROID_GAME_URL                     = "bilibili://game_center/detail?id=12&sourceType=adPut";
    private static final String ANDROID_GAME_REGULAR                 = "^(bilibili:\\/\\/game_center/detail\\?id=)(\\d*)[\\&]?(.*)$";

    private static final String TEST_NO_MATCH_ANDROID_GAME_URL1      = "bilibili://game_center/detail?pid=12&sourceType=adPut";
    private static final String TEST_NO_MATCH_ANDROID_GAME_URL2      = "https://game_center/detail?id=12&sourceType=adPut";
    private static final String TEST_NO_MATCH_ANDROID_GAME_URL3      = "bilibiili://game_center/detail?id=12&sourceType=adPut";

    private static final String URL_401_ENV_DEMOTION_UAT_PREFIX      = "http://cm-mng.bilibili.co/tetris/page/?pageId=";
    private static final String URL_401_ENV_DEMOTION_PRE_PROD_PREFIX = "https://gaoneng.bilibili.com/tetris/page/?pageId=";

    private static final String URL_401_PRE_DEV_NO_HEADER            = "https://miniapp.bilibili.com/applet/predev-bilinternal21fd3b7fbe484bb5/pages/business-noheader?page_id=12345678";
    private static final String URL_401_PRE_DEV_HEADER               = "https://miniapp.bilibili.com/applet/predev-bilinternal21fd3b7fbe484bb5/pages/business?page_id=23456789";

    private static final String URL_401_PRE_DEV_NO_HEADER_REGULAR    = "^(https:\\/\\/miniapp.bilibili.com/applet/predev-bilinternal21fd3b7fbe484bb5/pages/business-noheader\\?page_id=)(\\d*)[\\&]?(.*)$";
    private static final String URL_401_PRE_DEV_HEADER_REGULAR       = "^(https:\\/\\/miniapp.bilibili.com/applet/predev-bilinternal21fd3b7fbe484bb5/pages/business\\?page_id=)(\\d*)[\\&]?(.*)$";

    private static final String URL_401_PRE_PROD_NO_HEADER           = "https://miniapp.bilibili.com/applet/bilinternal21fd3b7fbe484bb5/pages/business-noheader?page_id=34567890";
    private static final String URL_401_PRE_PROD_HEADER              = "https://miniapp.bilibili.com/applet/bilinternal21fd3b7fbe484bb5/pages/business?page_id=012345678";

    private static final String URL_401_PRE_PROD_NO_HEADER_REGULAR   = "^(https:\\/\\/miniapp.bilibili.com/applet/bilinternal21fd3b7fbe484bb5/pages/business-noheader\\?page_id=)(\\d*)[\\&]?(.*)$";
    private static final String URL_401_PRE_PROD_REGULAR             = "^(https:\\/\\/miniapp.bilibili.com/applet/bilinternal21fd3b7fbe484bb5/pages/business\\?page_id=)(\\d*)[\\&]?(.*)$";

    public static void main(String[] args) {
        // 安卓游戏降级
        //System.out.println(test_android_game_judge());
        System.out.println(android_game_judge());

        // 落地页401匹配
        System.out.println(url_401_judge_pre_dev_no_header());
        System.out.println(url_401_judge_pre_dev_header());
        System.out.println(url_401_pre_prod_no_header());
        System.out.println(url_401_pre_prod_header());
    }

    private static String urlDemotion(String originalUrl, String matchRegular, String demotionUrl) {
        Pattern p = Pattern.compile(matchRegular);
        Matcher m = p.matcher(originalUrl);
        if (m.find()) {
            // id在正则中被固定在group 2
            return demotionUrl + m.group(2);
        }
        return originalUrl;
    }

    private static String url_401_judge_pre_dev_no_header() {
        return urlDemotion(URL_401_PRE_DEV_NO_HEADER, URL_401_PRE_DEV_NO_HEADER_REGULAR,
            URL_401_ENV_DEMOTION_UAT_PREFIX);
    }

    private static String url_401_judge_pre_dev_header() {
        return urlDemotion(URL_401_PRE_DEV_HEADER, URL_401_PRE_DEV_HEADER_REGULAR,
            URL_401_ENV_DEMOTION_UAT_PREFIX);
    }

    private static String url_401_pre_prod_no_header() {
        return urlDemotion(URL_401_PRE_PROD_NO_HEADER, URL_401_PRE_PROD_NO_HEADER_REGULAR,
            URL_401_ENV_DEMOTION_PRE_PROD_PREFIX);
    }

    private static String url_401_pre_prod_header() {
        return urlDemotion(URL_401_PRE_PROD_HEADER, URL_401_PRE_PROD_REGULAR,
            URL_401_ENV_DEMOTION_PRE_PROD_PREFIX);
    }

    private static String android_game_judge() {
        return urlDemotion(ANDROID_GAME_URL, ANDROID_GAME_REGULAR,
            ANDROID_GAME_URL_DEMOTION_PREFIX);
    }

    private static String test_android_game_judge() {
        // 测试用例
        boolean isMatch0 = Pattern.matches(ANDROID_GAME_REGULAR, ANDROID_GAME_URL);
        boolean isMatch1 = Pattern.matches(ANDROID_GAME_REGULAR, TEST_NO_MATCH_ANDROID_GAME_URL1);
        boolean isMatch2 = Pattern.matches(ANDROID_GAME_REGULAR, TEST_NO_MATCH_ANDROID_GAME_URL2);
        boolean isMatch3 = Pattern.matches(ANDROID_GAME_REGULAR, TEST_NO_MATCH_ANDROID_GAME_URL3);

        System.out.println(isMatch0);
        System.out.println(isMatch1);
        System.out.println(isMatch2);
        System.out.println(isMatch3);

        // 匹配出id
        Pattern p = Pattern.compile(ANDROID_GAME_REGULAR);
        Matcher m = p.matcher(ANDROID_GAME_URL);
        if (m.find()) {
            System.out.println(m.group(1));
            System.out.println(m.group(2));
            System.out.println(m.group(3));

            String g = m.group(2);
            return ANDROID_GAME_URL_DEMOTION_PREFIX + g;
        }

        return "";
    }

}