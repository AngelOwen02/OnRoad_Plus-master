package com.pnla.onroadplus.z_version2.MenuFragments.Login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuditTrail {


    @SerializedName("module_name")
    @Expose
    private String moduleName;
    @SerializedName("movement_type")
    @Expose
    private String movementType;
    @SerializedName("object_name")
    @Expose
    private String objectName;
    public AuditTrail(String moduleName,String movementType,String objectName)
    {
        this.moduleName=moduleName;
        this.movementType=movementType;
        this.objectName=objectName;
    }


    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
