package com.yh.xxlrpc.analyze.produce.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置启动 将 生产者用XxlRpcService 注解标注的接口 全部上报至注册中心
 */
public class XxlRpcSpringProviderFactory implements ApplicationContextAware, InitializingBean {
    // 获取动态的bean

    private Map<String, Object> serviceData = new HashMap<String, Object>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //获取携带 XxlRpcService 注解的bean 接口，即那些接口需要进行远程调用 暴露
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(XxlRpcService.class);
        if (serviceBeanMap != null && serviceBeanMap.size() > 0) {
            for (Object serviceBean : serviceBeanMap.values()) {
                // valid
                // 只针对接口暴露
                if (serviceBean.getClass().getInterfaces().length == 0) {
                    throw new RuntimeException("xxl-rpc, service(XxlRpcService) must inherit interface.");
                }
                // 接口名称
                String iface = serviceBean.getClass().getInterfaces()[0].getName();
                // 将需要暴露的接口 先统一的存放于 HashMap中
                serviceData.put(iface, serviceBean);
            }
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("启动扫描 将带有注解 XxlRpcService 的接口全部暂存与hashMap中 serviceData");
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("开始向注册中心 上报接口信息" + serviceData);
        //-----HttpUtil
        //  http://localhost:8082/xxl-rpc-admin/api/registry
        //                   {"accessToken":null,"biz":"default","env":"default","registryDataList":[{"key":"com.xxl.rpc.sample.api.DemoService","value":"172.28.67.35:7080"}],"keys":null}
        //注册中心更新
    }
}
