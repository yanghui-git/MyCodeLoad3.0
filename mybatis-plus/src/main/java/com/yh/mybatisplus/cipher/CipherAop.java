package com.yh.mybatisplus.cipher;


import com.yh.mybatisplus.demouser.entity.DemoUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Objects;

/**
 * Aop 拦截器
 */
public class CipherAop {


    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = null;
        System.out.println("进入动态加解密Aop拦截器");
        //获取方法注解
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] newArgs = proceedingJoinPoint.getArgs();
        Parameter[] parameters = method.getParameters();

        /**
         * 方法执行前 对参数进行加密处理
         */
        for (int i = 0; i < parameters.length; i++) {
            CipherAnnotation encrypt = parameters[i].getAnnotation(CipherAnnotation.class);
            if (Objects.nonNull(encrypt) && encrypt.mode().equals("encrypt")) {
                System.out.println("该方法 " + method.getName() + " 需要进行加密处理");
                DemoUser demoUser = (DemoUser) newArgs[i];
                demoUser.setName("加密成功");
                newArgs[i] = demoUser;
            }
        }
        result = proceedingJoinPoint.proceed(newArgs);

        /**
         * 解密
         */
        CipherAnnotation decrypt = method.getAnnotation(CipherAnnotation.class);
        if (Objects.nonNull(decrypt) && decrypt.mode().equals("decrypt")) {
            System.out.println("方法 " + method.getName() + " 需要进行解密");
            result = result.toString() + "解密成功";
        }
        return result;
    }

}
