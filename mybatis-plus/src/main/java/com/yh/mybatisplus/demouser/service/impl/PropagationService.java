package com.yh.mybatisplus.demouser.service.impl;

import com.yh.mybatisplus.demouser.entity.DemoUser;
import com.yh.mybatisplus.demouser.mapper.DemoUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 探究事物的传播行为
 * https://blog.csdn.net/weixin_36162966/article/details/106575192?spm=1001.2101.3001.6650.5&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-5.fixedcolumn&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-5.fixedcolumn
 */
@Service
public class PropagationService {

    @Autowired
    private DemoUserMapper demoUserMapper;

    /**
     * 1 默认配置 REQUIRED
     * Support a current transaction, create a new one if none exists.
     * 如果当前没有事务，就创建一个新事务，如果当前存在事务，就加入该事务，该设置是最常用的默认设置
     * 如果methodB发生异常，触发事务回滚，也会methodA中的也会回滚
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void REQUIRED_A() {
        demoUserMapper.insert(DemoUser.builder().name("required_a").build());
        REQUIRED_B();
        int a = 1 / 0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void REQUIRED_B() {
        demoUserMapper.insert(DemoUser.builder().name("required_b").build());
        // int a=1/0;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void SUPPORTS_A() {
        demoUserMapper.insert(DemoUser.builder().name("support_a").build());
        SUPPORTS_B();
    }

    /**
     * 2 SUPPORTS
     * Support a current transaction, execute non-transactionally if none exists.
     * 支持当前事务，如果当前存在事务，就加入该事务，如果当前不存在事务，就以非事务执行。
     * <p>
     * 如果调用methodA，再调用methodB，MehtodB会加入到MethodA的开启的当前事务中。
     * <p>
     * 如果直接调用methodB，当前没有事务，就以非事务执行。
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void SUPPORTS_B() {
        demoUserMapper.insert(DemoUser.builder().name("support_b").build());
        int a = 1 / 0;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void MANDATORY_A() {
        demoUserMapper.insert(DemoUser.builder().name("MANDATORY_a").build());
        MANDATORY_B();
    }

    /**
     * 3 MANDATORY
     * Support a current transaction, throw an exception if none exists.
     * 支持当前事务，如果当前存在事务，就加入该事务，如果当前不存在事务，就抛出异常
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void MANDATORY_B() {
        demoUserMapper.insert(DemoUser.builder().name("MANDATORY_b").build());
        //  int a=1/0;
    }

    /**
     * 4 REQUIRES_NEW
     * Create a new transaction, and suspend the current transaction if one exists.
     * 创建新事务，无论当前存不存在事务，都创建新事务
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void REQUIRES_NEW_A() {
        demoUserMapper.insert(DemoUser.builder().name("REQUIRES_NEW_a").build());
        REQUIRES_NEW_B();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void REQUIRES_NEW_B() {
        demoUserMapper.insert(DemoUser.builder().name("REQUIRES_NEW_b").build());
        int a = 1 / 0;
    }


    /**
     * 5  NOT_SUPPORTED
     * Execute non-transactionally, suspend the current transaction if one exists
     * 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void NOT_SUPPORTED_A() {
        demoUserMapper.insert(DemoUser.builder().name("NOT_SUPPORTED_a").build());
        NOT_SUPPORTED_B();
        int a = 1 / 0;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void NOT_SUPPORTED_B() {
        demoUserMapper.insert(DemoUser.builder().name("NOT_SUPPORTED_b").build());
        int a = 1 / 0;
    }


    /**
     * 6  NEVER
     * Execute non-transactionally, throw an exception if a transaction exists.
     * 以非事务方式执行操作，如果当前存在事务，则抛出异常
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void NEVER_A() {
        demoUserMapper.insert(DemoUser.builder().name("NEVER_a").build());
        NEVER_B();
        System.out.println("6666666");
    }

    @Transactional(propagation = Propagation.NEVER)
    public void NEVER_B() {
        demoUserMapper.insert(DemoUser.builder().name("NEVER_b").build());
        int a = 1 / 0;
    }

    /**
     * 7 NESTED
     * * Execute within a nested transaction if a current transaction exists,
     * * behave like {@code REQUIRED} otherwise.
     * <p>
     * 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则按REQUIRED属性执行。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void NESTED_A() {
        demoUserMapper.insert(DemoUser.builder().name("NESTED_a").build());
        NESTED_B();
        System.out.println("6666666");
    }

    @Transactional(propagation = Propagation.NESTED)
    public void NESTED_B() {
        demoUserMapper.insert(DemoUser.builder().name("NESTED_b").build());
        int a = 1 / 0;
    }

    /**
     * 事物超时回滚机制
     * 执行超过3秒 进行回滚
     * https://www.cnblogs.com/renjiaqi/p/11688764.html
     */
    @Transactional(timeout = 3)
    public void timeOut() throws InterruptedException {
        Thread.sleep(5000l);
        demoUserMapper.insert(DemoUser.builder().name("timeOut_test").build());
        System.out.println("6666666");
    }
}
