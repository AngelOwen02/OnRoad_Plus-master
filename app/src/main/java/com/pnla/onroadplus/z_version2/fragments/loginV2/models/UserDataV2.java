package com.pnla.onroadplus.z_version2.fragments.loginV2.models;

public class UserDataV2 {

    private String employee_name;
    private Boolean firstLogin;
    private String userImage;
    private String token;
    private String email;


    private String  user_cve;
    /**
     * @param token
     * @param userImage
     * @param firstLogin
     * @param employee_name
     */
    public UserDataV2(String employee_name, Boolean firstLogin, String userImage, String token, String email,String user_cve) {
        super();
        this.employee_name = employee_name;
        this.firstLogin = firstLogin;
        this.userImage = userImage;
        this.token = token;
        this.email = email;
        this.user_cve=user_cve;

    }

    public String getEmployeeName() {
        return employee_name;
    }

    public void setEmployeeName(String employee_name) {
        this.employee_name = employee_name;
    }

    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getUser_cve() {
        return user_cve;
    }

    public void setUser_cve(String user_cve) {
        this.user_cve = user_cve;
    }

}
