package com.leo.timer;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 屏蔽前两个注释，默认使用quartz实现定时任务
 * @Author: leo-zz
 * @Date: 2019/4/2 13:13
 */
//@EnableScheduling
//@EnableAsync
@SpringBootApplication
public class TimerDemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(TimerDemoApplication.class,args);
    }
}
