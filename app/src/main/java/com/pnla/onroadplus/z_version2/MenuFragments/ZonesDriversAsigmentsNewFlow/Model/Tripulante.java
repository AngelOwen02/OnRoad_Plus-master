package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tripulante {

    @SerializedName("cve_tripulante")
    @Expose
    private Integer cveTripulante;
    @SerializedName("tripulante_name")
    @Expose
    private String tripulanteName;

    public Tripulante(Integer cveTripulante,String tripulanteName)
    {
        this.cveTripulante=cveTripulante;
        this.tripulanteName=tripulanteName;
    }

    public Integer getCveTripulante() {
        return cveTripulante;
    }

    public void setCveTripulante(Integer cveTripulante) {
        this.cveTripulante = cveTripulante;
    }

    public String getTripulanteName() {
        return tripulanteName;
    }

    public void setTripulanteName(String tripulanteName) {
        this.tripulanteName = tripulanteName;
    }

}
