package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.ChecklistQuestions;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.dataChecklist;

import java.util.List;

public class listdataChecklist {

    private List<List<dataChecklist>> data=null;

    public listdataChecklist(List<List<dataChecklist>> data) {
        this.data = data;
    }

    public List<List<dataChecklist>> getData() {
        return data;
    }

    public void setData(List<List<dataChecklist>> data) {
        this.data = data;
    }


}
