package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/hello")
    public String getHello(){
        return "hello";
    }

    @GetMapping("another")
    public String getAnother(){
        return "another";
    }

    @GetMapping("/dashboard")
    public String Dashboard(){return "welcome to dashboard!";}

}
