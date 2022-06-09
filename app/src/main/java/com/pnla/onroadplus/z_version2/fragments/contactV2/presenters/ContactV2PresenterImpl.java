package com.pnla.onroadplus.z_version2.fragments.contactV2.presenters;

import android.content.Context;

import com.pnla.onroadplus.z_version2.fragments.contactV2.interactors.ContactV2InteractorImpl;
import com.pnla.onroadplus.z_version2.fragments.contactV2.interfaces.ContactV2Interactor;
import com.pnla.onroadplus.z_version2.fragments.contactV2.interfaces.ContactV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.contactV2.interfaces.ContactV2View;

public class ContactV2PresenterImpl implements ContactV2Presenter {

    private ContactV2View view;
    private ContactV2Interactor interactor;

    public ContactV2PresenterImpl() {
        interactor = new ContactV2InteractorImpl(this);
    }

    @Override
    public void setView(ContactV2View view) {
        this.view = view;
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

}
