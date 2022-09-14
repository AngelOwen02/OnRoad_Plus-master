package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.util;


import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.model.checkListHistoricResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.model.checklistHistoricRequest;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface histprocService {

    @POST(RetrofitEndPointsV2.GET_HISTORIC)
    Call<checkListHistoricResponse> getCheckList(@Body checklistHistoricRequest request);
}
