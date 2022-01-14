package com.oathc.authresource.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping
public class UserController {

    @GetMapping("/get_resource")
    public String getResource() {
        return "OK";
    }

    @GetMapping("resource_server")
    public String test() {
        return "资源服务器数据获取成功" + new Date();
    }

    /**
     * auth2
     * 资源服务器收到请求时， 会先通过请求 认证服务器 auth_service 去校验这个 token 是否正确 ,有效
     *
     * 然后将认证后的用户信息 保存在当前用户中
     *
     * 通过此进行获取
     *
     * @return
     */
    @GetMapping("/user")
    public Object getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

}