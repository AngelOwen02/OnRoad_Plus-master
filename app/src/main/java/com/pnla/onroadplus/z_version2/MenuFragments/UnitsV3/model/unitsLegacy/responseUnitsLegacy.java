package com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitsLegacy;

import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.UnitData;

public class responseUnitsLegacy {
    private int responseCode;
    private String message;
    private dataUnitsLegacy data;

    public responseUnitsLegacy(int responseCode, String message, dataUnitsLegacy data) {
        this.responseCode = responseCode;
        this.message = message;
        this.data = data;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public dataUnitsLegacy getData() {
        return data;
    }

    public void setData(dataUnitsLegacy data) {
        this.data = data;
    }
}
