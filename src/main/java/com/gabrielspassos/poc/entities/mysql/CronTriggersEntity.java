package com.gabrielspassos.poc.entities.mysql;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity(name="QRTZ_CRON_TRIGGERS")
@IdClass(CronTriggersIds.class)
public class CronTriggersEntity {

    @Id
    @Column(name = "SCHED_NAME")
    private String scheduleName;
    @Id
    @Column(name = "TRIGGER_NAME")
    private String triggerName;
    @Id
    @Column(name = "TRIGGER_GROUP")
    private String triggerGroup;
    @Column(name = "CRON_EXPRESSION")
    private String cronExpression;
    @Column(name = "TIME_ZONE_ID")
    private String timeZoneId;

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
