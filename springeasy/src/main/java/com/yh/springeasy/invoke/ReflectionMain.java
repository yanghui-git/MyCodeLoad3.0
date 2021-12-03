package com.yh.springeasy.invoke;

import java.lang.reflect.Method;

public class ReflectionMain {

    public void reflection() throws Exception {

        String methodName = "length";
        Method method = String.class.getDeclaredMethod(methodName);
        Object result = method.invoke("abc");
    }

/*    public static void main(String[] args) throws Exception {
        new ReflectionMain().reflection();
    }*/
}
