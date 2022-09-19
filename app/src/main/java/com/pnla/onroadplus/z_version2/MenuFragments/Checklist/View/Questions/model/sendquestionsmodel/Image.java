package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sendquestionsmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("cve_trip_mgm_question")
    @Expose
    private Integer cveTripMgmQuestion;
    @SerializedName("image")
    @Expose
    private String image;

    public Image(Integer cveTripMgmQuestion, String image) {
        super();
        this.cveTripMgmQuestion = cveTripMgmQuestion;
        this.image = image;
    }

    public Integer getCveTripMgmQuestion() {
        return cveTripMgmQuestion;
    }

    public void setCveTripMgmQuestion(Integer cveTripMgmQuestion) {
        this.cveTripMgmQuestion = cveTripMgmQuestion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
