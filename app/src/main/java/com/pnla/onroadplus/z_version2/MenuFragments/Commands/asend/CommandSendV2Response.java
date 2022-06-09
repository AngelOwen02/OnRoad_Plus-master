package com.pnla.onroadplus.z_version2.MenuFragments.Commands.asend;

public class CommandSendV2Response {

    private String message;
    private int responseCode;

    /**
     * @param message
     * @param responseCode
     */
    public CommandSendV2Response(String message, int responseCode) {
        super();
        this.message = message;
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

}
