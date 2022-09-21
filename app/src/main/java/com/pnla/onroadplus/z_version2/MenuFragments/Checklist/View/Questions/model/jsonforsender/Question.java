package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.jsonforsender;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {


    @SerializedName("cve_trip_mgm_question")
    @Expose
    private String cveTripMgmQuestion;
    @SerializedName("cve_trip_mgm_answer")
    @Expose
    private String cveTripMgmAnswer;

    public Question(String cveTripMgmQuestion, String cveTripMgmAnswer) {
        super();
        this.cveTripMgmQuestion = cveTripMgmQuestion;
        this.cveTripMgmAnswer = cveTripMgmAnswer;
    }

    public String getCveTripMgmQuestion() {
        return cveTripMgmQuestion;
    }

    public void setCveTripMgmQuestion(String cveTripMgmQuestion) {
        this.cveTripMgmQuestion = cveTripMgmQuestion;
    }

    public String getCveTripMgmAnswer() {
        return cveTripMgmAnswer;
    }

    public void setCveTripMgmAnswer(String cveTripMgmAnswer) {
        this.cveTripMgmAnswer = cveTripMgmAnswer;
    }

}
