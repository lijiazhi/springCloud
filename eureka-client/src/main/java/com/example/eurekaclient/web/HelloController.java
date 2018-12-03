package com.example.eurekaclient.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${server.port}")
    private String port;
    @Value("${url}")
    private String datUrl;

    @RequestMapping("/hello")
    public String home(@RequestParam(value = "name", defaultValue = "visitor") String name) {
        return "hello " + name + " ,i am from port:" + port;
    }

    @RequestMapping(value = "/hi")
    public String hi(){
        return datUrl;
    }

}
