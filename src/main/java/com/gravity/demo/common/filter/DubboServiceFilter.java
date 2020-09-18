package com.gravity.demo.common.filter;


import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.slf4j.MDC;

import java.util.UUID;

@Slf4j
@Activate
public class DubboServiceFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) {

        String msgId = UUID.randomUUID().toString();
        MDC.put("transId", msgId);
        //打印入参日志
        log.info("[{}-{}]Request body:{}", invocation.getInvoker().getInterface().getName(), invocation.getMethodName(), invocation.getArguments());

        //开始时间
        long startTime = System.currentTimeMillis();
        Result result = null;
        try {
            //执行接口调用逻辑
            result = invoker.invoke(invocation);
            //调用耗时
            long elapsed = System.currentTimeMillis() - startTime;
            //如果发生异常 则打印异常日志
            if (result.hasException()) {
                log.error("Response body", result.getException());
            } else {
                log.info("Response body: {}", result.getValue());
            }
            log.info("Successfully completed request. {} ms", elapsed);
            MDC.remove("transId");
        } catch (Exception e) {
            log.error("Response body", e);
        }
        //返回结果响应结果
        return result;
    }
}
