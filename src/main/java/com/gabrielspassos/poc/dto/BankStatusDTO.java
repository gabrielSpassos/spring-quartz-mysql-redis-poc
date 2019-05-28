package com.gabrielspassos.poc.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BankStatusDTO {

    private String id;
    private String status;
    private Boolean isBankActive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsBankActive() {
        return isBankActive;
    }

    public void setIsBankActive(Boolean bankActive) {
        isBankActive = bankActive;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
