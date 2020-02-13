package com.raines.raineslearn.restAuthorized;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

/**
 * 请求认证切面，验证自定义请求header的authtoken是否合法
 **/
@Aspect
@Component
public class AuthorizedAspect {

//    @Autowired
//    private AuthTokenService authTokenService;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {
    }

    //    @Pointcut("execution(* com.power.demo.controller.*Controller.*(..))")
    @Pointcut("execution(* dem*(..))")
    public void methodPointCut() {
    }

    /**
     * 某个方法执行前进行请求合法性认证 注入Authorized注解 （先）
     */
//    @Before("requestMapping() && methodPointCut()&&@annotation(authorized)")
    @Before("methodPointCut() && @annotation(authorized)")
    public void doBefore(JoinPoint joinPoint, Authorized authorized) throws Exception {

        System.out.println("====");

//        PowerLogger.info("方法认证开始...");


//        Class type = joinPoint.getSignature().getDeclaringType();
//
//        Annotation[] annotations = type.getAnnotationsByType(Authorized.class);
//
//        if (annotations != null && annotations.length > 0) {
////            PowerLogger.info("直接类认证");
//            return;
//        }
//
//        //获取当前http请求
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();

//        String token = request.getHeader(AppConst.AUTH_TOKEN);
//
//        BizResult<String> bizResult = authTokenService.powerCheck(token);

//        System.out.println(SerializeUtil.Serialize(bizResult));

//        if (bizResult.getIsOK() == true) {
//            PowerLogger.info("方法认证通过");
//        } else {
//            throw new Exception(bizResult.getMessage());
//        }
    }

    /**
     * 类下面的所有方法执行前进行请求合法性认证 （后）
     */
//    @Before("requestMapping() && methodPointCut()")
//    public void doBefore(JoinPoint joinPoint) throws Exception {
//
////        PowerLogger.info("类认证开始...");
//
////        Annotation[] annotations = joinPoint.getSignature().getDeclaringType().getAnnotationsByType(Authorized.class);
////
////        if (annotations == null || annotations.length == 0) {
//////            PowerLogger.info("类不需要认证");
////            return;
////        }
////
////        //获取当前http请求
////        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
////        HttpServletRequest request = attributes.getRequest();
//
////        String token = request.getHeader(AppConst.AUTH_TOKEN);
////
////        BizResult<String> bizResult = authTokenService.powerCheck(token);
////
////        System.out.println(SerializeUtil.Serialize(bizResult));
////
////        if (bizResult.getIsOK() == true) {
////            PowerLogger.info("类认证通过");
////        } else {
////            throw new Exception(bizResult.getMessage());
////        }
//    }

}