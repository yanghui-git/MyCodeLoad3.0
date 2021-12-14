package com.yh.mybatisplus.cipher;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义加解密 注解
 */
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface CipherAnnotation {

    /**
     * 模式
     * encrypt 加密
     * decrypt 解密
     */
    String mode() default "encrypt";

}
