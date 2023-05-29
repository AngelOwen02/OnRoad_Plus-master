package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.interactor;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.utils.ExternalApiSerice;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.presenter.unitViewpresenterV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.util.serviceVehicleDescription;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitExternalApi;

import retrofit2.Retrofit;

public class unitMapInteractorV3Impl implements unitMapInteractorV3{

    private Context context;
    private unitViewpresenterV3 presenter;
    private serviceVehicleDescription services;
    private ExternalApiSerice externalservice;
    private Retrofit retrofitClient,retrofitExternalapi;

    public unitMapInteractorV3Impl(unitViewpresenterV3 presenter, Context context) {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        services = retrofitClient.create(serviceVehicleDescription.class);

        retrofitExternalapi= RetrofitExternalApi.getApiMap2();
        externalservice=retrofitExternalapi.create(ExternalApiSerice.class);
    }

    @Override
    public void getVehicleDescription() {
        //todo LUIS en esta clase hereda el token del user y el cve comprobando antes el token
        //todo llegar hasta el consumo del endpoint y colocar el metodo setDataDescriptionVehicle en la View
    }
}
