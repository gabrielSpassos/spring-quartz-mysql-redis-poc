package com.gabrielspassos.poc.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduleConfig {

    static String UPDATE_CACHED_BANK_STATUS_CRON_SCHEDULE;

    @Value("${schedule.cron.jobs.update-cached-bank-status}")
    public void setUpdateCachedBankStatusCronSchedule(String updateCachedBankStatusCronSchedule) {
        UPDATE_CACHED_BANK_STATUS_CRON_SCHEDULE = updateCachedBankStatusCronSchedule;
    }
}

