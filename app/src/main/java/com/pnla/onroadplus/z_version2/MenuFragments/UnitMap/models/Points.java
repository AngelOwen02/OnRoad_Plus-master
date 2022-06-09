package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Points {


    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("coordinates")
    @Expose
    private List<List<Float>> coordinates = null;

    public Points(String type,List<List<Float>> coordinates)
    {
        this.type=type;
        this.coordinates=coordinates;

    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<List<Float>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<Float>> coordinates) {
        this.coordinates = coordinates;
    }
}
