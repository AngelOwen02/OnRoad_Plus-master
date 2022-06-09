package com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.util;

import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.model.MenuRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.model.MenuResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.model.VersionRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.model.VersionResponse;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MenuService {

    @POST(RetrofitEndPointsV2.GET_MENUBAR)
    Call<MenuResponse> getMenu(@Body MenuRequest menuRequest);

    @POST(RetrofitEndPointsV2.GET_VERSION)
    Call<VersionResponse> getVersion(@Body VersionRequest versionRequest);
}
