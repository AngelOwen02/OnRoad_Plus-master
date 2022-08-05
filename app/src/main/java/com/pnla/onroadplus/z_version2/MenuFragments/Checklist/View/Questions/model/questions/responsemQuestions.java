package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responsemQuestions {
    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<mquestions> data = null;

    public responsemQuestions(Integer responseCode, String message, List<mquestions> data) {
        super();
        this.responseCode = responseCode;
        this.message = message;
        this.data = data;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<mquestions> getData() {
        return data;
    }

    public void setData(List<mquestions> data) {
        this.data = data;
    }
}
