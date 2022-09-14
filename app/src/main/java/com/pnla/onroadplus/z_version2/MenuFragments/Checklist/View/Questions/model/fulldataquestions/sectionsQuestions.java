package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.fulldataquestions;

import com.google.gson.annotations.SerializedName;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections.dataSections;

import java.io.Serializable;
import java.util.List;

public class sectionsQuestions  implements Serializable {
    @SerializedName("list_numberSections")
    private List<numberSections> data = null;

    public sectionsQuestions(List<numberSections> data)
    {
        this.data=data;
    }
    public List<numberSections> getData() {
        return data;
    }

    public void setData(List<numberSections> data) {
        this.data = data;
    }


}
