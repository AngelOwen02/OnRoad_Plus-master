package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.jsonforsender;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class jsonsender {


    @SerializedName("Questions")
    @Expose
    private List<Question> questions = null;

    public jsonsender(List<Question> questions) {
        super();
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
