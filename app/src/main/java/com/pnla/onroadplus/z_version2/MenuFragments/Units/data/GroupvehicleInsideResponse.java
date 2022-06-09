package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

import java.util.List;

public class GroupvehicleInsideResponse {

    private int responseCode;
    private String message;
    private List<GroupvehicleInsideData> data;


    public GroupvehicleInsideResponse(int responseCode, String message, List<GroupvehicleInsideData> data)
    {
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

    public List<GroupvehicleInsideData> getData() {
        return data;
    }

    public void setData(List<GroupvehicleInsideData> data) {
        this.data = data;
    }


}
