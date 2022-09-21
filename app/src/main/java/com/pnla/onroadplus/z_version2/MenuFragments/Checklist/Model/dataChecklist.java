package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model;

import java.io.Serializable;

public class dataChecklist  implements Serializable {
    private int origin;
    private int questioId;
    private int section;
    private int answerPos;
    private String foto;
    private Integer cveTripMgmQuestion;
    private Integer score;
    private Integer cveTripMgmAnswer;


    public dataChecklist(int origin, int questioId, int section, int answerPos, String foto,Integer cveTripMgmQuestion,Integer score,Integer cveTripMgmAnswer) {
        this.origin = origin;
        this.questioId = questioId;
        this.section = section;
        this.answerPos = answerPos;
        this.foto = foto;
        this.cveTripMgmQuestion=cveTripMgmQuestion;
        this.score=score;
        this.cveTripMgmAnswer=cveTripMgmAnswer;
    }



    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public int getAnswerId() {
        return questioId;
    }

    public void setAnswerId(int answerId) {
        this.questioId = answerId;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public int getAnswerPos() {
        return answerPos;
    }

    public void setAnswerPos(int answerPos) {
        this.answerPos = answerPos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getCveTripMgmQuestion() {
        return cveTripMgmQuestion;
    }

    public void setCveTripMgmQuestion(Integer cveTripMgmQuestion) {
        this.cveTripMgmQuestion = cveTripMgmQuestion;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCveTripMgmAnswer() {
        return cveTripMgmAnswer;
    }

    public void setCveTripMgmAnswer(Integer cveTripMgmAnswer) {
        this.cveTripMgmAnswer = cveTripMgmAnswer;
    }
}
