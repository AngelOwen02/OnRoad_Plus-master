package com.pnla.onroadplus.z_version2.MenuFragments.Commands.asend;

public class CommandSendV2Request {

    private String token;
    private String msg_routine;
    private String unique_id;

    /**
     * @param token
     * @param msg_routine
     * @param unique_id
     */
    public CommandSendV2Request(String msg_routine,String token,  String unique_id) {
        super();
        this.token = token;
        this.msg_routine = msg_routine;
        this.unique_id = unique_id;
      //  this.cve_vehicle = cve_vehicle;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) { this.token = token;    }

    public String getCve_device() {
        return msg_routine;
    }

    public void setCve_device(String cve_device) {
        this.msg_routine = msg_routine;
    }

    public String getCve_routine() {
        return unique_id;
    }

    public void setCve_routine(String cve_routine) {
        this.unique_id = unique_id;
    }




}
