package com.pnla.onroadplus.z_version2.MenuFragments.Login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class setAuditTrail {

    @SerializedName("auditTrail")
    @Expose
    private AuditTrail auditTrail;
    @SerializedName("token")
    @Expose
    private String token;
    public setAuditTrail( AuditTrail auditTrail,String token)
    {
        this.auditTrail=auditTrail;
        this.token=token;
    }

    public AuditTrail getAuditTrail() {
        return auditTrail;
    }

    public void setAuditTrail(AuditTrail auditTrail) {
        this.auditTrail = auditTrail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
