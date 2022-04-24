package com.yh.springeasy.text;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/text")
public class TextController {

    @GetMapping("/one")
    public void testOne(@RequestBody(required = true) String args) {
        System.out.println(args);
    }

    public static void main(String[] args) {
      String test =  "  args=123,123 ";
      test=test.replaceAll(" ","").replaceAll("args=","");
      System.out.println(test);
    }
}
