package com.pnla.onroadplus.z_version2.fragments.contactV2.models;

public class ContactV2Request {

    private String message;
    private String subject;
    private String to;
    private String token;

    /**
     * @param to
     * @param message
     * @param token
     * @param subject
     */
    public ContactV2Request(String message, String subject, String to, String token) {
        super();
        this.message = message;
        this.subject = subject;
        this.to = to;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
