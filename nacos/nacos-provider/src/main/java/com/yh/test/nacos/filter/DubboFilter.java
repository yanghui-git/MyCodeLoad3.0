package com.yh.test.nacos.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//1 使用注解开启 拦截器
@Activate(group = {Constants.PROVIDER})
public class DubboFilter implements Filter {

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        //打印入参
        log.info("dubbo入参，InterfaceName={},MethodName={},Parameter={}", invocation.getInvoker().getInterface().getName(), invocation.getMethodName(), invocation.getArguments());
        //执行接口调用逻辑
        Result result = invoker.invoke(invocation);
        //打印结果
        System.out.println(result);
        //返回结果响应数据
        return result;
    }
}
