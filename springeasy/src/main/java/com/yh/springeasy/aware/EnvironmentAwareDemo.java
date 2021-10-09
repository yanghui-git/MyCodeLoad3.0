package com.yh.springeasy.aware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * https://mrlee.blog.csdn.net/article/details/89432877?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-2.no_search_link&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-2.no_search_link
 * Environment demo
 */
@Component
public class EnvironmentAwareDemo implements EnvironmentAware {

    @Autowired
    private Environment environment;

    public void setEnvironment(Environment env) {
        System.out.println("EnvironmentAware");
        String test = env.getProperty("test.env.yh");
        System.out.println(test);

        System.out.println(environment.getProperty("test.env.yh.two"));
    }
}
