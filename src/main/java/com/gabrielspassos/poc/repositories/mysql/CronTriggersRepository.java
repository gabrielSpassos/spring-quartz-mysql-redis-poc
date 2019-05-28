package com.gabrielspassos.poc.repositories.mysql;

import com.gabrielspassos.poc.entities.mysql.CronTriggersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CronTriggersRepository extends JpaRepository<CronTriggersEntity, String> {

    CronTriggersEntity findByTriggerName(String triggerName);
}
