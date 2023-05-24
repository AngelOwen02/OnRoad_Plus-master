package com.pnla.onroadplus.z_version2.MenuFragments.Login.model.loginV3.origin;

import com.google.gson.annotations.SerializedName;

public class dataOrigin {


    @SerializedName("origin_adm")
    private Integer origin_adm;


    public dataOrigin(Integer origin_adm) {
        super();
        this.origin_adm = origin_adm;
    }

    public Integer getOrigin_adm() {
        return origin_adm;
    }

    public void setOrigin_adm(Integer origin_adm) {
        this.origin_adm = origin_adm;
    }

}
