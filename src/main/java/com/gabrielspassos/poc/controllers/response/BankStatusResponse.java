package com.gabrielspassos.poc.controllers.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BankStatusResponse {

    private String id;
    private String status;
    private Boolean isBankActive;

    public Boolean getIsBankActive() {
        return isBankActive;
    }

    public void setIsBankActive(Boolean bankActive) {
        this.isBankActive = bankActive;
    }

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
