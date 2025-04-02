/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.third.party;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.*;

/**
 * @author Tony Zhao
 * @version $Id: DouYinVideo.java, v 0.1 2025-03-31 18:00 Tony Zhao Exp $$
 */
public class DouYinVideo {

    private static final OkHttpClient httpClient = new OkHttpClient.Builder()
            .followRedirects(true)
            .followSslRedirects(true)
            .build();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static VideoInfo getDouyinVideoInfo(String shareUrl) throws IOException {
        // 1. 获取重定向后的真实URL
        String finalUrl = getFinalUrl(shareUrl);

        // 2. 从URL中提取视频ID
        String videoId = extractVideoId(finalUrl);
        if (videoId == null) {
            throw new RuntimeException("无法从URL中提取视频ID");
        }

        // 3. 构造API请求URL
        String apiUrl = String.format("https://www.douyin.com/aweme/v1/web/aweme/detail/?aweme_id=%s&aid=1128&version_name=23.5.0&device_platform=android&os_version=2333", videoId);

        // 4. 发送API请求获取视频信息
        Request apiRequest = new Request.Builder()
                .url(apiUrl)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                .header("Referer", "https://www.douyin.com/")
                .header("Accept", "application/json")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
                .header("X-Requested-With", "XMLHttpRequest")
                // 这里需要替换为最新的有效Cookie
                .header("Cookie", "csrf_session_id=349d44f63907c76e0d5b1a2768fe6652; SEARCH_RESULT_LIST_TYPE=%22single%22; x-web-secsdk-uid=6e38ca2e-a255-4b9e-9ddc-77bec9ff81c4; UIFID_TEMP=96cd3b166f3029d7c1cc3f64582454ab8a83ff1f9e6d6689076dd47ef1dca5f8883c5ba65a056325ad8f73d7af46d2a61f81ab164bbb053aa9f2e5ef66c914579970fbd45f59e90369d0246279b7b925; ttwid=1%7CXcMHdp-uVH7H_D4Su71jFxsWpt3mjtkXOKTaZuh-5AI%7C1740382319%7C4f97953c02b2fb5fa6478f225a97f849b11827d5d337e3ad19fa9fa9a325239a; hevc_supported=true; s_v_web_id=verify_m7iqp53h_v717xYfD_QmkT_4XPJ_AS5Y_mMOorwk6QpVC; fpk1=U2FsdGVkX187oGyAMIdsmNTgPoLTz1dB+7DS1+2gH94NWd5Qvk0v2hjEI2maMNKLhOYVx5cRwQv3KF9Z08UOJg==; fpk2=0845b309c7b9b957afd9ecf775a4c21f; passport_csrf_token=c2d31cdbb73e0b7eafa8cfa6ec782029; passport_csrf_token_default=c2d31cdbb73e0b7eafa8cfa6ec782029; __security_mc_1_s_sdk_crypt_sdk=82244771-4751-9034; bd_ticket_guard_client_web_domain=2; __security_mc_1_s_sdk_sign_data_key_sso=bf59aa16-4857-8a6b; UIFID=0de8750d2b188f4235dbfd208e44abbb976428f0720eb983255afefa45d39c0cb53a9932b252b861f86d97dec18da5f6b19cc6f792ae985da2b1268f9f28584f98248055c0ccec09c15fe811b39751d69fc643cea590be9a9d53de4750341781f15e44be28240739497ce998dd66764e5700a60fbd96e3060615a0790d4e455b99d9203f3556247c46f7d41835639c682f864ca22bd392b07883b75706c6c920; volume_info=%7B%22isUserMute%22%3Afalse%2C%22isMute%22%3Atrue%2C%22volume%22%3A0.6%7D; dy_swidth=2560; dy_sheight=1440; stream_recommend_feed_params=%22%7B%5C%22cookie_enabled%5C%22%3Atrue%2C%5C%22screen_width%5C%22%3A2560%2C%5C%22screen_height%5C%22%3A1440%2C%5C%22browser_online%5C%22%3Atrue%2C%5C%22cpu_core_num%5C%22%3A8%2C%5C%22device_memory%5C%22%3A8%2C%5C%22downlink%5C%22%3A10%2C%5C%22effective_type%5C%22%3A%5C%224g%5C%22%2C%5C%22round_trip_time%5C%22%3A50%7D%22; strategyABtestKey=%221743415702.589%22; is_dash_user=1; __ac_nonce=067ea7f350068d4729686; __ac_signature=_02B4Z6wo00f01VyFc2wAAIDAdYHtENbdmx1cpXfAADDO3b; gulu_source_res=eyJwX2luIjoiNGZjZWI1ZTVhMjMzZmQ3MTNkNjU2YjQ4NTA2NTdhOTdjN2QxMzZlODc0YTMyMTA1YTExNGVjYjg3ZDA4YjQ4OSJ9; FORCE_LOGIN=%7B%22videoConsumedRemainSeconds%22%3A180%2C%22isForcePopClose%22%3A1%7D; download_guide=%223%2F20250331%2F0%22; passport_mfa_token=CjXlFzw08fgY0R1gwNeCnz9OhEnRLICLcXbPoLrAa9tckOGe%2BWOeogUCWupWfh%2FYhLHZ6weyixpKCjwAAAAAAAAAAAAATtLClhFrg6rSTOAPg%2FuF3Yd6VsvuZEFtN3sXMzhcSo%2Biefvfxyx9RoH8hi%2FAFGTqxxYQlL7tDRj2sdFsIAIiAQNsPS96; d_ticket=c37eec77d27f9f2308a29f31525ca7e74f70c; passport_assist_user=Cj1X3UUpHxjFQRIfaJWltwiJBgJjxxpz9jQQOtT9C8B2DMWkS6c9qyEFDlrKmfEivtpehI8T5_ortedRWgXEGkoKPAAAAAAAAAAAAABO0mNHMUWf3SPxxxjgdqAJkn5BDmMKT7FlAxiPknTG1w576kEHAiydeyu1mcpSZZb53BDQve0NGImv1lQgASIBA_TOK6M%3D; n_mh=p3i7kKksPF4ZDsLMIQdQbhidWGM6jgZ1qxLE48ZhqBw; sid_guard=aea44a5ff9c98c573c767c31004cccbc%7C1743421687%7C5184000%7CFri%2C+30-May-2025+11%3A48%3A07+GMT; uid_tt=686ee2c8dd1889b193db4620cb3ab8bc; uid_tt_ss=686ee2c8dd1889b193db4620cb3ab8bc; sid_tt=aea44a5ff9c98c573c767c31004cccbc; sessionid=aea44a5ff9c98c573c767c31004cccbc; sessionid_ss=aea44a5ff9c98c573c767c31004cccbc; is_staff_user=false; sid_ucp_v1=1.0.0-KDdhYjhjMjBiYmE0ODAwMThmYzBjODMxMWFiYTgzMTBmNzY4NTAyZjQKHwi23OSW-wIQ94GqvwYY7zEgDDDWj7vaBTgHQPQHSAQaAmxxIiBhZWE0NGE1ZmY5Yzk4YzU3M2M3NjdjMzEwMDRjY2NiYw; ssid_ucp_v1=1.0.0-KDdhYjhjMjBiYmE0ODAwMThmYzBjODMxMWFiYTgzMTBmNzY4NTAyZjQKHwi23OSW-wIQ94GqvwYY7zEgDDDWj7vaBTgHQPQHSAQaAmxxIiBhZWE0NGE1ZmY5Yzk4YzU3M2M3NjdjMzEwMDRjY2NiYw; login_time=1743421687194; SelfTabRedDotControl=%5B%5D; FOLLOW_NUMBER_YELLOW_POINT_INFO=%22MS4wLjABAAAAIwxjpWxBE4hWW0xi63WtEdVnpdaK9qJZOacdvuCG718%2F1743436800000%2F0%2F1743421688464%2F0%22; _bd_ticket_crypt_cookie=e5d3e14425bf00eddf73a07071da6ff8; __security_mc_1_s_sdk_sign_data_key_web_protect=b5375575-4222-aef7; __security_mc_1_s_sdk_cert_key=9466d9c0-46a7-9c19; __security_server_data_status=1; bd_ticket_guard_client_data=eyJiZC10aWNrZXQtZ3VhcmQtdmVyc2lvbiI6MiwiYmQtdGlja2V0LWd1YXJkLWl0ZXJhdGlvbi12ZXJzaW9uIjoxLCJiZC10aWNrZXQtZ3VhcmQtcmVlLXB1YmxpYy1rZXkiOiJCSTFYako2THhKRGlrYUZPSTNTQVgyZkhBeWxOS05jbDJxUXRjdjV1RGJMM0lVZmJTMnJPYXVud0ZiSHZuYXZpZ1BZK0pPWVE1UjY3RlFUVFBuVHVhUTg9IiwiYmQtdGlja2V0LWd1YXJkLXdlYi12ZXJzaW9uIjoyfQ%3D%3D; publish_badge_show_info=%220%2C0%2C0%2C1743421688894%22; home_can_add_dy_2_desktop=%221%22; odin_tt=d1bd9ff0a39e1beec14c6788ec3490b29a4207a5291d2462f01e6c0a3b872c28a5393c59a8fc302483012f02d3fa5d4ac51ae825c920903a84ea17813dd4c819; WallpaperGuide=%7B%22showTime%22%3A1743421692324%2C%22closeTime%22%3A0%2C%22showCount%22%3A1%2C%22cursor1%22%3A10%2C%22cursor2%22%3A2%7D; passport_fe_beating_status=true; biz_trace_id=afea0425; sdk_source_info=7e276470716a68645a606960273f276364697660272927676c715a6d6069756077273f276364697660272927666d776a68605a607d71606b766c6a6b5a7666776c7571273f275e58272927666a6b766a69605a696c6061273f27636469766027292762696a6764695a7364776c6467696076273f275e5827292771273f27323d3137373234373136313234272927676c715a75776a716a666a69273f2763646976602778; bit_env=eG-EbWxl0-EpB7wG3DHsxO4raSqBp-aHT-RKdHVDHmXq6-Lle0JCvX0BaCMcCZkCpJgpBGwuaiMYNFrP_bmYOcoRwmNe79o51essVCy8Muo_5G6CS1od_zqN_EdhZ2cMgJnCYBjrZ4iNrJ2OvDvu-0uvMCDxGO3utbCY6GV66sCEn8JHg3O_qq3xsrQ-gnIeqIrPhk9_OhrYfkTzGh9UyM02PLUMovuN3stJ47jKW2qOeb9O8aK7n-eGazCTa7JLzyimWbK-cNctsXwI0mk0App-gF6Zb9hRglmhS-bxsnxsYexxHx9U99-OYNn5KRMwYpqaNj_lBq3QQuepoPB9QC2IYW9kn1XWozuLNhOR7klqjcsbRm39XLTgUo3_lUgYU4D5snLIORD2Df_rX0_4PXz3STAMhtJjIQK-Mwko4XEjUPSzlaJcMd9do23WUrefYKLWmRdq2HfUDGCp94I72E_uidTSCvrigX1X5FaskILlQJLObJ5wk-u8LfLtmpz1-8k5iXYjPQe6aDT1sd36X1baGlgVSmAYy2d20EcHoRA%3D; passport_auth_mix_state=vvmubu5bqxwag7o9bvpdcch5bvay4een6s57205wnjd1vlyv; IsDouyinActive=true")
                .build();

        try (Response apiResponse = httpClient.newCall(apiRequest).execute()) {
            if (!apiResponse.isSuccessful()) {
                String errorBody = apiResponse.body().string();
                System.out.println("API请求失败，状态码: " + apiResponse.code());
                System.out.println("错误响应体: " + errorBody);
                throw new RuntimeException("API请求失败，状态码: " + apiResponse.code());
            }

            String responseBody = apiResponse.body().string();
            System.out.println("完整API响应: " + responseBody);  // 打印完整响应

            // 5. 解析JSON响应
            JsonNode rootNode = objectMapper.readTree(responseBody);

            // 尝试多种可能的响应格式
            JsonNode awemeDetail = rootNode.path("aweme_detail");
            if (awemeDetail.isMissingNode()) {
                awemeDetail = rootNode.path("item_list").path(0);
            }
            if (awemeDetail.isMissingNode()) {
                awemeDetail = rootNode.path("aweme_info");
            }

            if (awemeDetail.isMissingNode()) {
                throw new RuntimeException("视频信息获取失败，未找到视频详情字段。完整响应: " + responseBody);
            }

            VideoInfo videoInfo = new VideoInfo();
            videoInfo.setTitle(awemeDetail.path("desc").asText());
            videoInfo.setVideoId(videoId);
            videoInfo.setAuthor(awemeDetail.path("author").path("nickname").asText());
            videoInfo.setAuthorId(awemeDetail.path("author").path("unique_id").asText());
            videoInfo.setLikeCount(awemeDetail.path("statistics").path("digg_count").asInt());
            videoInfo.setCommentCount(awemeDetail.path("statistics").path("comment_count").asInt());
            videoInfo.setShareCount(awemeDetail.path("statistics").path("share_count").asInt());

            return videoInfo;
        }
    }

    private static String getFinalUrl(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            String finalUrl = response.request().url().toString();
            System.out.println("原始URL: " + url);
            System.out.println("重定向后URL: " + finalUrl);
            return finalUrl;
        }
    }

    private static String extractVideoId(String url) {
        // 匹配多种可能的抖音视频ID格式
        Pattern[] patterns = {
                Pattern.compile("video/(\\d+)"),  // https://www.douyin.com/video/123456789
                Pattern.compile("v\\.douyin\\.com/([A-Za-z0-9]+)/?"),  // 短链
                Pattern.compile("/(\\d+)\\?previous_page"),  // 重定向后的URL
                Pattern.compile("/([A-Za-z0-9]{10,})/?$")   // 其他格式
        };

        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }

        return null;
    }

    private static String extractVideoId2(String url) {
        // 抖音URL可能有多种格式，需要适配更多情况
        // 格式1: https://www.douyin.com/video/1234567890123456789
        // 格式2: https://v.douyin.com/AbCdEfG/
        // 格式3: https://www.iesdouyin.com/share/video/1234567890123456789

        // 尝试匹配长视频ID（纯数字）
        Pattern longIdPattern = Pattern.compile("video/(\\d+)");
        Matcher longIdMatcher = longIdPattern.matcher(url);
        if (longIdMatcher.find()) {
            return longIdMatcher.group(1);
        }

        // 尝试匹配短链中的短ID（字母数字组合）
        Pattern shortIdPattern = Pattern.compile("v\\.douyin\\.com/([A-Za-z0-9]+)/?");
        Matcher shortIdMatcher = shortIdPattern.matcher(url);
        if (shortIdMatcher.find()) {
            return shortIdMatcher.group(1);
        }

        // 尝试匹配其他可能的格式
        Pattern otherPattern = Pattern.compile("/([A-Za-z0-9]{10,})/?$");
        Matcher otherMatcher = otherPattern.matcher(url);
        if (otherMatcher.find()) {
            return otherMatcher.group(1);
        }

        return null;
    }

    public static class VideoInfo {
        private String title;
        private String videoId;
        private String author;
        private String authorId;
        private int likeCount;
        private int commentCount;
        private int shareCount;

        // Getters and Setters
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthorId() {
            return authorId;
        }

        public void setAuthorId(String authorId) {
            this.authorId = authorId;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getShareCount() {
            return shareCount;
        }

        public void setShareCount(int shareCount) {
            this.shareCount = shareCount;
        }

        @Override
        public String toString() {
            return "VideoInfo{" +
                    "title='" + title + '\'' +
                    ", videoId='" + videoId + '\'' +
                    ", author='" + author + '\'' +
                    ", authorId='" + authorId + '\'' +
                    ", likeCount=" + likeCount +
                    ", commentCount=" + commentCount +
                    ", shareCount=" + shareCount +
                    '}';
        }
    }

    public static void main(String[] args) {
        String shareUrl = "https://v.douyin.com/-H1LxMgUqw8/";

        try {
            VideoInfo videoInfo = getDouyinVideoInfo(shareUrl);
            System.out.println("视频标题: " + videoInfo.getTitle());
            System.out.println("作者昵称: " + videoInfo.getAuthor());
            System.out.println("点赞数: " + videoInfo.getLikeCount());
        } catch (Exception e) {
            System.err.println("获取视频信息失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

}