package com.leo.timer.config;

import com.leo.timer.job.QuartzJobTest;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * SpringBoot整合Quartz完整实用例子--https://www.cnblogs.com/ealenxie/p/9134602.html
 * @Author: leo-zz
 * @Date: 2019/4/3 14:49
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail quartzJob(){
        return JobBuilder.newJob(QuartzJobTest.class).withIdentity("job1")
                .storeDurably().build();
    }


    /**
     *  触发器用来告诉调度程序作业什么时候触发.最常用的触发器是SimpleTrigger和CronTrigger
     */
    @Bean
    public Trigger quartzTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5).repeatForever();
        return TriggerBuilder.newTrigger().forJob(quartzJob())
                .withIdentity("job1").withSchedule(scheduleBuilder).build();
    }
}
