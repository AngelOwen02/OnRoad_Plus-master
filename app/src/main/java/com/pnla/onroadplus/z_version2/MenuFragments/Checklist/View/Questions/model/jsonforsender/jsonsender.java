package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.jsonforsender;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class jsonsender {


    @SerializedName("Questions")
    @Expose
    private List<mfQuestion> questions = null;

    public jsonsender(List<mfQuestion> questions) {
        super();
        this.questions = questions;
    }

    public List<mfQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<mfQuestion> questions) {
        this.questions = questions;
    }

}
