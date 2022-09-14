package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.presenter;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.dataQuestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.mquestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections.dataSections;

import java.util.List;

public interface questionPresenter {
    void getpSections(Integer checklistN);
    void getpQuestions(Integer dataSections);

    void setSections(List<dataSections> data);
    void setQuestions(List<dataQuestions> data);

    void showpDialog();
    void hidepDialog();
}
