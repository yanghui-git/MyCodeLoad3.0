package com.yh.mybatis.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yh.mybatisplus.MybatisPlusMain;
import com.yh.mybatisplus.demouser.entity.DemoUser;
import com.yh.mybatisplus.demouser.mapper.DemoUserMapper;
import com.yh.mybatisplus.demouser.service.DemoUserService;
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
 */
// 设置启动类
@SpringBootTest(classes = MybatisPlusMain.class)
@RunWith(SpringRunner.class)
public class SpringTxTest {

    @Autowired
    private DemoUserService demoUserService;

    @Autowired
    private DemoUserMapper demoUserMapper;

    @Test
    public void test() {
        demoUserService.addDemoUser(DemoUser.builder()
                .name("事物测试1").age(100)
                .build());
        //检验
      //  System.out.println(demoUserMapper.selectList(new LambdaQueryWrapper<DemoUser>()
        //        .eq(DemoUser::getName, "事物测试1")));
    }
}
