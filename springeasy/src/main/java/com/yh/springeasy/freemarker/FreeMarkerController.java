package com.yh.springeasy.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/freemarker")
public class FreeMarkerController {

    @GetMapping("/one")
    public String hello(Model model){
        model.addAttribute("demo","hello freemarker !");
        return "/test";
    }

    @GetMapping("/two")
    public String test(Model model){
        model.addAttribute("name","freemarker test");
        return "/welcome";
    }
}
