package com.yh.springeasy.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class InvokeExact {

    public void invokeExact() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(String.class, int.class, int.class);
        MethodHandle mh = lookup.findVirtual(String.class, "substring", type);
        String str = (String) mh.invokeExact("Hello World", 1, 3);
    }
}
