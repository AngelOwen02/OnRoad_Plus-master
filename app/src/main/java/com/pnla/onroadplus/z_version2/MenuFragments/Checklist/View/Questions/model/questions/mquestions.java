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
    @SerializedName("instructions")
    private String instructions;
    @SerializedName("required_evidence")
    private boolean required_evidence;
    @SerializedName("required_observations")
    private Boolean required_observations;
    public mquestions(Integer originAdm, Integer cveTripMgmSection, Integer cveTripMgmQuestion, String descTripMgmQuestion, List<Answer> answers,String instructions,boolean required_evidence,Boolean required_observations) {
        super();
        this.originAdm = originAdm;
        this.cveTripMgmSection = cveTripMgmSection;
        this.cveTripMgmQuestion = cveTripMgmQuestion;
        this.descTripMgmQuestion = descTripMgmQuestion;
        this.answers = answers;
        this.instructions=instructions;
        this.required_evidence=required_evidence;
        this.required_observations=required_observations;
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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = (instructions != null) ? instructions : "";
    }

    public boolean isRequired_evidence() {
        return required_evidence;
    }

    public void setRequired_evidence(boolean required_evidence) {
        this.required_evidence = required_evidence;
    }

    public Boolean getRequired_observations() {
        return required_observations != null ? required_observations : false;
    }

    public void setRequired_observations(Boolean required_observations) {
        this.required_observations = required_observations;
    }
}
