package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.view;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.dataQuestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.mquestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections.dataSections;

import java.util.List;

public interface questionView {
    void setSections(List<dataSections> data);
    void showDialog();
    void hideDialog();

    void setQuestions(List<dataQuestions> data);

    void successetCehcklist();
}
