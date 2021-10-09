package com.yh.springeasy.springscanner;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import java.time.LocalDateTime;
import java.util.Set;

public class MyClassPathDefinitonScanner extends ClassPathBeanDefinitionScanner {

    public MyClassPathDefinitonScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
        addIncludeFilter(new AnnotationTypeFilter(MyBean.class));
    }

    @Override
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> beanDefinitions = super.findCandidateComponents(basePackage);
        for(BeanDefinition item:beanDefinitions){
            if (!(item instanceof ScannedGenericBeanDefinition)) {
                continue;
            }
            AnnotationMetadata metadata = ((ScannedGenericBeanDefinition) item).getMetadata();
            if (null == metadata) {
                continue;
            }

            /**
             * 添加自定义属性数据
             *
             */
            MutablePropertyValues propertyValues = item.getPropertyValues();
            propertyValues.addPropertyValue("name", "yh自定义Bean测试"+ LocalDateTime.now().toString());
            propertyValues.addPropertyValue("appName","hahah");
            /**
             * 获取自定义Bean 数据
             */
            AnnotationAttributes annotationAttr = AnnotationAttributes.fromMap(
                    metadata.getAnnotationAttributes(MyBean.class.getName()));
            if (null == annotationAttr) {
                continue;
            }
            annotationAttr.put("messageMode", "yhtestghahahha");

            System.out.println(annotationAttr.getNumber("minConsumeThread"));
            System.out.println(annotationAttr.<Integer>getNumber("maxConsumeThread"));
            System.out.println(annotationAttr.getString("messageMode"));

        }
        return beanDefinitions;

    }

}
