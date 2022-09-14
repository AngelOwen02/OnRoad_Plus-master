package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.dataQuestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.mquestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.requestmQuestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.responsemQuestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections.dataSections;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections.requestmySections;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections.responseSections;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.presenter.questionsPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.util.serviceQuestions;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class questionsInteractorImpl  implements questionsInteractor{

   private questionsPresenterImpl presenter;
   private Context context;
 private Retrofit retrofitClient;
 private serviceQuestions service;
    public questionsInteractorImpl(questionsPresenterImpl presenter, Context context) {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        service = retrofitClient.create(serviceQuestions.class);
    }
    //region sections
    @Override
    public void getiSections(Integer checklistN) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null) {
            Log.e("newchecklistEndpoints","V: "+checklistN+" "+token);
            requestSections(checklistN,token);
        }
    }
    /** esta metodo trae las secciones*/
    private void requestSections(Integer checklistN,String token) {
        requestmySections request=new requestmySections(checklistN,token);
        presenter.showpDialog();
        Call<responseSections> call=service.getSections(request);
        call.enqueue(new Callback<responseSections>() {
            @Override
            public void onResponse(Call<responseSections> call, Response<responseSections> response) {
                validateCode(response, context);
            }

            @Override
            public void onFailure(Call<responseSections> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void validateCode(Response<responseSections> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getSectionsdata(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getSectionsdata(Response<responseSections> response, Context context) {
             responseSections responseinfo = response.body();
                if (responseinfo != null) {
                    String message = responseinfo.getMessage();
                    int responseCode = responseinfo.getResponseCode();
                    if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                        List<dataSections> data=responseinfo.getData();
                            if(data!=null)
                            {
                                presenter.setSections(data);
                                presenter.hidepDialog();
                            }else
                            {
                                Toast.makeText(context, "vacio:105", Toast.LENGTH_SHORT).show();
                                presenter.hidepDialog();
                            }

                    }else
                    {
                        Toast.makeText(context, "" + response.code(), Toast.LENGTH_SHORT).show();
                        presenter.hidepDialog();
                    }
                }else
                {
                    Toast.makeText(context, "" + response.code(), Toast.LENGTH_SHORT).show();
                    presenter.hidepDialog();
                }
    }
//endregion
    //region questions
    @Override
    public void getQuestions(Integer dataSections) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            Log.e("question","token:   "+token);
            requestAquestion(dataSections,token);
        }
    }

    private void requestAquestion(Integer dataSections, String token) {
        requestmQuestions request=new requestmQuestions(dataSections,token);//dataSections,
        presenter.showpDialog();
        Call<responsemQuestions> call=service.getQuestions(request);
        call.enqueue(new Callback<responsemQuestions>() {
            @Override
            public void onResponse(Call<responsemQuestions> call, Response<responsemQuestions> response) {
                validateCodeQuestions(response, context);
            }

            @Override
            public void onFailure(Call<responsemQuestions> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateCodeQuestions(Response<responsemQuestions> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getQuestionsdata(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getQuestionsdata(Response<responsemQuestions> response, Context context) {
        responsemQuestions respons=response.body();
        if(respons!=null)
        {
            String message = respons.getMessage();
            int responseCode = respons.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                List<dataQuestions> data=respons.getData();
                if(data!=null)
                {
                   presenter.setQuestions(data);
                   presenter.hidepDialog();
                }else
                {
                    Toast.makeText(context, "vacio:105", Toast.LENGTH_SHORT).show();
                    presenter.hidepDialog();
                }

            }else
            {
                Toast.makeText(context, "" + response.code(), Toast.LENGTH_SHORT).show();
                presenter.hidepDialog();
            }
        }else
        {
            Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            presenter.hidepDialog();
        }

    }


    //endregion
}
