package com.pnla.onroadplus.z_version2.fragments.commands.models.get;

public class CommandsV2Response {

    private int responseCode;
    private String message;
    private CommandV2Data data;

    /**
     * @param message
     * @param responseCode
     * @param data
     */
    public CommandsV2Response(int responseCode, String message, CommandV2Data data) {
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

    public CommandV2Data getData() {
        return data;
    }

    public void setData(CommandV2Data data) {
        this.data = data;
    }


}
