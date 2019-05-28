package com.gabrielspassos.poc.jobs;

import com.gabrielspassos.poc.entities.redis.BankStatusCacheEntity;
import com.gabrielspassos.poc.services.BankService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@DisallowConcurrentExecution
@Component
public class UpdateBankStatusCacheJob implements Job {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final BankService  bankService;

    @Autowired
    public UpdateBankStatusCacheJob(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            String id = context.getJobDetail().getKey().getName();
            logger.info("Executing update bank status cache job, id {}", id);
            BankStatusCacheEntity bankStatusCacheEntity = bankService.updateCachedBankStatus();
            logger.info("Updated cached entity {}", bankStatusCacheEntity);
        } catch (Exception e) {
            logger.error("Error at update bank status cache job", e);
            throw new JobExecutionException(e);
        }
    }
}
