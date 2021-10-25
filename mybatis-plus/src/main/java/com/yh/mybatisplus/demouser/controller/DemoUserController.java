package com.yh.mybatisplus.demouser.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yh.mybatisplus.demouser.entity.DemoUser;
import com.yh.mybatisplus.demouser.mapper.DemoUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Mybatis-Plus 使用demo
 */
@RestController
@RequestMapping("mybatis")
public class DemoUserController {

    @Autowired
    private DemoUserMapper demoUserMapper;


    /**
     * 分页查询
     *
     * @param pageNum  页码 从1 开始
     * @param pageSize 页数
     * @return
     */
    @GetMapping("/five")
    public Object testFive(@RequestParam(required = true,defaultValue = "1") Integer pageNum,
                           @RequestParam(required = true,defaultValue = "20") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<DemoUser> pageInfo = new PageInfo<DemoUser>(demoUserMapper.selectList(null));
        PageHelper.clearPage();
        return pageInfo;
    }
}
