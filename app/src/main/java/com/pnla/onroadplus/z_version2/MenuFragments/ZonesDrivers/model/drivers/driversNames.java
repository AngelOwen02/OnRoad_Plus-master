package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class driversNames {

    @SerializedName("origin_adm")
    @Expose
    private String originAdm;
    @SerializedName("cve_employee")
    @Expose
    private String cveEmployee;
    @SerializedName("employee_internal_cve")
    @Expose
    private String employeeInternalCve;
    @SerializedName("employee_name")
    @Expose
    private String employeeName;
    @SerializedName("employee_email")
    @Expose
    private String employeeEmail;

    public driversNames(String originAdm,String cveEmployee,String employeeInternalCve,String employeeName, String employeeEmail)
    {
     this.originAdm=originAdm;
     this.cveEmployee=cveEmployee;
     this.employeeInternalCve=employeeInternalCve;
     this.employeeName=employeeName;
     this.employeeEmail=employeeEmail;
    }



    public String getOriginAdm() {
        return originAdm;
    }

    public void setOriginAdm(String originAdm) {
        this.originAdm = originAdm;
    }

    public String getCveEmployee() {
        return cveEmployee;
    }

    public void setCveEmployee(String cveEmployee) {
        this.cveEmployee = cveEmployee;
    }

    public String getEmployeeInternalCve() {
        return employeeInternalCve;
    }

    public void setEmployeeInternalCve(String employeeInternalCve) {
        this.employeeInternalCve = employeeInternalCve;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }


}
