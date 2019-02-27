package com.woody.iptest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

@RestController
@RequestMapping("/test")
public class IpTestController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public String getIp() {
        return getIpAddr(request);
    }

    private String getIpAddr(HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        String ip = request.getHeader("x-forwarded-for");
        builder.append("x-forwarded-for: ");
        builder.append(ip);

        ip = request.getHeader("Proxy-Client-IP");
        builder.append(", Proxy-Client-IP: ");
        builder.append(ip);

        ip = request.getHeader("WL-Proxy-Client-IP");
        builder.append(", WL-Proxy-Client-IP: ");
        builder.append(ip);

        ip = request.getRemoteAddr();
        if (ip.equals("127.0.0.1")) {
            //根据网卡取本机配置的IP
            InetAddress inet = null;
            try {
                inet = InetAddress.getLocalHost();
            } catch (Exception e) {
                e.printStackTrace();
            }
            assert inet != null;
            ip = inet.getHostAddress();
        }
        builder.append(", RemoteAddr: ");
        builder.append(ip);

        return builder.toString();
    }
}
