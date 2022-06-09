package com.pnla.onroadplus.z_version2.activities.online.presenters;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pnla.onroadplus.z_version2.activities.online.interactors.ActivityOnLineInteractorImpl;
import com.pnla.onroadplus.z_version2.activities.online.interfaces.ActivityOnLineInteractor;
import com.pnla.onroadplus.z_version2.activities.online.interfaces.ActivityOnLinePresenter;
import com.pnla.onroadplus.z_version2.activities.online.interfaces.ActivityOnLineView;
import com.pnla.onroadplus.z_version2.activities.online.menuDrawerV2.menuModelV2.MenuModelV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;

import java.util.HashMap;
import java.util.List;

public class ActivityOnLinePresenterImpl implements ActivityOnLinePresenter {

    private ActivityOnLineView view;
    private ActivityOnLineInteractor interactor;

    public ActivityOnLinePresenterImpl(Context context) {
        interactor = new ActivityOnLineInteractorImpl(this, context);
    }

    @Override
    public void setView(ActivityOnLineView view) {
        this.view = view;
    }

    @Override
    public void getUserDataPreferences(Context context) {
        if (view != null) {
            interactor.getUserDataPreferences(context);
        }
    }

    @Override
    public void getMenuIcon() {
        if (view != null) {
            interactor.getMenuIcon();
        }
    }

    @Override
    public void getDrawerMenu(Context context) {
        if (view != null) {
            interactor.getDrawerMenu(context);
        }
    }

    @Override
    public void validateDataToCloseSession(Context context) {
        if (view != null) {
            view.showLoader();
            interactor.validateDataToCloseSession(context);
        }
    }

    @Override
    public void findFragmentByName(String fragmentName, FragmentManager manager, FragmentTransaction transaction, Context context) {
        if (view != null) {
            interactor.findFragmentByName(fragmentName, manager, transaction, context);
        }
    }

    @Override
    public void validateVehcleList(List<VehicleV2> vehicles, FragmentManager manager, Context context) {
        if (view != null) {
            interactor.validateVehcleList(vehicles, manager, context);
        }
    }

    @Override
    public void saveGroups(List<GroupV2> groups, Context context) {
        if (view != null) {
            interactor.saveGroups(groups, context);
        }
    }

    @Override
    public void getGroupsFromAPI(Context context) {
        if (view != null) {
            view.showLoader();
            interactor.getGroupsFromAPI(context);
        }
    }

    @Override
    public void validateGroupVehicles(List<GroupV2> groups, FragmentManager manager, Context context) {
        if (view != null) {
            interactor.validateGroupVehicles(groups, manager, context);
        }
    }

    @Override
    public void validateVehiclesUserClickedDrawerMenu(String groupName, int cveGroup, List<GroupV2> groups, FragmentManager manager, Context context) {
        if (view != null) {
            interactor.validateVehiclesUserClickedDrawerMenu(groupName, cveGroup, groups, manager, context);
        }
    }

    @Override
    public void setCloseDrawerMenu() {
        if (view != null) {
            view.closeDrawerMenu();
        }
    }

    @Override
    public void successCloseSession() {
        if (view != null) {
            view.hideLoader();
            view.successCloseSession();
        }
    }

    @Override
    public void setUserEmployeeName(String employeeName) {
        if (view != null) {
            view.setUserEmployeeName(employeeName);
        }
    }

    @Override
    public void setEmployeeImage(String employeeImage) {
        if (view != null) {
            view.setEmployeeImage(employeeImage);
        }
    }

    @Override
    public void setDefaultEmployeeImage() {
        if (view != null) {
            view.setDefaultEmployeeImage();
        }
    }

    @Override
    public void setMenuIconToView(Drawable icon) {
        if (view != null) {
            view.setMenuIcon(icon);
        }
    }

    @Override
    public void setDrawerMenu(List<MenuModelV2> headerList, HashMap<MenuModelV2, List<MenuModelV2>> childList) {
        if (view != null) {
            view.setDrawerMenu(headerList, childList);
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
    public void setFragmentNotifications(Fragment fragment) {
        if (view != null) {
            view.showFragmentNotifications(fragment);
        }
    }

    @Override
    public void setFragmentContact(Fragment fragment) {
        if (view != null) {
            view.showFragmentContact(fragment);
        }
    }

    @Override
    public void setFragmentConfiguration(Fragment fragment) {
        if (view != null) {
            view.showFragmentConfiguration(fragment);
        }
    }

    @Override
    public void setFragmentHelp(Fragment fragment) {
        if (view != null) {
            view.showFragmentHelp(fragment);
        }
    }

    @Override
    public void setFragmentMap(Fragment fragmentMap) {
        if (view != null) {
            view.showFragmentMap(fragmentMap);
        }
    }

    @Override
    public void setFragmentVehicles(Fragment fragment) {
        if (view != null) {
            view.showFragmentVehicles(fragment);
        }
    }

    @Override
    public void setGroupsList(List<GroupV2> groups) {
        if (view != null) {
            view.hideLoader();
            view.setGroupsList(groups);
        }
    }

    @Override
    public void hideLoaderFromInteractor() {
        if (view != null) {
            view.hideLoader();
        }
    }

}
