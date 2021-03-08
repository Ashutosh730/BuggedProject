package com.admereselvyn.mic.api.models.user_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class  AuditionVideo implements Serializable {

    @SerializedName("isZoneDivided")
    @Expose
    private Boolean isZoneDivided;
    @SerializedName("zone_no")
    @Expose
    private Integer zoneNo;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("auditionId")
    @Expose
    private String auditionId;
    @SerializedName("auditionVideoId")
    @Expose
    private String auditionVideoId;

    public Boolean getIsZoneDivided() {
        return isZoneDivided;
    }

    public void setIsZoneDivided(Boolean isZoneDivided) {
        this.isZoneDivided = isZoneDivided;
    }

    public Integer getZoneNo() {
        return zoneNo;
    }

    public void setZoneNo(Integer zoneNo) {
        this.zoneNo = zoneNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuditionId() {
        return auditionId;
    }

    public void setAuditionId(String auditionId) {
        this.auditionId = auditionId;
    }

    public String getAuditionVideoId() {
        return auditionVideoId;
    }

    public void setAuditionVideoId(String auditionVideoId) {
        this.auditionVideoId = auditionVideoId;
    }

}