package com.leo.timer.service;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: leo-zz
 * @Date: 2019/4/5 9:46
 */
@Component
public class QuartzDemoService {
    @Autowired
    JobDetail quartzJob;

    @Autowired
    Trigger quartzTrigger;

    public void runQuartzJob(){
        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(quartzJob, quartzTrigger);

            Thread.sleep(60*1000);

            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
