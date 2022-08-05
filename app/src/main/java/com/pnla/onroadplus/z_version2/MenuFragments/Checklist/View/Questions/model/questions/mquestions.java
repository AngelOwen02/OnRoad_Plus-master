package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class mquestions {


    @SerializedName("origin_adm")

    private Integer originAdm;
    @SerializedName("cve_trip_mgm_section")

    private Integer cveTripMgmSection;
    @SerializedName("cve_trip_mgm_question")

    private Integer cveTripMgmQuestion;
    @SerializedName("desc_trip_mgm_question")

    private String descTripMgmQuestion;
    @SerializedName("answers")

    private List<Answer> answers = null;

    public mquestions(Integer originAdm, Integer cveTripMgmSection, Integer cveTripMgmQuestion, String descTripMgmQuestion, List<Answer> answers) {
        super();
        this.originAdm = originAdm;
        this.cveTripMgmSection = cveTripMgmSection;
        this.cveTripMgmQuestion = cveTripMgmQuestion;
        this.descTripMgmQuestion = descTripMgmQuestion;
        this.answers = answers;
    }

    public Integer getOriginAdm() {
        return originAdm;
    }

    public void setOriginAdm(Integer originAdm) {
        this.originAdm = originAdm;
    }

    public Integer getCveTripMgmSection() {
        return cveTripMgmSection;
    }

    public void setCveTripMgmSection(Integer cveTripMgmSection) {
        this.cveTripMgmSection = cveTripMgmSection;
    }

    public Integer getCveTripMgmQuestion() {
        return cveTripMgmQuestion;
    }

    public void setCveTripMgmQuestion(Integer cveTripMgmQuestion) {
        this.cveTripMgmQuestion = cveTripMgmQuestion;
    }

    public String getDescTripMgmQuestion() {
        return descTripMgmQuestion;
    }

    public void setDescTripMgmQuestion(String descTripMgmQuestion) {
        this.descTripMgmQuestion = descTripMgmQuestion;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
