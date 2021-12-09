package com.yh.mybatisplus.demouser.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yh.mybatisplus.demouser.entity.DemoUser;
import com.yh.mybatisplus.demouser.mapper.DemoUserMapper;
import com.yh.mybatisplus.demouser.service.DemoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Mybatis-Plus 使用demo
 */
@RestController
@RequestMapping("mybatis")
public class DemoUserController {

    @Autowired
    private DemoUserMapper demoUserMapper;

    @Autowired
    private DemoUserService demoUserService;

    /**
     * 分页查询
     *
     * @param pageNum  页码 从1 开始
     * @param pageSize 页数
     * @return
     */
    @GetMapping("/five")
    public Object testFive(@RequestParam(required = true, defaultValue = "1") Integer pageNum,
                           @RequestParam(required = true, defaultValue = "20") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //配置系统属性为true,代理类生成时将自动写入磁盘
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        PageInfo<DemoUser> pageInfo = new PageInfo<DemoUser>(demoUserMapper.selectList(null));
        PageHelper.clearPage();
        return pageInfo;
    }

    @PostMapping("/demouser")
    public void add(@RequestBody DemoUser demoUser) {
        demoUserService.addDemoUser(demoUser);

    }


}
