package com.raines.raineslearn.redis.idempotent;

import com.raines.raineslearn.redis.idempotent.cache.DataCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {


    @Autowired
    private DataCache dataCache;

    /**
     * 检验token
     *
     * @param request
     * @return
     */
    @Override
    public boolean checkToken(HttpServletRequest request) throws Exception {
        String token = request.getHeader(Constant.TOKEN_NAME);
        if (StrUtil.isBlank(token)) {
// header中不存在tokenw
            token = request.getParameter(Constant.TOKEN_NAME);
            if (StrUtil.isBlank(token)) {
            // parameter中也不存在token
                throw new Exception("parameter中也不存在token");
            }
        }
        if (!dataCache.exists(token)) {
            throw new Exception("找不到token");
        }
        boolean remove = dataCache.remove(token);
        if (!remove) {
            throw new Exception("redis错误");
        }
        return true;
    }

    /**
     * 创建token
     *
     * @return
     */
    @Override
    public String createToken() {
        String str = UUID.randomUUID().toString();
        StringBuilder token = new StringBuilder();
        try {
            token.append(str);
            dataCache.set(token.toString(), token.toString(), 10000L);
            boolean notEmpty = StrUtil.isNotBlank(token.toString());
            if (notEmpty) {
                return token.toString();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
