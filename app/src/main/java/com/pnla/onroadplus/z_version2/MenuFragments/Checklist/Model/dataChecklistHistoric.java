package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model;

import java.io.Serializable;
import java.util.List;

public class dataChecklistHistoric implements Serializable {

    private List<dataChecklist> fulChecklist= null;
    public dataChecklistHistoric(List<dataChecklist> fulChecklist) {
        this.fulChecklist = fulChecklist;
    }

    public List<dataChecklist> getFulChecklist() {
        return fulChecklist;
    }

    public void setFulChecklist(List<dataChecklist> fulChecklist) {
        this.fulChecklist = fulChecklist;
    }
}
