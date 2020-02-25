package com.raines.raineslearn.redis.idempotent;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BusinessController {


    @Resource
    private TokenService tokenService;


    /**
     * 幂等接口，必须携带token
     * @return
     */
    @AutoIdempotent
    @PostMapping("/test/Idempotence")
    public String testIdempotence() {
        String businessResult = "幂等接口";
        if (StrUtil.isNotBlank(businessResult)) {
            return businessResult;
        }
        return StrUtil.EMPTY;

    }

    /**
     * 获取幂等接口所需token
     * @return
     */
    @PostMapping("/get/token")
    public String getToken() {
        String token = tokenService.createToken();
        if (StrUtil.isNotBlank(token)) {
            return token;
        }
        return StrUtil.EMPTY;
    }

}
