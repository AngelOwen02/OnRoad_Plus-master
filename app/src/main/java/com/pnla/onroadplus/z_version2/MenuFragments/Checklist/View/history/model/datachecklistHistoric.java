package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class datachecklistHistoric {

    @SerializedName("historic")

    private List<Historic> historic = null;

    public datachecklistHistoric(List<Historic> historic) {
        super();
        this.historic = historic;
    }

    public List<Historic> getHistoric() {
        return historic;
    }

    public void setHistoric(List<Historic> historic) {
        this.historic = historic;
    }
}
