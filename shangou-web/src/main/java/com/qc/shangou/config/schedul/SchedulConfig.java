package com.qc.shangou.config.schedul;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * Author quincey
 * Date 2020/5/29 11:17
 */
@Configuration
public class SchedulConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(8));// 给你一百个线程，这样就不会造成线程拥堵
    }
}
