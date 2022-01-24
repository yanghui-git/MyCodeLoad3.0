package com.oathc.authserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.Keymap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class LoginController {

    @GetMapping("/login")
    public ModelAndView toLogin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    public static void main(String[] args) {
        Map <String,String> test=new HashMap() {
            {
                put("test1","1");
                put("test2","2");
            }
        };
       System.out.println(test.remove("test1"));
     //  System.out.println(test.remove("test2"));

       System.out.println(test.remove("222"));
       System.out.println(test);
    }
}
