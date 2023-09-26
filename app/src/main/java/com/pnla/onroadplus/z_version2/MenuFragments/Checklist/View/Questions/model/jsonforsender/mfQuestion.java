package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.jsonforsender;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class mfQuestion {


    @SerializedName("cve_trip_mgm_question")
    @Expose
    private Integer cveTripMgmQuestion;
    @SerializedName("cve_trip_mgm_answer")
    @Expose
    private Integer cveTripMgmAnswer;



    @SerializedName("desc_trip_mgm_answer")
    private String openanswer=null;
    @SerializedName("observation")
    private String comments;
    public mfQuestion(Integer cveTripMgmQuestion, Integer cveTripMgmAnswer,String openanswer,String comments) {
        super();
        this.cveTripMgmQuestion = cveTripMgmQuestion;
        this.cveTripMgmAnswer = cveTripMgmAnswer;
        this.openanswer=openanswer;
        this.comments=comments;
    }

    public Integer getCveTripMgmQuestion() {
        return cveTripMgmQuestion;
    }

    public void setCveTripMgmQuestion(Integer cveTripMgmQuestion) {
        this.cveTripMgmQuestion = cveTripMgmQuestion;
    }

    public Integer getCveTripMgmAnswer() {
        return cveTripMgmAnswer;
    }

    public void setCveTripMgmAnswer(Integer cveTripMgmAnswer) {
        this.cveTripMgmAnswer = cveTripMgmAnswer;
    }
    public String openanswer() {
        return openanswer;
    }

    public void openanswer(String desc_trip_mgm_answer) {
        this.openanswer = desc_trip_mgm_answer;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
