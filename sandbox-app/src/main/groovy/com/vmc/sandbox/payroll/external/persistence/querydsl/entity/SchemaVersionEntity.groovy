package com.vmc.sandbox.payroll.external.persistence.querydsl.entity;

import javax.annotation.Generated;

/**
 * SchemaVersionEntity is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class SchemaVersionEntity {

    private Integer checksum;

    private String description;

    private Integer executionTime;

    private String installedBy;

    private java.sql.Timestamp installedOn;

    private Integer installedRank;

    private String script;

    private Boolean success;

    private String type;

    private String version;

    public Integer getChecksum() {
        return checksum;
    }

    public void setChecksum(Integer checksum) {
        this.checksum = checksum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Integer executionTime) {
        this.executionTime = executionTime;
    }

    public String getInstalledBy() {
        return installedBy;
    }

    public void setInstalledBy(String installedBy) {
        this.installedBy = installedBy;
    }

    public java.sql.Timestamp getInstalledOn() {
        return installedOn;
    }

    public void setInstalledOn(java.sql.Timestamp installedOn) {
        this.installedOn = installedOn;
    }

    public Integer getInstalledRank() {
        return installedRank;
    }

    public void setInstalledRank(Integer installedRank) {
        this.installedRank = installedRank;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}

