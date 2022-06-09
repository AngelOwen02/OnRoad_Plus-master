package com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.model;

import java.util.List;

public class MenuResponse {


    private int responseCode;
    private String message;
    private List<MenuData> data;

    /**
     * @param message
     * @param responseCode
     * @param data
     */
    public MenuResponse(int responseCode, String message, List<MenuData> data) {
        super();
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

    public List<MenuData> getData() {
        return data;
    }

    public void setData(List<MenuData> data) {
        this.data = data;
    }
}
