package com.yh.springeasy.forwardandrediect;

import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重定向 demo
 */
@RestController
public class RedirectController {

    @RequestMapping("/redirect/one")
    public void testOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/redirect/test");
    }


    @RequestMapping("/redirect/test")
    public String testThree() {
        return "redirect test";
    }
}
