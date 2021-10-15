package com.yh.springeasy.forwardandrediect;

import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 转发 demo
 */
@RestController
public class ForwardController {

    @RequestMapping("/forward/one")
    public void testOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.getRequestDispatcher("/forward/test").forward(request,response);
    }


    @RequestMapping("/forward/test")
    public String testThree() {
        return "forward test";
    }
}
