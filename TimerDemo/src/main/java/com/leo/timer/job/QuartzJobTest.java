package com.leo.timer.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 任务类
 * @Author: leo-zz
 * @Date: 2019/4/3 14:45
 */
public class QuartzJobTest extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("使用Quartz实现定时任务，每5秒执行一次。"+Thread.currentThread().getName()+" "+new Date());
    }
}
