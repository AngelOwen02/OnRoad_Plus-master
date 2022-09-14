package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.fulldataquestions;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class mQuestions  implements Serializable {
    @SerializedName("origin_adm")
    private Integer originAdm;
    @SerializedName("cveTripMgmSection")
    private Integer cveTripMgmSection;
    @SerializedName("cveTripMgmQuestion")
    private Integer cveTripMgmQuestion;
    @SerializedName("descTripMgmQuestion")
    private String descTripMgmQuestion;
    @SerializedName("mAnswersList")
    private List<mAnswers> mAnswersList=null;

    public mQuestions (Integer originAdm,Integer cveTripMgmSection,Integer cveTripMgmQuestion,String descTripMgmQuestion,List<mAnswers> mAnswersList)
    {
        this.originAdm=originAdm;
        this.cveTripMgmSection=cveTripMgmSection;
        this.cveTripMgmQuestion=cveTripMgmQuestion;
        this.descTripMgmQuestion=descTripMgmQuestion;
        this.mAnswersList=mAnswersList;
    }
    public List<mAnswers> getmAnswersList() {
        return mAnswersList;
    }

    public void setmAnswersList(List<mAnswers> mAnswersList) {
        this.mAnswersList = mAnswersList;
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
}
