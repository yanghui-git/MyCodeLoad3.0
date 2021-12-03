package com.yh.springeasy.invoke;

/**
 * https://blog.csdn.net/hj7jay/article/details/73480386
 * Java语言的动态性-invokedynamic
 */
public class Lambda {

    public void lambda(Func func) {
        return;
    }

    public static void main(String[] args) {
        Lambda lambda = new Lambda();
        lambda.lambda(s -> {
            return true;
        });
        lambda.lambda(s -> {
            return false;
        });

    }
}
