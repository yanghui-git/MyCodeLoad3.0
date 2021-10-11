package com.yh.springeasy.environmentpostprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 动态管理配置文件扩展接口EnvironmentPostProcessor
 *
 * https://www.jianshu.com/p/be6c818fe6ff
 *
 * SPI spring.factories 配置
 */
@Component
public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {


    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication springApplication) {
        try{
            InputStream inputStream = new FileInputStream("/Users/hui.yang/IdeaProjects/MyCodeLoad3.0/springeasy/src/main/resources/environmentpostprocessortest/EnvironmentPostProcessor.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            //自定义添加配置
            properties.setProperty("dubbo.consumer.check.yh","false");
            PropertiesPropertySource propertiesPropertySource = new PropertiesPropertySource("EnvironmentPostProcessorTest",properties);
            environment.getPropertySources().addLast(propertiesPropertySource);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
