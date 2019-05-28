package com.gabrielspassos.poc.entities.mysql;

import javax.persistence.Column;
import java.io.Serializable;

public class CronTriggersIds implements Serializable {

    @Column(name = "SCHED_NAME")
    private String scheduleName;
    @Column(name = "TRIGGER_NAME")
    private String triggerName;
    @Column(name = "TRIGGER_GROUP")
    private String triggerGroup;

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
}
