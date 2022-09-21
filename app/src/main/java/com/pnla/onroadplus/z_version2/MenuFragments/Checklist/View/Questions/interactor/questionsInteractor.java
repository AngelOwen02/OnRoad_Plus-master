package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.interactor;

public interface questionsInteractor {
    void getiSections(Integer checklistN);
    void getQuestions(Integer dataSections);

    void sendfullCheckList(int checkl, boolean aproved);
}
