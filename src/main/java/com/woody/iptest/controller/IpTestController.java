package com.woody.iptest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class IpTestController {

    @GetMapping
    public String getIp() {
        return "abc";
    }
}
