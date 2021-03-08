package com.admereselvyn.mic.api.models.user_model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payment implements Serializable {

    @SerializedName("payment_id")
    @Expose
    private List<Object> paymentId = null;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("emi")
    @Expose
    private Boolean emi;
    @SerializedName("isHalfPaid")
    @Expose
    private Boolean isHalfPaid;
    @SerializedName("isTotalPaid")
    @Expose
    private Boolean isTotalPaid;

    public List<Object> getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(List<Object> paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Boolean getEmi() {
        return emi;
    }

    public void setEmi(Boolean emi) {
        this.emi = emi;
    }

    public Boolean getIsHalfPaid() {
        return isHalfPaid;
    }

    public void setIsHalfPaid(Boolean isHalfPaid) {
        this.isHalfPaid = isHalfPaid;
    }

    public Boolean getIsTotalPaid() {
        return isTotalPaid;
    }

    public void setIsTotalPaid(Boolean isTotalPaid) {
        this.isTotalPaid = isTotalPaid;
    }

}
