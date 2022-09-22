package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.interactor.questionsInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.interactor.questionsInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.dataQuestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections.dataSections;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.view.questionView;

import java.util.List;

public class questionsPresenterImpl implements  questionPresenter{
    private questionView view;
    private Context context;
    private questionsInteractor interactor;

    public questionsPresenterImpl(questionView view,Context context)
    {
        this.view=view;
        this.context=context;
        interactor=new questionsInteractorImpl(this,context);
    }

    @Override
    public void getpSections(Integer checklistN) {
        if(view!=null)
        {
            interactor.getiSections(checklistN);
        }
    }

    @Override
    public void getpQuestions(Integer dataSections) {
        if(view!=null)
        {
            interactor.getQuestions(dataSections);
        }
    }

    @Override
    public void setSections(List<dataSections> data) {
        if(view!=null)
        {
            view.setSections(data);
        }
    }

    @Override
    public void setQuestions(List<dataQuestions> data) {
        if(view!=null)
        {
            view.setQuestions(data);
        }
    }

    @Override
    public void showpDialog() {
        if(view!=null)
        {
            view.showDialog();
        }
    }

    @Override
    public void hidepDialog() {
        if(view!=null)
        {
            view.hideDialog();
        }
    }

    @Override
    public void sendfullchecklist(int checkl, boolean aproved) {
        if(view!=null) {
        interactor.sendfullCheckList(checkl,aproved);
        }
    }

    @Override
    public void gotoChecklistAgain(String valueSemaforo,int finalscore) {
        if(view!=null) {
            view.successetCehcklist(valueSemaforo,finalscore);
        }
    }
}
