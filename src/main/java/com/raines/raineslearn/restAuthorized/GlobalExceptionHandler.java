package com.raines.raineslearn.restAuthorized;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局统一异常处理增强
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * API统一异常处理
     **/
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public boolean jsonApiErrorHandler(HttpServletRequest request, Exception e) {
        System.out.println("API统一异常处理");
        e.printStackTrace();
        return false;
//    public ErrorInfo<Exception> jsonApiErrorHandler(HttpServletRequest request, Exception e) {
//        ErrorInfo<Exception> errorInfo = new ErrorInfo<>();
//        try {
//            System.out.println("统一异常处理...");
//            e.printStackTrace();
//
//            Throwable innerEx = e.getCause();
//            while (innerEx != null) {
//                //innerEx.printStackTrace();
//                if (innerEx.getCause() == null) {
//                    break;
//                }
//                innerEx = innerEx.getCause();
//            }
//
//            if (innerEx == null) {
//                errorInfo.setMessage(e.getMessage());
//                errorInfo.setError(e.toString());
//            } else {
//                errorInfo.setMessage(innerEx.getMessage());
//                errorInfo.setError(innerEx.toString());
//            }
//
//            errorInfo.setData(e);
//            errorInfo.setTimestamp(new Date());
//            errorInfo.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());//500错误
//            errorInfo.setUrl(request.getRequestURL().toString());
//            errorInfo.setPath(request.getServletPath());
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//
//            errorInfo.setMessage(ex.getMessage());
//            errorInfo.setError(ex.toString());
//        }
//
//        return errorInfo;
    }

}