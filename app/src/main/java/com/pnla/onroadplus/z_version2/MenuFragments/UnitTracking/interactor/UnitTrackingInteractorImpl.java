package com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.pnla.onroadplus.z_version2.Containers.UnitTrackingContainer;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.adapter.GroupTrackingAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.adapter.UnitTrackingAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.model.TrackingService;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.presenter.UnitTrackingPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Group.FinalGroupDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Group.GroupDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Group.TemporalGroupDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.FinalUnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.TemporalUnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.Group;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.GroupvehicleInsideData;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.GroupvehicleInsideRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.GroupvehicleInsideResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.interactor.UnitsInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UnitTrackingInteractorImpl implements UnitTrackingInteractor {

    private UnitTrackingPresenter presenter;
    private TrackingService service;
    private Context context;
    private List<GroupvehicleInsideData> groupvehicleInsideData;
    private List<String> gruposdata = new ArrayList<>();
    public static List<Integer> groupsdataInteger=new ArrayList<>();

    public UnitTrackingInteractorImpl(UnitTrackingPresenter presenter) {
        this.presenter = presenter;
        initRetrofit();
    }
    private void initRetrofit() {
        Retrofit retrofitClient = RetrofitClientV2.getRetrofitInstance();
        service = retrofitClient.create(TrackingService.class);
    }
    @Override
    public void getVehiclesAnGroups(Context context)
    {



    }

    @Override
    public void getVehicles(Context context) {
        Log.e("toogleslistn","frafra "+ UnitsInteractorImpl.dataofvehicles);
        presenter.setVehicleList(getUnitList());
    }

    @Override
    public void getGroups(Context context) {

        presenter.setGroupsList(getGroupList());

    }

    @Override
    public void onClickGroups() {
        if (groupDBIsEmpty()) {
            presenter.hideProgressBar();
            presenter.hideGroupRV();
            presenter.showEmptyGroupsImage();
        } else {
            presenter.showGroupRV();
            presenter.setGroupsList(getGroupList());
        }
    }

    @Override
    public void getUnitsfromgroups(Context context) {
if(UnitTrackingContainer.integervalueforrequest!=0) {
    SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
    String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
    //cveofgroup = 53;
    startGroupsRequestVehicles(UnitTrackingContainer.integervalueforrequest, token);

}/*else{        if(!UnitTrackingAdapter.integerList.isEmpty()) {
            for (int i = 0; i < UnitTrackingInteractorImpl.groupsdataInteger.size(); i++) {
                UnitTrackingAdapter.integerList.remove(UnitTrackingInteractorImpl.groupsdataInteger.get(i));
            }
        }}*/
    }
    private void startGroupsRequestVehicles(int cve,String token){
    /*    GroupvehicleInsideRequest request = new GroupvehicleInsideRequest(cve,token);
        Log.e("unitsthaticansaw", "response" +  cve+ " " + token);
          unitService.getVehiclesInGroups(request).enqueue(new Callback<GroupvehicleInsideResponse>() {
              @Override
              public void onResponse(Call<GroupvehicleInsideResponse> call, Response<GroupvehicleInsideResponse> response) {
                  validateGroupsVehiclesCode(response);
                  Log.e("unitsthaticansaw", "onresponse: " +   response);
                  Log.e("unitsthaticansaw", "onresponse: " +   call.request().toString());
              }

              @Override
              public void onFailure(Call<GroupvehicleInsideResponse> call, Throwable t) {
                      Log.e("unitsthaticansaw","validate : "+ call.request().toString());
                  Log.e("unitsthaticansaw","validate : "+ t.toString());
              }
          });*/

        GroupvehicleInsideRequest requestG=new GroupvehicleInsideRequest(cve,token);
        Call<GroupvehicleInsideResponse> call=service.getVehiclesInGroups(requestG);
        call.enqueue(new Callback<GroupvehicleInsideResponse>() {
            @Override
            public void onResponse(Call<GroupvehicleInsideResponse> call, Response<GroupvehicleInsideResponse> response) {
                //    Log.e("unitsthaticansaw", "onresponse: " +   response);
                validateGroupsVehiclesCode(response);
            }

            @Override
            public void onFailure(Call<GroupvehicleInsideResponse> call, Throwable t) {
                //Log.e("unitsthaticansaw","validate : "+ t.toString());
            }
        });

    }

    private void validateGroupsVehiclesCode(Response<GroupvehicleInsideResponse> response){
        Log.e("validategroups","validate : "+ String.valueOf(response.body().getResponseCode()));
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {

            getGrupsDataVhicles(response);
        }

    }
    /**reques de grupos**/
    private void getGrupsDataVhicles(Response<GroupvehicleInsideResponse> response )
    {

        GroupvehicleInsideResponse groupResponse = response.body();
        if (groupResponse != null) {
            int responseCode = groupResponse.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                //List<GroupvehicleInsideData> data = groupResponse.getData();

                groupvehicleInsideData = groupResponse.getData();
              // Log.e("groupsitems",""+groupvehicleInsideData.toString());
                if (groupvehicleInsideData != null){

                    //  List<String> gruposdata = new ArrayList<>();
                    gruposdata.clear();
                    groupsdataInteger.clear();
                    GroupTrackingAdapter.dataofvehiclesgroupsnames2.clear();
                    // dataofvehiclesgroupsnames.clear();
                    /*for (GroupvehicleInsideData data : groupvehicleInsideData){
                        //gruposdata.add(String.valueOf(data.getCve_vehicle()));
                        gruposdata.add(String.valueOf(data.getVehicle_name()));

                    }*/
                    Log.e("itemsnew","boolean duplicidad   "+ GroupTrackingAdapter.request);
                    if(GroupTrackingAdapter.request==true) {
                        for (int i = 0; i < groupvehicleInsideData.size(); i++) {
                            if(groupsdataInteger.contains(Integer.parseInt(groupvehicleInsideData.get(i).getCve_vehicle().trim()))){

                            }
                            else
                            {
                                Log.e("groupsitems"," : "+groupvehicleInsideData.get(i).getCve_vehicle());
                                if(UnitTrackingAdapter.integerList.contains(groupvehicleInsideData.get(i).getCve_vehicle()))
                                {

                                }else
                                {
                                    UnitTrackingAdapter.integerList.add(Integer.valueOf(  groupvehicleInsideData.get(i).getCve_vehicle()));
                                }
                                if(!gruposdata.isEmpty()) {
                                    gruposdata.add(String.valueOf(groupvehicleInsideData.get(i).getCve_vehicle()));
                                    //  Log.e("dothetogles",""+i);
                                    groupsdataInteger.add(Integer.parseInt(groupvehicleInsideData.get(i).getCve_vehicle().trim()));
                                    UnitTrackingAdapter.integerList.add(Integer.parseInt(groupvehicleInsideData.get(i).getCve_vehicle().trim()));
                                    UnitDB.updateCheckedStatus(groupvehicleInsideData.get(i).getVehicle_name(), true);
                                }
                            }
                        }


                        GroupTrackingAdapter.dataofvehiclesgroupsnames2.add(String.valueOf(gruposdata));
                        Log.e("itemsnew", "datainteractor add  " + UnitTrackingAdapter.integerList);
                        GroupTrackingAdapter.intforrequest = 0;
                        Log.e("unitsfirstscreen",""+UnitTrackingAdapter.integerList);
                        Log.e("unitsfirstscreen",""+ UnitDB.getUnitListActive());
                    }

                    else
                    {

                        List<String> namesvehicles=new ArrayList<>();
                        for (int i = 0; i < groupvehicleInsideData.size(); i++) {
                           // gruposdata.add(String.valueOf(groupvehicleInsideData.get(i).getCve_vehicle()));
                            //  Log.e("dothetogles",""+i);
                            if(groupsdataInteger.contains(Integer.parseInt(groupvehicleInsideData.get(i).getCve_vehicle().trim()))){

                            }

                                groupsdataInteger.add(Integer.parseInt(groupvehicleInsideData.get(i).getCve_vehicle().trim()));   /**esto hace el recuest de los items en false*/
                                namesvehicles.add(groupvehicleInsideData.get(i).getVehicle_name());


                         //   UnitTrackingAdapter.integerList.add(Integer.parseInt(groupvehicleInsideData.get(i).getCve_vehicle().trim()));
                        }
                        Log.e("groupsitems", "datinteract remove  " + groupsdataInteger); //UnitTrackingAdapter.integerList);

                        for (int i=0;i<groupsdataInteger.size();i++)                                   /** */
                        {
                            UnitTrackingAdapter.integerList.remove(UnitTrackingInteractorImpl.groupsdataInteger.get(i));
                           UnitDB.updateCheckedStatus(groupvehicleInsideData.get(i).getVehicle_name(),false);
                            if(namesvehicles.contains(groupvehicleInsideData.get(i).getVehicle_name()))
                            {
                          //     UnitDB.updateCheckedStatus(groupvehicleInsideData.get(i).getVehicle_name(),true);
                            }else
                            {
                               UnitDB.updateCheckedStatus(groupvehicleInsideData.get(i).getVehicle_name(),false);
                            }
                        }
                       /* for(int j=0;j<namesvehicles.size();j++)
                        {
                            //  UnitDB.updateCheckedStatus(namesvehicles.get(j),true);
                        }*/
                         Log.e("unitsfirstscreen",""+GroupDB.getActiveGroupList());
                          Log.e("unitsfirstscreen",""+ UnitDB.getUnitListActive());

                        Log.e("itemsnew", "itemsin integerlist " + UnitTrackingAdapter.integerList);
                        GroupTrackingAdapter.intforrequest = 0;
                    }

                  //  Log.e("dothetogles","groupsdata "+gruposdata.size());
                }
            }
        }


    }

    private boolean unitDBIsEmpty() {
        return UnitDB.getUnitList().isEmpty();
    }

    private boolean groupDBIsEmpty() {
        return GroupDB.getGroupList().isEmpty();
    }

    private List<Unit> getUnitList() {
        return UnitDB.getUnitList();
    }

    private List<Group> getGroupList() {
        return GroupDB.getGroupList();
    }

    // Temporal

    private boolean temporalUnitDBIsEmpty() {
        return TemporalUnitDB.getUnitList().isEmpty();
    }

    private boolean temporalGroupDBIsEmpty() {
        return TemporalGroupDB.getGroupList().isEmpty();
    }

    private List<Unit> getTemporalUnitList() {
        return TemporalUnitDB.getUnitList();
    }

    private List<Group> getTemporalGroupList() {
        return TemporalGroupDB.getGroupList();
    }

    private void createTemporalUnitDB(List<Unit> unitList) {
        for (Unit unit : unitList) {
            TemporalUnitDB.createNewUnit(unit.isVehicleStatus(), unit.getCveVehicle(), unit.getVehicleSwitch(), unit.getVehicleName(), unit.getVehicleImage(), unit.getSendTime(), unit.getDescBrand(), unit.getDescModel(), unit.getVehicleYear(), unit.getVehicleVin(), unit.getVehiclePlate(), unit.getGeoreference(), unit.getTimeTravel(), unit.getTimeElapsed(), unit.getLatitude(), unit.getLongitude(), unit.getMileage(), unit.getKmTravel(), unit.getCurrentSpeed(), unit.getMaxSpeed());
        }
    }

    private void createTemporalGroupDB(List<Group> groupList) {
        for (Group group : groupList) {
            TemporalGroupDB.createNewGroup(group.getCve_vehicle_group(), group.getUserGroup(), group.getVehicle_group(), group.getDesc_vehicle_group(), group.isSelected(), group.getPositionItem(), group.getVehicles());
        }
    }

    private void updateTemporalUnitDB(List<Unit> unitList) {
        for (Unit unit : unitList) {
            TemporalUnitDB.updateUnits(unit.getId(),unit.isVehicleStatus(), unit.getCveVehicle(), unit.getVehicleSwitch(), unit.getVehicleName(), unit.getVehicleImage(), unit.getSendTime(), unit.getDescBrand(), unit.getDescModel(), unit.getVehicleYear(), unit.getVehicleVin(), unit.getVehiclePlate(), unit.getGeoreference(), unit.getTimeTravel(), unit.getTimeElapsed(), unit.getLatitude(), unit.getLongitude(), unit.getMileage(), unit.getKmTravel(), unit.getCurrentSpeed(), unit.getMaxSpeed());
        }
    }

    private void updateTemporalGroupDB(List<Group> groupList) {
        for (Group group : groupList) {
            TemporalGroupDB.updateGroups(group.getId(),group.getCve_vehicle_group(), group.getUserGroup(), group.getVehicle_group(), group.getDesc_vehicle_group(), group.isSelected(), group.getPositionItem(), group.getVehicles());
        }
    }

    //FInal

    private boolean finalUnitDBIsEmpty() {
        return FinalUnitDB.getUnitList().isEmpty();
    }

    private boolean finalGroupDBIsEmpty() {
        return FinalGroupDB.getGroupList().isEmpty();
    }

    private List<Unit> getFinalUnitList() {
        return FinalUnitDB.getUnitList();
    }

    private List<Group> getFinalGroupList() {
        return FinalGroupDB.getGroupList();
    }

    private void createFinalUnitDB(List<Unit> unitList) {
        for (Unit unit : unitList) {
            FinalUnitDB.createNewUnit(unit.isVehicleStatus(), unit.getCveVehicle(), unit.getVehicleSwitch(), unit.getVehicleName(), unit.getVehicleImage(), unit.getSendTime(), unit.getDescBrand(), unit.getDescModel(), unit.getVehicleYear(), unit.getVehicleVin(), unit.getVehiclePlate(), unit.getGeoreference(), unit.getTimeTravel(), unit.getTimeElapsed(), unit.getLatitude(), unit.getLongitude(), unit.getMileage(), unit.getKmTravel(), unit.getCurrentSpeed(), unit.getMaxSpeed());
        }
    }

    private void createFinalGroupDB(List<Group> groupList) {
        for (Group group : groupList) {
            FinalGroupDB.createNewGroup(group.getCve_vehicle_group(), group.getUserGroup(), group.getVehicle_group(), group.getDesc_vehicle_group(), group.isSelected(), group.getPositionItem(), group.getVehicles());
        }
    }

    private void updateFinalUnitDB(List<Unit> unitList) {
        for (Unit unit : unitList) {
            FinalUnitDB.updateUnits(unit.getId(),unit.isVehicleStatus(), unit.getCveVehicle(), unit.getVehicleSwitch(), unit.getVehicleName(), unit.getVehicleImage(), unit.getSendTime(), unit.getDescBrand(), unit.getDescModel(), unit.getVehicleYear(), unit.getVehicleVin(), unit.getVehiclePlate(), unit.getGeoreference(), unit.getTimeTravel(), unit.getTimeElapsed(), unit.getLatitude(), unit.getLongitude(), unit.getMileage(), unit.getKmTravel(), unit.getCurrentSpeed(), unit.getMaxSpeed());
        }
    }

    private void updateFinalGroupDB(List<Group> groupList) {
        for (Group group : groupList) {
            FinalGroupDB.updateGroups(group.getId(),group.getCve_vehicle_group(), group.getUserGroup(), group.getVehicle_group(), group.getDesc_vehicle_group(), group.isSelected(), group.getPositionItem(), group.getVehicles());
        }
    }

}
