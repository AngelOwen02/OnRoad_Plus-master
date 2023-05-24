package com.pnla.onroadplus.z_version2.MenuFragments.Login.model.loginV3;

import com.google.gson.annotations.SerializedName;

public class dataLoginV3 {
    @SerializedName("token")
    private String token;
    @SerializedName("cve_employee")
    private Integer cveEmployee;
    @SerializedName("employee_name")
    private String employeeName;
    @SerializedName("userImage")
    private String userImage;
    @SerializedName("email")
    private String email;
    @SerializedName("user_cve")
    private String userCve;
    @SerializedName("origin_adm")
    private Integer originAdm;

    public dataLoginV3(String token, Integer cveEmployee, String employeeName, String userImage, String email, String userCve, Integer originAdm) {
        super();
        this.token = token;
        this.cveEmployee = cveEmployee;
        this.employeeName = employeeName;
        this.userImage = userImage;
        this.email = email;
        this.userCve = userCve;
        this.originAdm = originAdm;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getCveEmployee() {
        return cveEmployee;
    }

    public void setCveEmployee(Integer cveEmployee) {
        this.cveEmployee = cveEmployee;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserCve() {
        return userCve;
    }

    public void setUserCve(String userCve) {
        this.userCve = userCve;
    }

    public Integer getOriginAdm() {
        return originAdm;
    }

    public void setOriginAdm(Integer originAdm) {
        this.originAdm = originAdm;
    }
}
