package com.example.PW14.java.controllers;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private MBeanServer mBeanServer;
    @Autowired
    private ObjectName taskedServiceName;

    @GetMapping("/home")
    public String welcome() throws javax.management.InstanceNotFoundException, javax.management.MBeanException, javax.management.ReflectionException {
        mBeanServer.invoke(taskedServiceName, "execute", null, null);
        return "home";
    }
}
