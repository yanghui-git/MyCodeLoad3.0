package com.yh.springeasy.importtest;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class ImportDemoThreeBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition root = new RootBeanDefinition(ImportDemoThree.class);
        registry.registerBeanDefinition("importDemoThree", root);
    }
}
