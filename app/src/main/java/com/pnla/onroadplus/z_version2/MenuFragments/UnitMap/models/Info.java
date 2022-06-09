package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Info {


    @SerializedName("copyrights")
    @Expose
    private List<String> copyrights = null;
    @SerializedName("took")
    @Expose
    private Integer took;

    public Info (List<String> copyrights,Integer took)
    {
        this.copyrights=copyrights;
        this.took=took;
    }

    public List<String> getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(List<String> copyrights) {
        this.copyrights = copyrights;
    }

    public Integer getTook() {
        return took;
    }

    public void setTook(Integer took) {
        this.took = took;
    }
}
