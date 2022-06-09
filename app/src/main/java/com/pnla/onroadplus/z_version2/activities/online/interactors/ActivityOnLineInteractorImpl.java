package com.pnla.onroadplus.z_version2.activities.online.interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.activities.online.interfaces.ActivityOnLineInteractor;
import com.pnla.onroadplus.z_version2.activities.online.interfaces.ActivityOnLinePresenter;
import com.pnla.onroadplus.z_version2.activities.online.interfaces.ActivityOnLineServices;
import com.pnla.onroadplus.z_version2.activities.online.menuDrawerV2.menuModelV2.MenuModelV2;
import com.pnla.onroadplus.z_version2.activities.online.models.CloseSessionV2Request;
import com.pnla.onroadplus.z_version2.activities.online.models.CloseSessionV2Response;
import com.pnla.onroadplus.z_version2.activities.online.utils.ActivityOnLineUtils;
import com.pnla.onroadplus.z_version2.fragments.contactV2.view.FragmentContactV2;
import com.pnla.onroadplus.z_version2.fragments.helpV2.FragmentHelpV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.view.FragmentMapV2;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.view.FragmentNotificationsV2;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2_map.FragmentNotificationV2Map;
import com.pnla.onroadplus.z_version2.fragments.settingsV2.view.FragmentSettingsV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2Request;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2Response;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupsV2Data;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.view.FragmentVehiclesV2;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmGroup;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmVehicle;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ActivityOnLineInteractorImpl implements ActivityOnLineInteractor {

    private ActivityOnLinePresenter presenter;
    private Context context;
    private ActivityOnLineServices services;
    private Retrofit retrofitClient;

    public ActivityOnLineInteractorImpl(ActivityOnLinePresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        services = retrofitClient.create(ActivityOnLineServices.class);
    }

    @Override
    public void getUserDataPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String employeeName = preferences.getString(GeneralConstantsV2.EMPLOYEE_NAME_PREFERENCES, null);
        String employeeImage = preferences.getString(GeneralConstantsV2.URL_USER_IMAGE_PREFERENCES, null);
        presenter.setUserEmployeeName(employeeName);
        if (employeeImage != null && !employeeImage.equals(GeneralConstantsV2.NO_IMAGE)) {
            presenter.setEmployeeImage(employeeImage);
        } else {
            presenter.setDefaultEmployeeImage();
        }
    }

    @Override
    public void getMenuIcon() {
        Drawable drawable = context.getResources().getDrawable(R.drawable.icon_menu);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Drawable newdrawable = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 80, 80, true));
        presenter.setMenuIconToView(newdrawable);
    }

    @Override
    public void getDrawerMenu(Context ctx) {
        HashMap<MenuModelV2, List<MenuModelV2>> childList = new HashMap<>();
        List<MenuModelV2> headerList = new ArrayList<>();
        MenuModelV2 menuModel = null;

        /**Consultamos la db para verificar si el usuario cuenta con grupos en db, en caso contrario se pinta menu por default*/
        SharedPreferences preferences = ctx.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String user = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
        List<GroupV2> realmGroups = RealmGroup.getAllGroupsByUser(user);

        if (realmGroups != null && realmGroups.size() > 0) {
            /**     En este caso si hay grupos en la db*/
            menuModel = new MenuModelV2(R.drawable.icon_groups, ctx.getString(R.string.textItemGroups), true, true, GeneralConstantsV2.WITHOUT_CVE);
            headerList.add(menuModel);
            List<MenuModelV2> childModelsList0 = new ArrayList<>();
            MenuModelV2 childModel00;
            childModel00 = new MenuModelV2(-1, ctx.getString(R.string.textItemAllGroups), false, false, GeneralConstantsV2.USER_CLICKED_ALL);
            childModelsList0.add(childModel00);
            for (int i = 0; i < realmGroups.size(); i++) {
                realmGroups.get(i).setPositionItem(i);//
                int cveGroup = realmGroups.get(i).getCve_vehicle_group();
                String groupName = realmGroups.get(i).getVehicle_group();
                childModelsList0.add(new MenuModelV2(-1, groupName, false, false, cveGroup));
            }
            if (menuModel.isHasChildren()) {
                childList.put(menuModel, childModelsList0);
            }
        } else {
            /**En este caso no hay grupos en la db*/
            menuModel = new MenuModelV2(R.drawable.icon_groups, ctx.getString(R.string.textItemGroups), true, true, GeneralConstantsV2.WITHOUT_CVE);
            headerList.add(menuModel);
            List<MenuModelV2> childModelsList0 = new ArrayList<>();
            MenuModelV2 childModel00;
            //se agrega la opción seleccionar grupo
            childModel00 = new MenuModelV2(-1, ctx.getString(R.string.textItemSelectGroup), false, false, GeneralConstantsV2.USER_CLICKED_SELECT_GROUP);
            childModelsList0.add(childModel00);
            if (menuModel.isHasChildren()) {
                childList.put(menuModel, childModelsList0);
            }
        }

        /**     en la 1ra versión el dasboard tiene que estar oculto Dashboar */
        //menuModel = new MenuModel(R.drawable.icon_dashboard, getString(R.string.textItemDashboard), false, true, -1);
        //headerList.add(menuModel);

        //if (!menuModel.isHasChildren()) {
        //    childList.put(menuModel, null);
        //}

        /**     en la 1ra versión el dasboard tiene que estar oculto Unidades       */
        //menuModel = new MenuModel(R.drawable.icon_cars, getString(R.string.textItemCars), false, true, -1);
        //headerList.add(menuModel);

        //if (!menuModel.isHasChildren()) {
        //    childList.put(menuModel, null);
        //}

        /**     Notificaciones      */
        menuModel = new MenuModelV2(R.drawable.icon_notifications, ctx.getString(R.string.textItemNotifications), false, true, GeneralConstantsV2.WITHOUT_CVE);
        headerList.add(menuModel);
        if (!menuModel.isHasChildren()) {
            childList.put(menuModel, null);
        }

        /**     Contacto        */
        menuModel = new MenuModelV2(R.drawable.icon_contact, ctx.getString(R.string.textItemContact), false, true, GeneralConstantsV2.WITHOUT_CVE);
        headerList.add(menuModel);
        if (!menuModel.isHasChildren()) {
            childList.put(menuModel, null);
        }

        /**     Configuración       */
        menuModel = new MenuModelV2(R.drawable.icon_settings, ctx.getString(R.string.textItemSettings), false, true, GeneralConstantsV2.WITHOUT_CVE);
        headerList.add(menuModel);
        if (!menuModel.isHasChildren()) {
            childList.put(menuModel, null);
        }

        /**     Ayuda       */
        menuModel = new MenuModelV2(R.drawable.icon_help, ctx.getString(R.string.textItemHelp), false, true, GeneralConstantsV2.WITHOUT_CVE);
        headerList.add(menuModel);
        if (!menuModel.isHasChildren()) {
            childList.put(menuModel, null);
        }

        presenter.setDrawerMenu(headerList, childList);
    }

    @Override
    public void validateDataToCloseSession(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        String resultValidation = ActivityOnLineUtils.validateTokenCloseSession(token);
        if (resultValidation.equals(GeneralConstantsV2.SUCCESS)) {
            startCloseSessionRequest(token);
        } else {
            presenter.setMessageToView("Ocurrió un error, no se pudo cerrar sesión");
        }
    }

    @Override
    public void findFragmentByName(String fragmentName, FragmentManager manager, FragmentTransaction transaction, Context context) {
        transaction = manager.beginTransaction();
        Fragment fragment = null;

        if (fragmentName.equals(context.getString(R.string.textItemDashboard))) {
        } else if (fragmentName.equals(context.getString(R.string.textItemCars))) {
            /**     si ya nos econtramos en la pantalla de los vehiculos, solo cerramos el drawer   */
            Fragment currentFragment = manager.findFragmentByTag(FragmentVehiclesV2.TAG);
            if (currentFragment == null) {
                removeAllFragments(manager);
                fragment = new FragmentVehiclesV2();
                presenter.setFragmentVehicles(fragment);
                presenter.setCloseDrawerMenu();
            } else {
                presenter.setCloseDrawerMenu();
            }
        } else if (fragmentName.equals(context.getString(R.string.textItemNotifications))) {
            /**     Si nos encontramos en el mapa donde se muestra la notificacion, se realiza un pop para quitar ese fragment y luego removerlo    */
            Fragment fragment1 = manager.findFragmentByTag(FragmentNotificationV2Map.TAG);
            if (fragment1 != null) {
                FragmentTransaction transaction1 = manager.beginTransaction();
                transaction1.remove(fragment1).commit();
                transaction1 = null;
                presenter.setCloseDrawerMenu();
                manager.popBackStack();
            } else {
                /**     si ya nos encontramos en la pantalla NOTIFICACIONES, solo ceramos el drawer     */
                Fragment currentFragment = manager.findFragmentByTag(FragmentNotificationsV2.TAG);
                if (currentFragment == null) {
                    removeAllFragments(manager);
                    fragment = new FragmentNotificationsV2();
                    presenter.setCloseDrawerMenu();
                    presenter.setFragmentNotifications(fragment);
                } else {
                    presenter.setCloseDrawerMenu();
                }
                currentFragment = null;
            }
        } else if (fragmentName.equals(context.getString(R.string.textItemContact))) {
            /**     Si ya nos encontramos en la pantalla contacto, solo cerramos el drawer menu     */
            Fragment currentFragment = manager.findFragmentByTag(FragmentContactV2.TAG);
            if (currentFragment == null) {
                removeAllFragments(manager);
                fragment = new FragmentContactV2();
                presenter.setFragmentContact(fragment);
                presenter.setCloseDrawerMenu();
            } else {
                presenter.setCloseDrawerMenu();
            }
            currentFragment = null;
        } else if (fragmentName.equals(context.getString(R.string.textItemSettings))) {
            /**     si ya nos encontramos en la pantalla settings, solo ceramos el drawer   */
            Fragment currentFragment = manager.findFragmentByTag(FragmentSettingsV2.TAG);
            if (currentFragment == null) {
                removeAllFragments(manager);
                fragment = new FragmentSettingsV2();
                presenter.setFragmentConfiguration(fragment);
                presenter.setCloseDrawerMenu();
            } else {
                presenter.setCloseDrawerMenu();
            }
            currentFragment = null;
        } else if (fragmentName.equals(context.getString(R.string.textItemHelp))) {
            removeAllFragments(manager);
            fragment = new FragmentHelpV2();
            presenter.setCloseDrawerMenu();
            presenter.setFragmentHelp(fragment);
        }
    }

    @Override
    public void validateVehcleList(List<VehicleV2> vehicles, FragmentManager manager, Context context) {
        /** Si el usuario esta en el fragmentVehicles enviamos los vehiculos que seleccionó al fragment del mapa.   */
        if (ActivityOnLineUtils.userIsInFragmentVehiclesV2(manager)) {
            if (ActivityOnLineUtils.areThereSelectedVehicles(vehicles)) {
                RealmVehicle.saveVehicles(vehicles, context);
                removeAllFragments(manager);
                Fragment fragmentMap = new FragmentMapV2();
                presenter.setFragmentMap(fragmentMap);
            } else {
                presenter.setMessageToView("Tienes que seleccionar al menos 1 unidad para visualizar en el mapa.");
            }
        }
        /** En otro caso enviamos los vehiculos que estan en realm al fragment del mapa, si no hay vehiculos en realm, mandamos a fragmentVehicles*/
        else {
            removeAllFragments(manager);
            List<VehicleV2> vehiclesSavedInRealm;
            SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
            String userName = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
            /**Obtenemos los vehiculos de realm y si no hay vehiculos tenemos que mandar a "FragmentVehiclesV2"*/
            vehiclesSavedInRealm = RealmVehicle.getAllVehiclesByUserName(userName);
            if (vehiclesSavedInRealm != null && vehiclesSavedInRealm.size() > 0) {
                String jsonStringVehiclesList = ActivityOnLineUtils.getJsonStringFromVehicleList(vehiclesSavedInRealm);
                Fragment fragment = new FragmentMapV2();
                Bundle bundle = new Bundle();
                bundle.putString(GeneralConstantsV2.JSON_VEHICLES_LIST, jsonStringVehiclesList);
                fragment.setArguments(bundle);
                presenter.setFragmentMap(fragment);
            } else {
                Fragment fragment = new FragmentVehiclesV2();
                presenter.setMessageToView("Selecciona vehículos para visualizarlos en el mapa");
                presenter.setFragmentVehicles(fragment);
            }
        }
    }

    @Override
    public void validateGroupVehicles(List<GroupV2> groups, FragmentManager manager, Context context) {
        if (ActivityOnLineUtils.areThereSelectedGroups(groups)) {
            /**     Obtenemos los vehiculos de cada grupo para enviarlos al mapa    */
            List<VehicleV2> vehicles = ActivityOnLineUtils.getVehiclesFromSelectedGroups(groups);
            if (vehicles != null && vehicles.size() > 0) {
                removeAllFragments(manager);
                String jsonStringVehiclesList = ActivityOnLineUtils.getJsonStringFromVehicleList(vehicles);
                Fragment fragment = new FragmentMapV2();
                Bundle bundle = new Bundle();
                bundle.putString(GeneralConstantsV2.JSON_VEHICLES_LIST, jsonStringVehiclesList);
                fragment.setArguments(bundle);
                presenter.setFragmentMap(fragment);
            } else {
                presenter.setMessageToView("Es necesario seleccionar un grupo con unidades para poder visualizar en el mapa.");
            }
        } else {
            presenter.setMessageToView("Tienes que seleccionar un grupo para mostrar sus unidades en el mapa.");
        }
    }

    @Override
    public void validateVehiclesUserClickedDrawerMenu(String groupName, int cveGroup, List<GroupV2> groups, FragmentManager manager, Context context) {
        /** Si el usuario seleccióna la opcion "seleccionar grupo" mandamos a la pantalla de vehículos a la seccion de grupos
         *  Si el usuario seleccionó la opcion "todos", se envían todas las unidades seleccionadas al mapa.
         *  Si el usuario seleccionó un grupo valido enviamos solo las unidades de ese grupo al mapa    */
        if (groupName.equals(context.getString(R.string.textItemSelectGroup))) {  //selecionar grupo --> pantalla vehiculos
            removeAllFragments(manager);
            Fragment fragment = new FragmentVehiclesV2();
            presenter.setFragmentVehicles(fragment);
            presenter.setCloseDrawerMenu();
        } else if (groupName.equals(context.getString(R.string.textItemAllGroups))) { // todos --> pantalla mapa
            //validateGroupVehicles(groups, manager, context);
            setVehclesFromGroups(groups, manager);
        } else {
            /**     Buscamos al grupo por nombre y nos traemos sus vehiculos    */
            List<VehicleV2> vehicles = ActivityOnLineUtils.findVehiclesByGroupName(groupName, groups);  //grupo specific --> map
            if (vehicles != null && vehicles.size() > 0) {
                String jsonStringVehiclesList = ActivityOnLineUtils.getJsonStringFromVehicleList(vehicles);
                Fragment fragment = new FragmentMapV2();
                Bundle bundle = new Bundle();
                bundle.putString(GeneralConstantsV2.JSON_VEHICLES_LIST, jsonStringVehiclesList);
                fragment.setArguments(bundle);
                presenter.setFragmentMap(fragment);
            } else {
                presenter.setMessageToView("No se encontraron vehículos");
            }
        }
    }

    @Override
    public void saveGroups(List<GroupV2> groups, Context context) {
        RealmGroup.saveGroups(groups, context);
        getDrawerMenu(context);
    }

    @Override
    public void getGroupsFromAPI(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        startGroupsRequest(token, context);
    }

    private void setVehclesFromGroups(List<GroupV2> groups, FragmentManager manager) {
        /**     Obtenemos los vehiculos de cada grupo para enviarlos al mapa    */
        List<VehicleV2> vehicles = ActivityOnLineUtils.getVehiclesFromSelectedGroups(groups);
        if (vehicles != null && vehicles.size() > 0) {
            removeAllFragments(manager);
            String jsonStringVehiclesList = ActivityOnLineUtils.getJsonStringFromVehicleList(vehicles);
            Fragment fragment = new FragmentMapV2();
            Bundle bundle = new Bundle();
            bundle.putString(GeneralConstantsV2.JSON_VEHICLES_LIST, jsonStringVehiclesList);
            fragment.setArguments(bundle);
            presenter.setCloseDrawerMenu();
            presenter.setFragmentMap(fragment);
        } else {
            presenter.setMessageToView("No se encontraron vehículos.");
        }
    }

    private void startGroupsRequest(String token, final Context context) {
        GroupV2Request request = new GroupV2Request(token);
        services.getGroups(request).enqueue(new Callback<GroupV2Response>() {
            @Override
            public void onResponse(Call<GroupV2Response> call, Response<GroupV2Response> response) {
                validateGroupsCode(response, context);
            }

            @Override
            public void onFailure(Call<GroupV2Response> call, Throwable t) {
                //presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
            }
        });
    }

    private void validateGroupsCode(Response<GroupV2Response> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getGroupsData(response, context);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }

    private void getGroupsData(Response<GroupV2Response> response, Context context) {
        GroupV2Response groupV2Response = response.body();
        if (groupV2Response != null) {
            int responseCode = groupV2Response.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                GroupsV2Data data = groupV2Response.getData();
                if (data != null) {
                    RealmList<GroupV2> groups = data.getGroups();
                    if (groups != null && groups.size() > 0) {
                        List<GroupV2> groupV2List = ActivityOnLineUtils.getGroupsList(groups);
                        presenter.setGroupsList(groupV2List);
                    } else {
                        presenter.hideLoaderFromInteractor();
                        presenter.setGroupsList(null);
                    }
                } else {
                    presenter.setMessageToView(context.getString(R.string.textEmptyCarsResponse));
                    presenter.setGroupsList(null);
                }
            } else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {
                presenter.hideLoaderFromInteractor();
                presenter.setGroupsList(null);
            } else {
                presenter.setMessageToView(groupV2Response.getMessage());
                presenter.setGroupsList(null);
            }
        } else {
            presenter.setMessageToView(context.getString(R.string.textEmptyResponse));
            presenter.setGroupsList(null);
        }
    }

    private void removeAllFragments(FragmentManager manager) {
        int count = manager.getBackStackEntryCount();
        for (int i = 0; i < count; i++) {
            manager.popBackStack();
        }

        Fragment fragment1 = manager.findFragmentByTag(FragmentVehiclesV2.TAG);
        if (fragment1 != null) {
            manager.beginTransaction().remove(fragment1).commit();
        }

        fragment1 = manager.findFragmentByTag(FragmentHelpV2.TAG);
        if (fragment1 != null) {
            manager.beginTransaction().remove(fragment1).commit();
        }

        fragment1 = manager.findFragmentByTag(FragmentMapV2.TAG);
        if (fragment1 != null) {
            manager.beginTransaction().remove(fragment1).commit();
        }

        fragment1 = manager.findFragmentByTag(FragmentNotificationsV2.TAG);
        if (fragment1 != null) {
            manager.beginTransaction().remove(fragment1).commit();
        }

        fragment1 = manager.findFragmentByTag(FragmentNotificationV2Map.TAG);
        if (fragment1 != null) {
            manager.beginTransaction().remove(fragment1).commit();
        }

        fragment1 = manager.findFragmentByTag(FragmentSettingsV2.TAG);
        if (fragment1 != null) {
            manager.beginTransaction().remove(fragment1).commit();
        }
    }

    private void startCloseSessionRequest(String token) {
        CloseSessionV2Request request = new CloseSessionV2Request(token);
        services.closeSessionService(request).enqueue(new Callback<CloseSessionV2Response>() {
            @Override
            public void onResponse(Call<CloseSessionV2Response> call, Response<CloseSessionV2Response> response) {
                getCloseSessionResponse(response);
            }

            @Override
            public void onFailure(Call<CloseSessionV2Response> call, Throwable t) {
                presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
            }
        });
    }

    private void getCloseSessionResponse(Response<CloseSessionV2Response> response) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            closeSessionSuccess(response);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }

    private void closeSessionSuccess(Response<CloseSessionV2Response> response) {
        CloseSessionV2Response closeSessionV2Response = response.body();
        if (closeSessionV2Response != null) {
            int responseCode = closeSessionV2Response.getResponseCode();
            if (responseCode == 102 || responseCode == 104) {
                SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(GeneralConstantsV2.CLOSE_SESSION_PREFERENCES, "YES");
                editor.commit();
                presenter.successCloseSession();
            } else {
                presenter.setMessageToView("Ocurrió un error, no se pudo cerrar sesión");
            }
        } else {
            presenter.setMessageToView("Ocurrió un error, no se pudo cerrar sesión");
        }
    }

}