package com.gabrielspassos.poc.services;

import com.gabrielspassos.poc.entities.mysql.CronTriggersEntity;
import com.gabrielspassos.poc.repositories.mysql.CronTriggersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.Optional;

import static org.quartz.CronExpression.validateExpression;

@Service
public class ScheduleService {

    private final CronTriggersRepository cronTriggersRepository;

    @Autowired
    public ScheduleService(CronTriggersRepository cronTriggersRepository) {
        this.cronTriggersRepository = cronTriggersRepository;
    }

    public CronTriggersEntity updateScheduleTriggerCronExpression(String scheduleTriggerName, String newCronExpression) throws ParseException {
        validateExpression(newCronExpression);
        CronTriggersEntity cronTriggersEntity = getCronTriggerByName(scheduleTriggerName);
        cronTriggersEntity.setCronExpression(newCronExpression);
        return cronTriggersRepository.save(cronTriggersEntity);
    }

    private CronTriggersEntity getCronTriggerByName(String triggerName) {
        CronTriggersEntity cronTriggersEntity = cronTriggersRepository.findByTriggerName(triggerName);
        return Optional.ofNullable(cronTriggersEntity)
                .orElseThrow(() -> new EntityNotFoundException("Not found trigger with this name"));
    }
}
