package com.yh.springeasy.invoke;

public class JavaCommonInvokeInstruction {

    public static void main(String[] args) {

    }

    public void invoke() {
        //invokespeicial
        InvokeInterface sample = new InvokeInterfaceImpl();
        //invokeinterface
        sample.invokeInterface();
        InvokeInterfaceImpl sampleImpl = new InvokeInterfaceImpl();
        //invokevirtual
        sampleImpl.invokeNormalMethod();
        //invokestatic
        InvokeInterfaceImpl.invokeStaticMethod();
    }
}
