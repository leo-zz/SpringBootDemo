package com.leo.timer.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 使用Spring提供的定时器工具
 * 参考https://blog.csdn.net/wqh8522/article/details/79224290
 * cron：通过表达式来配置任务执行时间
 * fixedRate：定义一个按一定频率执行的定时任务
 * fixedDelay：定义一个按一定频率执行的定时任务，与上面不同的是，改属性可以配合initialDelay， 定义该任务延迟执行时间。
 * 执行的优先级，先fixedRate后fixedDelay，最后cron
 * @Author: leo-zz
 * @Date: 2019/4/3 12:46
 */
@Component
public class ScheduledService {

    /**
     *
     * 结果日志：
     * 使用fixedRate设置定时任务，每5秒执行一次。pool-1-thread-1 Wed Apr 03 13:12:01 CST 2019
     * 使用fixedDelay设置定时任务，每5秒执行一次。pool-1-thread-1 Wed Apr 03 13:12:04 CST 2019
     * 使用cron表达式设置定时任务，每5秒执行一次。pool-1-thread-1 Wed Apr 03 13:12:05 CST 2019
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduleTest1(){
        System.out.println("使用cron表达式设置定时任务，每5秒执行一次。"+Thread.currentThread().getName()+" "+new Date());
    }

    @Async
    @Scheduled(fixedRate = 5000)
    /**
     * @Async 注解表示该方法通过taskExecutor线程池执行。
     */
    public void scheduleTest2(){
        System.out.println("使用fixedRate设置定时任务，每5秒执行一次。"+Thread.currentThread().getName()+" "+new Date());
    }

    @Scheduled(fixedDelay = 5000,initialDelay = 3000)
    public void scheduleTest3(){
        System.out.println("使用fixedDelay设置定时任务，每5秒执行一次。"+Thread.currentThread().getName()+" "+new Date());
    }

}
