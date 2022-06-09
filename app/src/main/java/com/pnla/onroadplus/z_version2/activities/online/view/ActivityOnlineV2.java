package com.pnla.onroadplus.z_version2.activities.online.view;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.activities.online.interfaces.ActivityOnLinePresenter;
import com.pnla.onroadplus.z_version2.activities.online.interfaces.ActivityOnLineView;
import com.pnla.onroadplus.z_version2.activities.online.menuDrawerV2.menuAdapterV2.ExpandableListAdapterV2;
import com.pnla.onroadplus.z_version2.activities.online.menuDrawerV2.menuModelV2.MenuModelV2;
import com.pnla.onroadplus.z_version2.activities.online.presenters.ActivityOnLinePresenterImpl;
import com.pnla.onroadplus.z_version2.fragments.commands.interfaces.FragmentCommandV2Data;
import com.pnla.onroadplus.z_version2.fragments.contactV2.interfaces.FragmentContactV2Data;
import com.pnla.onroadplus.z_version2.fragments.contactV2.view.FragmentContactV2;
import com.pnla.onroadplus.z_version2.fragments.helpV2.FragmentHelpV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.interfaces.MapV2Data;
import com.pnla.onroadplus.z_version2.fragments.mapV2.view.FragmentMapV2;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.interfaces.NotificationsV2SetData;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.view.FragmentNotificationsV2;
import com.pnla.onroadplus.z_version2.fragments.settingsV2.interfaces.SettingsV2Data;
import com.pnla.onroadplus.z_version2.fragments.settingsV2.view.FragmentSettingsV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.interfaces.FragmentVehiclesV2Data;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.view.FragmentVehiclesV2;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmConfig;
import com.phoenix2.top_driver_ui.tracking_app.DialogOkMessageTracking;
import com.phoenix2.top_driver_ui.tracking_app.DialogTrackingLoader;

import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityOnlineV2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SettingsV2Data, View.OnClickListener, ActivityOnLineView, DialogOkMessageTracking.OnTrackingAppSendCommandListener, FragmentVehiclesV2Data, FragmentContactV2Data, FragmentCommandV2Data, MapV2Data, FragmentHelpV2.OnHelpV2Listener, NotificationsV2SetData {
    private ActivityOnLinePresenter presenter;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    public static View viewM;
    private View userInfoHeader;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    // Loader //
    private DialogTrackingLoader loader;
    // MenuItem Toolbar //
    private static MenuItem itemMenuMap;
    private static MenuItem itemMenuVehicles;
    private static MenuItem itemMenuRoute;
    private static MenuItem itemMenuBack;
    // Android Components //
    private TextView txvUserName, txvCloseSession;
    private CircleImageView imgvUserImage;
    private ImageView imgvWithoutUserImage;
    private DialogOkMessageTracking dialogOkMessageTracking;
    // Menu //
    private ExpandableListView expandableListView;
    private ExpandableListAdapterV2 expandableListAdapter;
    // Listas //
    private List<VehicleV2> vehicles;
    private List<GroupV2> groups;
    /**
     * Esta variable se activa dentro del "onCreate" de cada uno de estos fragments ( FragmentContactV2,FragmentHelpV2,FragmentSettingsV2),
     * el fragment que puede cambiar esta variable a false es "FragmentVehiclesV2" cada que el usuario preiona el botton "Grupos" :)
     */
    public static boolean isVisibleVehiclesList = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_v2);

        initActivityOnLine();
        setFonts();
    }

    private void initActivityOnLine() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RealmConfig.initRealm(getApplicationContext());
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        expandableListView = findViewById(R.id.expandableListView);
        viewM = findViewById(R.id.mainOnlineView);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        drawer.setScrimColor(Color.TRANSPARENT);
        navigationView.setNavigationItemSelectedListener(this);
        userInfoHeader = navigationView.getHeaderView(0);
        txvUserName = userInfoHeader.findViewById(R.id.txvUserName);
        imgvUserImage = userInfoHeader.findViewById(R.id.imgvUserImage);
        imgvWithoutUserImage = userInfoHeader.findViewById(R.id.imgvWithoutUserImage);
        txvCloseSession = findViewById(R.id.txvCloseSession);
        txvCloseSession.setOnClickListener(this);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        getSupportActionBar().setTitle("");
        presenter = new ActivityOnLinePresenterImpl(getApplicationContext());
        presenter.setView(this);
        presenter.getUserDataPreferences(getApplicationContext());
        setTypefaceToolbarTitle();
        changeMenuIcon();
        presenter.getMenuIcon();
        presenter.getDrawerMenu(getApplicationContext());
        presenter.getGroupsFromAPI(getApplicationContext());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int count = getSupportFragmentManager().getBackStackEntryCount();
            if (count > 1) {
                super.onBackPressed();
            } else {
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_items, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        itemMenuMap = menu.findItem(R.id.itemMenuMap);
        itemMenuVehicles = menu.findItem(R.id.itemMenuFilter);
        itemMenuRoute = menu.findItem(R.id.itemMenuRoute);
        itemMenuBack = menu.findItem(R.id.itemMenuBack);
        itemMenuVehicles.setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemMenuBack:
                backToFragmentMapV2();
                break;
            case R.id.itemMenuMap:      /**     Icono del mapa                                  */
                validateVehicles();
                break;
            case R.id.itemMenuFilter:   /**     Icono para mandar al fragment de vehiculos      */
                presenter.findFragmentByName(getString(R.string.textItemCars), manager, transaction, getApplicationContext());
                break;
            case R.id.itemMenuRoute:    /**     Icono para localizar unidad                     */
                openVehicleLocationDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {// Handle the camera action
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_tools) {
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txvCloseSession:
                showDialogCloseSession();
                break;
        }
    }

    @Override
    public void showLoader() {
        loader = new DialogTrackingLoader();
        loader.show(getSupportFragmentManager(), DialogTrackingLoader.TAG);
    }

    @Override
    public void hideLoader() {
        if (loader != null && loader.isVisible()) {
            loader.dismiss();
            loader = null;
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUserEmployeeName(String employeeName) {
        txvUserName.setText(employeeName);
    }

    @Override
    public void setEmployeeImage(String employeeImage) {
        //String newUrl = employeeImage.replace("https", "http");    http://35.194.83.10:8080/images/profile/718/9856/rostro.jpg
        //   https:www.cerberusenlinea.com/images/profile/718/9856/rostro.jpg      https:www.cerberusenlinea.com
        String newUrl=employeeImage.replace("https:www.cerberusenlinea.com","http://35.194.83.10:8080");
        Glide.with(ActivityOnlineV2.this).load(newUrl).into(imgvUserImage);
        //Picasso.get().load(employeeImage).into(imgvUserImage);
    }

    @Override
    public void setDefaultEmployeeImage() {
        imgvWithoutUserImage.setVisibility(View.VISIBLE);
        Glide.with(ActivityOnlineV2.this).load(R.drawable.without_image).into(imgvWithoutUserImage);
    }

    @Override
    public void setMenuIcon(Drawable icon) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(icon);
        }
    }

    @Override
    public void setDrawerMenu(final List<MenuModelV2> headerList, final HashMap<MenuModelV2, List<MenuModelV2>> childList) {
        Typeface latoRegularTypeface = Typeface.createFromAsset(getAssets(), "fonts/Lato-Regular.ttf");
        Typeface latoBoldTypeface = Typeface.createFromAsset(getAssets(), "fonts/Lato-Bold.ttf");
        expandableListAdapter = new ExpandableListAdapterV2(this, headerList, childList, latoRegularTypeface, latoBoldTypeface);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (headerList.get(groupPosition).isGroup()) {
                    if (!headerList.get(groupPosition).isHasChildren()) {
                        FragmentManager manager = getSupportFragmentManager();
                        FragmentTransaction transaction = null;
                        presenter.findFragmentByName(headerList.get(groupPosition).getMenuName(), manager, transaction, getApplicationContext());
                    }
                }
                return false;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModelV2 model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    if (model.getMenuName().length() > 0) {
                        presenter.validateVehiclesUserClickedDrawerMenu(model.getMenuName(), model.getCveGroup(), groups, getSupportFragmentManager(), getApplicationContext());
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void closeDrawerMenu() {
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void successCloseSession() {
        dialogOkMessageTracking.dismiss();
        dialogOkMessageTracking = null;
        finish();
    }

    @Override
    public void showFragmentNotifications(Fragment fragment) {
        transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentNotificationsV2.TAG);
        transaction.replace(R.id.containerFragmentsOnLineV2, fragment, FragmentNotificationsV2.TAG).commit();
        showMapItemMenu();
    }

    @Override
    public void showFragmentContact(Fragment fragment) {
        transaction = manager.beginTransaction();
        transaction.replace(R.id.containerFragmentsOnLineV2, fragment, FragmentContactV2.TAG).commit();
        showMapItemMenu();
    }

    @Override
    public void showFragmentConfiguration(Fragment fragment) {
        transaction = manager.beginTransaction();
        transaction.replace(R.id.containerFragmentsOnLineV2, fragment, FragmentSettingsV2.TAG).commit();
        showMapItemMenu();
    }

    @Override
    public void showFragmentHelp(Fragment fragment) {
        toolbar.setVisibility(View.GONE);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        transaction = manager.beginTransaction();
        transaction.replace(R.id.containerFragmentsOnLineV2, fragment, FragmentHelpV2.TAG).commit();
    }

    @Override
    public void showFragmentMap(Fragment fragment) {
        transaction = manager.beginTransaction();
        transaction.replace(R.id.containerFragmentsOnLineV2, fragment, FragmentMapV2.TAG).commit();
        showVehiclesItemMenu();
    }

    @Override
    public void showFragmentVehicles(Fragment fragment) {
        transaction = manager.beginTransaction();
        transaction.replace(R.id.containerFragmentsOnLineV2, fragment, FragmentVehiclesV2.TAG).commit();
        showMapItemMenu();
    }

    @Override
    public void setGroupsList(List<GroupV2> groups) {
        this.groups = groups;
        showFragmentMapV2();
    }

    @Override   //  metodo del dialogo close session
    public void onOkButton() {
        presenter.validateDataToCloseSession(getApplicationContext());
    }

    @Override  //  metodo del dialogo close session
    public void onCancelButton() {
        dialogOkMessageTracking.dismiss();
        dialogOkMessageTracking = null;
    }

    @Override
    public void setVehiclesTitleToolbar(String vehiclesTitle) {
        getSupportActionBar().setTitle(vehiclesTitle);
        showMapItemMenu();
    }

    @Override
    public void setVehiclesListToActivity(List<VehicleV2> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public void setNewStateToVehicleList(int position, boolean newState) {
        vehicles.get(position).setSelected(newState);
    }

    @Override
    public void setGroupsListToActivity(List<GroupV2> groups) {
        this.groups = groups;
    }

    @Override
    public void setNewStateToGroupsList(int position, boolean newState) {
        groups.get(position).setSelected(newState);                 /**     Actualizamos la lista "groups"  */
        presenter.saveGroups(groups, getApplicationContext());      /**     Guardamos el grupo en db, este mismo metodo actualiza el DrawerMenu     */
    }

    @Override
    public void setVehiclesListState(boolean isVisible) {
        this.isVisibleVehiclesList = isVisible;
    }

    @Override
    public void setContactTitleToolbar(String contactTitle) {
        getSupportActionBar().setTitle(contactTitle);
    }

    @Override
    public void setNotificationsTitleToolbar(String notificationsTitle) {
        getSupportActionBar().setTitle(notificationsTitle);
    }

    @Override
    public void setCommandTitleToolbar(String commandTitleToolbar) {
        getSupportActionBar().setTitle(commandTitleToolbar);
        showBackButtonItemMenu();
    }

    @Override
    public void setMapTitleToolbar(String mapitleToolbar) {
        getSupportActionBar().setTitle(mapitleToolbar);
        showVehiclesItemMenu();
    }

    @Override
    public void setSettingsTitleToolbar(String settingsTitleToolbar) {
        getSupportActionBar().setTitle(settingsTitleToolbar);
    }

    @Override
    public void showOrHideItemRoute(boolean isVisibleItemRoute) {
        if (isVisibleItemRoute) {
            itemMenuRoute.setVisible(true);
        } else {
            itemMenuRoute.setVisible(false);
        }
    }

    @Override   // se manda a llamar desde el mapa
    public void setFragmentVehicles(Fragment fragmentVehicles) {
        transaction = manager.beginTransaction();
        transaction.replace(R.id.containerFragmentsOnLineV2, fragmentVehicles, FragmentVehiclesV2.TAG).commit();
        showMapItemMenu();
    }

    @Override
    public void showToolbar() {
        toolbar.setVisibility(View.VISIBLE);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }



    private void validateVehicles() {
        /** El usuario realizó click en el botton del mapa de la toolbar (FragmentVehicles).
         *  Si estaba en la sección de "ListaVehiculos" se deben mostrar las unidades seleccionadas.
         *  Si estaba en la sección de "listaGrupos" se deben de mostrar las unidades de los grupos seleccionados.
         */
        if (isVisibleVehiclesList)        /**Caso en el que el usuario se encuentra en la lista de vehiculos (FragmentVehicles)*/ {
            presenter.validateVehcleList(vehicles, manager, getApplicationContext());
        } else  /**Caso en el que el usuario se encuentra en la lista de grupos (FragmentVehicles)*/ {
            presenter.validateGroupVehicles(groups, getSupportFragmentManager(), getApplicationContext());
        }
    }

    public static void showVehiclesItemMenu() {
        itemMenuMap.setVisible(false);
        itemMenuVehicles.setVisible(true);
        itemMenuBack.setVisible(false);
    }

    public void showMapItemMenu() {
        itemMenuMap.setVisible(true);
        itemMenuRoute.setVisible(false);
        itemMenuVehicles.setVisible(false);
        itemMenuBack.setVisible(false);
    }

    public void showBackButtonItemMenu() {
        itemMenuMap.setVisible(false);
        itemMenuVehicles.setVisible(false);
        itemMenuRoute.setVisible(false);
        itemMenuBack.setVisible(true);
    }

    private void setFonts() {
        Typeface latoBoldTypeface = Typeface.createFromAsset(getAssets(), "fonts/Lato-Bold.ttf");
        txvCloseSession.setTypeface(latoBoldTypeface);
        txvUserName.setTypeface(latoBoldTypeface);
    }

    private void setTypefaceToolbarTitle() {
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                Typeface latoBold = Typeface.createFromAsset(getAssets(), "fonts/Lato-Bold.ttf");
                textView.setTextColor(ContextCompat.getColor(ActivityOnlineV2.this, R.color.colorBlack));
                textView.setTypeface(latoBold);
            }
        }
    }

    private void changeMenuIcon() {
        /**  Cambiamos el icono del menu */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.icon_menu);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    private void showDialogCloseSession() {
        drawer.closeDrawer(GravityCompat.START);
        dialogOkMessageTracking = new DialogOkMessageTracking();
        dialogOkMessageTracking.setOnTrackingAppSendCommandListener(this);
        dialogOkMessageTracking.setInfoNormalDialog(GeneralConstantsV2.TYPE_REQUEST_CLOSE_SESSION);
        dialogOkMessageTracking.show(getSupportFragmentManager(), DialogOkMessageTracking.TAG);
    }

    private void showFragmentMapV2() {
        transaction.addToBackStack(FragmentMapV2.TAG);
        transaction.add(R.id.containerFragmentsOnLineV2, new FragmentMapV2(), FragmentMapV2.TAG).commit();
    }

    private void backToFragmentMapV2() {
        manager.popBackStack();
    }

    private void openVehicleLocationDialog() {
        FragmentMapV2 fragmentMap = (FragmentMapV2) getSupportFragmentManager().findFragmentByTag(FragmentMapV2.TAG);
        if (fragmentMap != null) {
            fragmentMap.drawRouteWithGoogleMapsOrWaze();
        } else {
            Toast.makeText(ActivityOnlineV2.this, getString(R.string.textErrorToDrawRoute), Toast.LENGTH_LONG).show();
        }
    }

}
