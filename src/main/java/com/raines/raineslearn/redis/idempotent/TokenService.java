package com.raines.raineslearn.redis.idempotent;

import javax.servlet.http.HttpServletRequest;

/**
 * 幂等接口所需功能
 */
public interface TokenService {

    /**
     * 创建token
     *
     * @return
     */
    String createToken();

    /**
     * 检验token
     *
     * @param request
     * @return
     */
    boolean checkToken(HttpServletRequest request) throws Exception;
}
