package com.yh.mybatis.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yh.mybatisplus.MybatisPlusMain;
import com.yh.mybatisplus.demouser.entity.DemoUser;
import com.yh.mybatisplus.demouser.mapper.DemoUserMapper;
import com.yh.mybatisplus.demouser.service.DemoUserService;
import com.yh.mybatisplus.demouser.service.impl.DemoUserServiceImplFour;
import com.yh.mybatisplus.demouser.service.impl.DemoUserServiceImplThree;
import com.yh.mybatisplus.demouser.service.impl.DemoUserServiceImplTwo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * https://zhanglong.blog.csdn.net/article/details/118107179?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.no_search_link&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.no_search_link
 *
 * @RunWith 作用
 * 指让类运行在Spring的测试环境，以便测试开始时自动创建Spring应用上下文
 * <p>
 * 事物失效情况
 * <p>
 * https://blog.csdn.net/weixin_42719412/article/details/85318391?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-1.opensearchhbase&spm=1001.2101.3001.4242.2
 */
// 设置启动类
@SpringBootTest(classes = MybatisPlusMain.class)
@RunWith(SpringRunner.class)
public class SpringTxTest {

    @Autowired
    private DemoUserService demoUserService;

    @Autowired
    private DemoUserMapper demoUserMapper;

    @Autowired
    public DemoUserServiceImplTwo demoUserServiceImplTwo;

    @Autowired
    public DemoUserServiceImplThree demoUserServiceImplThree;

    @Autowired
    private DemoUserServiceImplFour demoUserServiceImplFour;

    @Test
    public void test() {
        demoUserService.addDemoUser(DemoUser.builder()
                .name("事物测试1").age(100)
                .build());
        //检验
        //  System.out.println(demoUserMapper.selectList(new LambdaQueryWrapper<DemoUser>()
        //        .eq(DemoUser::getName, "事物测试1")));
    }

    /**
     * 探究事物失效1：  只能应用于public
     * <p>
     * 直接编译报错
     */
    @Test
    public void testTwo() {
        //public
        try {
            demoUserServiceImplTwo.addDemoUser(DemoUser.builder()
                    .name("事物测试100").age(100)
                    .build());
        } catch (Exception e) {

        }
        //private
        try {
            //  demoUserServiceImplTwo.addDemoUserTwo(DemoUser.builder()
            //             .name("事物测试100").age(100)
            //             .build());
        } catch (Exception e) {

        }
        // protect
        try {
            //     demoUserServiceImplTwo.addDemoUserThree(DemoUser.builder()
            //             .name("事物测试100").age(100)
            //             .build());
        } catch (Exception e) {

        }
    }

    /**
     * 默认情况下此注解会对unchecked异常进行回滚，对checked异常不回滚。
     * <p>
     * 那什么是unchecked,什么是checked呢？
     * <p>
     * 通俗的说，编译器能检测到的是checked，检测不到的就是unchecked。
     * 派生于Error或者RuntimeException（比如空指针，1/0）的异常称为unchecked异常。
     * 继承自Exception得异常统称为checked异常，如IOException、TimeoutException等
     */
    @Test
    public void testThree() {
        //   try {
        //   demoUserServiceImplThree.addDemoUser(
        DemoUser.builder()
                .name("事物测试200").age(200)
                .build();
        //);
        //  } catch (Exception e) {

        // }

        //  try {
        //  demoUserServiceImplThree.addDemoUserTwo(
        DemoUser.builder()
                .name("事物测试300").age(300)
                .build();
        //);
        //     } catch (Exception e) {

        //      }

        //     try {
        //   demoUserServiceImplThree.addDemoUserThree(
        DemoUser.builder()
                .name("事物测试300").age(400)
                .build();
        //);
        //    } catch (Exception e) {

        //      }

        try {
            demoUserServiceImplThree.addDemoUserFive(
                    DemoUser.builder()
                            .name("事物测试300").age(400)
                            .build());
        } catch (Exception e) {

        }

    }

    /**
     * 事物失效
     * 同一类中 方法调用
     */
    @Test
    public void testFour() {
        // demoUserServiceImplFour.addDemoUserTwo(DemoUser.builder().name("test500").age(500).build());
        // 使用AopContext.currentProxy()获取代理对象 调用方法
        demoUserServiceImplFour.addDemoUserTwoFix(DemoUser.builder().name("test500").age(500).build());
        //  demoUserServiceImplFour.addDemoUserThree(DemoUser.builder().name("test500").age(500).build());
    }
}
