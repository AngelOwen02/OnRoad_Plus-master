package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections;

import com.google.gson.annotations.SerializedName;

public class requestmySections {
    @SerializedName("token")
    private String token;
    @SerializedName("cve_trip_mgm_checklist")
    private Integer checklistN;

    public requestmySections(Integer checklistN,String token) {
        super();
        this.checklistN=checklistN;
        this.token = token;
    }

    public Integer getChecklistN() {
        return checklistN;
    }

    public void setChecklistN(Integer checklistN) {
        this.checklistN = checklistN;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
