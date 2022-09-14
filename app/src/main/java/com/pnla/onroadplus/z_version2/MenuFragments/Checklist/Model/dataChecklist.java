package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model;

import java.io.Serializable;

public class dataChecklist  implements Serializable {
    private int origin;
    private int questioId;
    private int section;
    private int answerPos;
    private String foto;

    public dataChecklist(int origin, int questioId, int section, int answerPos, String foto) {
        this.origin = origin;
        this.questioId = questioId;
        this.section = section;
        this.answerPos = answerPos;
        this.foto = foto;
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
}
