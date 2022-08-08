package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions;

import com.google.gson.annotations.SerializedName;

public class requestmQuestions {

    @SerializedName("cve_section")

    private Integer cveSection;
    @SerializedName("token")

    private String token;


    public requestmQuestions(Integer cveSection, String token) {
        super();
        this.cveSection = cveSection;
        this.token = token;
    }

    public Integer getCveSection() {
        return cveSection;
    }

    public void setCveSection(Integer cveSection) {
        this.cveSection = cveSection;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
