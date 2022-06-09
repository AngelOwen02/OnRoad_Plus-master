package com.pnla.onroadplus.z_version2.MenuFragments.Contact.presenter;

import android.content.Context;
import android.graphics.Typeface;

import com.pnla.onroadplus.z_version2.MenuFragments.Contact.interactor.ContactInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.Contact.interactor.ContactInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Contact.view.ContactView;

public class ContactPresenterImpl implements ContactPresenter {

    Context context;
    ContactView view;
    ContactInteractor interactor;

    public ContactPresenterImpl(Context context){
        this.context = context;
        interactor  = new ContactInteractorImpl(this,context);
    }


    @Override
    public void setView(ContactView view) {
        this.view = view;
    }

    @Override
    public void initThemeSettings() {
        if (view != null) {

            interactor.getFonts();
            interactor.getUserEmail(context);
            interactor.getSupportEmail();
        }
    }

    @Override
    public void setFonts(Typeface robotoLight, Typeface robotoRegular, Typeface robotoMedium) {
        if (view != null) {
            view.setFonts(robotoLight, robotoRegular, robotoMedium);
        }
    }

    @Override
    public void sendEmail() {
        if (view != null){
            interactor.getSendConfirmationDialog();
        }
    }

    @Override
    public void getUserEmail(Context context) {
        if (view != null) {
            interactor.getUserEmail(context);
        }
    }

    @Override
    public void validateUserDataToSend(String to, String subject, String message, Context context) {
        if (view != null) {
            view.showLoader();
            interactor.validateUserDataToSend(to, subject, message, context);
        }
    }

    @Override
    public void setMessageToView(String message) {
        if (view != null) {
            view.hideLoader();
            view.showMessage(message);
        }
    }

    @Override
    public void setUserEmail(String email) {
        if (view != null) {
            view.setUserEmail(email);
        }
    }

    @Override
    public void setSupportEmail(String email) {
        if (view != null) {
            view.setSupportEmail(email);
        }
    }

    @Override
    public void sessionExpired(String message) {
        if (view != null) {
            view.hideLoader();
            view.sessionExpired(message);
        }
    }

    @Override
    public void successContactService() {
        if (view != null) {
            view.hideLoader();
            view.successContactService();
        }
    }

    @Override
    public void getSupportEmail() {
        if (view != null) {
            interactor.getSupportEmail();
        }
    }


    @Override
    public void getSendConfirmationDialog() {

    }
}
