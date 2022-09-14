package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.fulldataquestions;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class numberSections  implements Serializable {
    @SerializedName("origin_adm")
    private Integer originAdm;
    @SerializedName("cveTrip_MgmSection")
    private Integer cveTripMgmSection;
    @SerializedName("cveTrip_MgmSection")
    private String descTripMgmSection;
    @SerializedName("cveTrip_MgmSection")
    private List<mQuestions> mquestuions=null;
    public numberSections(Integer originAdm,Integer cveTripMgmSection,String descTripMgmSection,List<mQuestions> mquestuions)
    {
        this.originAdm=originAdm;
        this.cveTripMgmSection=cveTripMgmSection;
        this.descTripMgmSection=descTripMgmSection;
        this.mquestuions=mquestuions;
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

    public List<mQuestions> getMquestuions() {
        return mquestuions;
    }

    public void setMquestuions(List<mQuestions> mquestuions) {
        this.mquestuions = mquestuions;
    }
}
