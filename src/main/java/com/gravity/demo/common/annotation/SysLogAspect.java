package com.gravity.demo.common.annotation;

import cn.hutool.core.convert.Convert;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gravity.demo.common.utils.HttpContextUtils;
import com.gravity.demo.common.utils.IpUtils;
import com.gravity.demo.common.utils.SysUserUtils;
import com.gravity.demo.entity.sys.Log;
import com.gravity.demo.service.sys.LogService;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.apache.http.params.HttpParamsNames;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 系统日志切面处理
 *
 * @author qijunlin
 * @date 2019/11/22 5:04 下午
 */
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private LogService logService;
    @Autowired
    private ObjectMapper objectMapper;

    @Pointcut("@annotation(com.gravity.demo.common.annotation.SysLog)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {

        Object result = null;
        try {
            long begin = System.currentTimeMillis();
            //执行方法
            result = joinPoint.proceed();
            long time = System.currentTimeMillis() - begin;
            saveLog(joinPoint, time);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    private void saveLog(ProceedingJoinPoint point, long time) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Log log = new Log();
        SysLog logAnnotation = method.getAnnotation(SysLog.class);
        if (logAnnotation != null) {
            //注解上的描述
            log.setOperation(logAnnotation.value());
        }
        //请求的方法名
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.setMethod(className + "." + methodName);
        //请求方法参数值
        Object[] args = point.getArgs();
        LocalVariableTableParameterNameDiscoverer p = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = p.getParameterNames(method);
        if (args != null && paramNames != null) {
            StringBuilder params = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                params = handleParams(params, args, Arrays.asList(paramNames));
            }
            log.setParams(params.toString());
        }
        log.setIp(IpUtils.getIpAdress(HttpContextUtils.getHttpServletRequest()));
        log.setUsername(SysUserUtils.getLoginUsername());
        log.setTime(Convert.toInt(time));
        log.setCreateTime(LocalDateTime.now());
        logService.save(log);
    }

    private StringBuilder handleParams(StringBuilder params, Object[] args, List paramNames) {
        try {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Map) {
                    Map map = (Map) args[i];
                    ArrayList<Object> argList = new ArrayList<>();
                    ArrayList<Object> paramList = new ArrayList<>();
                    for (Object key : map.keySet()) {
                        argList.add(map.get(key));
                        paramList.add(key);
                    }
                    return handleParams(params, argList.toArray(), paramList);
                } else {
                    if (args[i] instanceof Serializable) {
                        Class<?> aClass = args[i].getClass();
                        try {
                            aClass.getDeclaredMethod("toString", new Class[]{null});
                            // 如果不抛出 NoSuchMethodException 异常则存在 toString 方法 ，安全的 writeValueAsString ，否则 走 Object的 toString方法
                            params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i]));
                        } catch (NoSuchMethodException e) {
                            params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i].toString()));
                        }
                    } else if (args[i] instanceof Mult) {
                        MultipartFile file = (MultipartFile) args[i];
                        params.append(" ").append(paramNames.get(i)).append(": ").append(file.getName());
                    } else {
                        params.append(" ").append(paramNames.get(i)).append(": ").append(args[i]);
                    }
                }
            }
        } catch (Exception ignore) {
            params.append("参数解析失败");
        }
        return params;
    }

}
