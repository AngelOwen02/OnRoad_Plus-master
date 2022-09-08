package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.model;

import com.google.gson.annotations.SerializedName;

public class Historic {


    @SerializedName("date")
    private String date;
    @SerializedName("economicNumber")
    private String economicNumber;
    @SerializedName("status")
    private String status;


    public Historic(String date, String economicNumber, String status) {
        super();
        this.date = date;
        this.economicNumber = economicNumber;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEconomicNumber() {
        return economicNumber;
    }

    public void setEconomicNumber(String economicNumber) {
        this.economicNumber = economicNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
