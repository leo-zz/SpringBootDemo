package com.leo.timer.controller;

import com.leo.timer.service.QuartzDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: leo-zz
 * @Date: 2019/4/5 10:14
 */
@RestController
public class QuartzController {

    @Autowired
    QuartzDemoService demoService;

    @RequestMapping("/begin")
    public void runQuartzJob(){
        demoService.runQuartzJob();
    }
}
