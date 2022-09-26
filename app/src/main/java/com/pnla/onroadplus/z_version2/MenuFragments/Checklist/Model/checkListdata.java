package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model;

import com.google.gson.annotations.SerializedName;

public class checkListdata {

    @SerializedName("origin_adm")
    private Integer originAdm;
    @SerializedName("cve_trip_mgm_checklist")
    private Integer cveTripMgmSection;
    @SerializedName("desc_trip_mgm_checklist")
    private String descTripMgmSection;
    @SerializedName("approver")
    private boolean aprobador;
    @SerializedName("email")
    private String email;

    public checkListdata(Integer originAdm, Integer cveTripMgmSection, String descTripMgmSection,boolean aprobador,String email) {
        super();
        this.originAdm = originAdm;
        this.cveTripMgmSection = cveTripMgmSection;
        this.descTripMgmSection = descTripMgmSection;
        this.aprobador=aprobador;
        this.email=email;
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

    public boolean isAprobador() {
        return aprobador;
    }

    public void setAprobador(boolean aprobador) {
        this.aprobador = aprobador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
