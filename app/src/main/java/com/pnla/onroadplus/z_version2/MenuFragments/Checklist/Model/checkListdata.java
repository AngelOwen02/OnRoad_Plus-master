package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model;

import com.google.gson.annotations.SerializedName;

public class checkListdata {


    @SerializedName("origin_adm")
     private Integer originAdm;
    @SerializedName("cve_trip_mgm_section")
    private Integer cveTripMgmSection;
    @SerializedName("desc_trip_mgm_section")
    private String descTripMgmSection;

    public checkListdata(Integer originAdm, Integer cveTripMgmSection, String descTripMgmSection) {
        super();
        this.originAdm = originAdm;
        this.cveTripMgmSection = cveTripMgmSection;
        this.descTripMgmSection = descTripMgmSection;
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
}
