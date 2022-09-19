package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.util;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.requestmQuestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.responsemQuestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections.requestmySections;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections.responseSections;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sendquestionsmodel.requestFullCheckList;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sendquestionsmodel.responseFullCheckList;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface serviceQuestions {
    @POST(RetrofitEndPointsV2.GET_SECTIONS)
    Call<responseSections> getSections(@Body requestmySections request);
    @POST(RetrofitEndPointsV2.GET_QUESTIONS)
    Call<responsemQuestions> getQuestions(@Body requestmQuestions request);
    @POST(RetrofitEndPointsV2.SET_QUESTIONS)
    Call<responseFullCheckList> setQuestions(@Body requestFullCheckList request);
}
