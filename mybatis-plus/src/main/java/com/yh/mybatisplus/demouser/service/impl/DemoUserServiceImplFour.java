package com.yh.mybatisplus.demouser.service.impl;

import com.yh.mybatisplus.demouser.entity.DemoUser;
import com.yh.mybatisplus.demouser.mapper.DemoUserMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 探究事物失效 方法内部调用
 */
@Service
public class DemoUserServiceImplFour {

    @Autowired
    private DemoUserMapper demoUserMapper;

    @Transactional
    public void addDemoUser(DemoUser demoUser) {
        demoUserMapper.insert(demoUser);
        int i = 1 / 0;
    }

    /**
     * 不可回滚
     *
     * @param demoUser
     */
    public void addDemoUserTwo(DemoUser demoUser) {
        addDemoUser(demoUser);
    }


    /**
     * 不可回滚 --解决方案 使用代码aop获取对象调用方法
     *
     * @param demoUser
     */
    public void addDemoUserTwoFix(DemoUser demoUser) {
        ((DemoUserServiceImplFour) AopContext.currentProxy()).addDemoUser(demoUser);
    }


    /**
     * 可以回滚
     *
     * @param demoUser
     */
    @Transactional
    public void addDemoUserThree(DemoUser demoUser) {
        addDemoUserFour(demoUser);
    }


    public void addDemoUserFour(DemoUser demoUser) {
        demoUserMapper.insert(demoUser);
        int i = 1 / 0;
    }

}
