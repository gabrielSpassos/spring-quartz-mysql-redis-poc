package com.gabrielspassos.poc.configs;

import com.gabrielspassos.poc.jobs.UpdateBankStatusCacheJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateBankStatusCacheJobConfig {

    @Bean
    public JobDetail updateBankStatusCacheJobDetails() {
        return JobBuilder
                .newJob(UpdateBankStatusCacheJob.class)
                .withIdentity("updateBankStatusCacheJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger updateBankStatusCacheJobTrigger() {
        return TriggerBuilder
                .newTrigger()
                .forJob(updateBankStatusCacheJobDetails())
                .withIdentity("updateBankStatusCacheTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(ScheduleConfig.UPDATE_CACHED_BANK_STATUS_CRON_SCHEDULE))
                .build();
    }
}
