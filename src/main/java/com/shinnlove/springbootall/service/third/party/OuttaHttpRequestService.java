/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.third.party;


import com.shinnlove.springbootall.exceptions.HttpRequestException;
import com.shinnlove.springbootall.util.dto.ServiceResult;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * 对外HTTP请求服务接口。
 *
 * @author Tony Zhao
 * @version $Id: OuttaHttpRequestService.java, v 0.1 2025-03-04 15:07 Tony Zhao Exp $$
 */
public interface OuttaHttpRequestService {

    /**
     * 请求一次三方http接口。
     *
     * @param url
     * @param method
     * @param bizParams
     * @param type
     * @param <T>
     * @return
     * @throws HttpRequestException
     */
    <T> ServiceResult<T> requestOnce(String url, String method, Map<String, Object> bizParams, Type type) throws HttpRequestException;

}
