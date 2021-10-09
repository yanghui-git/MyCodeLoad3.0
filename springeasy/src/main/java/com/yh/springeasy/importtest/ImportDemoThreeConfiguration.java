package com.yh.springeasy.importtest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;

/**
 * three  {@link ImportBeanDefinitionRegistrar}
 */
@Import(ImportDemoThreeBeanDefinitionRegister.class)
@Configuration
public class ImportDemoThreeConfiguration {
}
