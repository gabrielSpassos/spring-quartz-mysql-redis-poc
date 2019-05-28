package com.gabrielspassos.poc.controllers.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;

public class ScheduleTriggerRequest {

    @NotNull(message = "New Cron Expression must be informed")
    private String newCronExpression;

    public String getNewCronExpression() {
        return newCronExpression;
    }

    public void setNewCronExpression(String newCronExpression) {
        this.newCronExpression = newCronExpression;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
