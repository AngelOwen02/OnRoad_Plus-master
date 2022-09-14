package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.fulldataquestions;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class mAnswers  implements Serializable {

    @SerializedName("origin_adm")
    private Integer originAdm;
    @SerializedName("origin_adm")
    private Integer cveTripMgmSection;
    @SerializedName("origin_adm")
    private Integer cveTripMgmQuestion;
    @SerializedName("origin_adm")
    private Integer cveTripMgmAnswer;
    @SerializedName("origin_adm")
    private String descTripMgmAnswer;
    @SerializedName("origin_adm")
    private Integer tripMgmAnswerValue;
    @SerializedName("object_Type")
    private Integer objectType;

    public mAnswers(Integer originAdm,Integer cveTripMgmSection,Integer cveTripMgmQuestion,Integer cveTripMgmAnswer,String descTripMgmAnswer,Integer tripMgmAnswerValue,Integer objectType)
    {
        this.originAdm=originAdm;
        this.cveTripMgmSection=cveTripMgmSection;
        this.cveTripMgmQuestion=cveTripMgmQuestion;
        this.cveTripMgmAnswer=cveTripMgmAnswer;
        this.descTripMgmAnswer=descTripMgmAnswer;
        this.tripMgmAnswerValue=tripMgmAnswerValue;
        this.objectType=objectType;
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

    public Integer getCveTripMgmAnswer() {
        return cveTripMgmAnswer;
    }

    public void setCveTripMgmAnswer(Integer cveTripMgmAnswer) {
        this.cveTripMgmAnswer = cveTripMgmAnswer;
    }

    public String getDescTripMgmAnswer() {
        return descTripMgmAnswer;
    }

    public void setDescTripMgmAnswer(String descTripMgmAnswer) {
        this.descTripMgmAnswer = descTripMgmAnswer;
    }

    public Integer getTripMgmAnswerValue() {
        return tripMgmAnswerValue;
    }

    public void setTripMgmAnswerValue(Integer tripMgmAnswerValue) {
        this.tripMgmAnswerValue = tripMgmAnswerValue;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }



}
