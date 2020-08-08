package com.gravity.demo.common.aspect;//package com.huobi.huobichat.welfarechainapi.common.aspect;
//
//
//import com.huobi.huobichat.welfarechaincommon.filter.XssHttpServletRequestWrapper;
//import com.huobi.huobichat.welfarechaincommon.utils.IpUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.MDC;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.UUID;
//
///**
// * @description:系统日志，切面处理类
// */
//@Aspect
//@Component
//@Slf4j
//public class LogAspect {
//
//    @Pointcut("execution(public * com.huobi.huobichat.welfarechainapi.controller.*.*.*(..))")
//    public void logPointCut() {
//
//    }
//
//    @Before(value = "logPointCut()")
//    public void before(JoinPoint joinPoint) {
//        MDC.put("transId", UUID.randomUUID().toString().replace("-", "").toLowerCase());
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        request.setAttribute("startTime", System.currentTimeMillis());
//        if (request instanceof XssHttpServletRequestWrapper) {
//            String bodyStr = ((XssHttpServletRequestWrapper) request).getBodyStr();
//            Long startTime = (Long) request.getAttribute("startTime");
//            log.info("ip:{} url:{} requestBody:{}", IpUtil.getIp(request), request.getRequestURL(), bodyStr);
//        }
//    }
//
//    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
//    public void after(JoinPoint point, Object ret) {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        Long startTime = (Long) request.getAttribute("startTime");
//        log.info("R:{} time:{}毫秒", ret, System.currentTimeMillis() - startTime);
//        MDC.clear();
//    }
//
//}
