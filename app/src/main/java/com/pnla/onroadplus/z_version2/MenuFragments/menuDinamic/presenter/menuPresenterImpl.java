package com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.interactor.menuInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.interactor.menuInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view.menuView;

import java.util.List;

public class menuPresenterImpl  implements menuPresenter{


    private Context context;
    private menuInteractor interactor;
    private menuView view;

    public menuPresenterImpl(menuView view,Context context)
    {
        this.context=context;
        this.interactor=new menuInteractorImpl(this,context);
        this.view=view;
    }


    @Override
    public void itemsMenu() {
        if (view != null) {
            interactor.getMenuitems();
        }
    }

    @Override
    public void setErrorToView(String error) {
        if (view != null) {
            //view.hideLoader();
            view.showError(error);
        }
    }

    @Override
    public void nameslistitems(List<String> items) {
        if (view != null) {
            // view.hideLoader();
            view.listItems(items);
        }
    }

    @Override
    public void closeAppSessionExpired() {
        if (view != null) {
            //    view.hideLoader();
            view.closeAppSessionExpired();
        }
    }
}
