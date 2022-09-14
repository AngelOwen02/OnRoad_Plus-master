package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Util;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.checkListResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.checklistRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections.requestmySections;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections.responseSections;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface serviceChecklist {
    @POST(RetrofitEndPointsV2.GET_CHECKLIST)
    Call<checkListResponse> getSCheckLits(@Body checklistRequest request);
}
