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

    public mfQuestion(Integer cveTripMgmQuestion, Integer cveTripMgmAnswer) {
        super();
        this.cveTripMgmQuestion = cveTripMgmQuestion;
        this.cveTripMgmAnswer = cveTripMgmAnswer;
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

}
