package com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2;

public class GroupV2Response {

    private int responseCode;
    private String message;
    private GroupsV2Data data;

    /**
     * @param message
     * @param responseCode
     * @param data
     */
    public GroupV2Response(int responseCode, String message, GroupsV2Data data) {
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

    public GroupsV2Data getData() {
        return data;
    }

    public void setData(GroupsV2Data data) {
        this.data = data;
    }


}
