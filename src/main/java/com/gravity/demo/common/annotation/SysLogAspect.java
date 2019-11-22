package com.gravity.demo.common.annotation;

import com.gravity.demo.entity.sys.Log;
import org.apache.http.params.HttpParamsNames;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Method;
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
    @Pointcut("@annotation(com.gravity.demo.common.annotation.SysLog)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object before(ProceedingJoinPoint joinPoint) {

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

    }

    private StringBuilder handleParams(StringBuilder params, Object[] args, List paramNames) {

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
            }else {
                if (args[i] instanceof Serializable) {
                    Class<?> aClass = args[i].getClass();
                    try {
                        aClass.getDeclaredMethod("toString", new Class[]{null});
                        // 如果不抛出 NoSuchMethodException 异常则存在 toString 方法 ，安全的 writeValueAsString ，否则 走 Object的 toString方法
                        params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i]));
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
