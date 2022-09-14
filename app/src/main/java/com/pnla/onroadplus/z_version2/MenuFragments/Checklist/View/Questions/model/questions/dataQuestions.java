package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dataQuestions {

    @SerializedName("origin_adm")
    @Expose
    private Integer originAdm;
    @SerializedName("cve_trip_mgm_section")
    @Expose
    private Integer cveTripMgmSection;
    @SerializedName("desc_trip_mgm_section")
    @Expose
    private String descTripMgmSection;
    @SerializedName("questions")
    @Expose
    private List<mquestions> questions = null;

    public dataQuestions(Integer originAdm, Integer cveTripMgmSection, String descTripMgmSection, List<mquestions> questions) {
        super();
        this.originAdm = originAdm;
        this.cveTripMgmSection = cveTripMgmSection;
        this.descTripMgmSection = descTripMgmSection;
        this.questions = questions;
    }

    public Integer getOriginAdm() {
        return originAdm;
    }

    public void setOriginAdm(Integer originAdm) {
        this.originAdm = originAdm;
    }

    public Integer getCveTripMgmSection() {
        return cveTripMgmSection;
    }

    public void setCveTripMgmSection(Integer cveTripMgmSection) {
        this.cveTripMgmSection = cveTripMgmSection;
    }

    public String getDescTripMgmSection() {
        return descTripMgmSection;
    }

    public void setDescTripMgmSection(String descTripMgmSection) {
        this.descTripMgmSection = descTripMgmSection;
    }

    public List<mquestions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<mquestions> questions) {
        this.questions = questions;
    }

}
