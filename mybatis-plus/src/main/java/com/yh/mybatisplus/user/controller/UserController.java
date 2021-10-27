package com.yh.mybatisplus.user.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yh.mybatisplus.user.entity.User;
import com.yh.mybatisplus.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/one")
    public Object testOne() {
        return userMapper.selectList(null);
    }

    /**
     * 分页拦截器与多租户 拦截器一起使用
     *
     * @return
     */
    @GetMapping("/two")
    public Object testTwo(@RequestParam(required = true) Integer pageNum, @RequestParam(required = true) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<User> pageInfo = new PageInfo<User>(userMapper.selectList(null));
        return pageInfo;
    }
}


