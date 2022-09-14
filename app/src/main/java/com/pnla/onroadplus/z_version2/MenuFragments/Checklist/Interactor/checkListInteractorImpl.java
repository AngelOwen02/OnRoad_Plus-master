package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.checkListResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.checkListdata;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.checklistRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Presenter.checkListPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Util.serviceChecklist;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.util.asignmentService;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class checkListInteractorImpl implements checkListInteractor{


    private Context context;
    private checkListPresenter presenter;
    private serviceChecklist service;

    private Retrofit retrofitClient;
    public checkListInteractorImpl( checkListPresenter presenter,Context context)
    {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        service = retrofitClient.create(serviceChecklist.class);
    }

    @Override
    public void requestCheckList() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            Log.e("questionsConf","token: "+token);
          //  presenter.setChecList();
            requestfullCheckList(token);
        }
    }

    private void requestfullCheckList(String token) {
        checklistRequest request = new checklistRequest(token);
        presenter.showpDialog();
        Call<checkListResponse> call = service.getSCheckLits(request);
        call.enqueue(new Callback<checkListResponse>() {
            @Override
            public void onResponse(Call<checkListResponse> call, Response<checkListResponse> response) {
                validateCodeChecklist(response,context);
            }

            @Override
            public void onFailure(Call<checkListResponse> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateCodeChecklist(Response<checkListResponse> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                checkList(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void checkList(Response<checkListResponse> response, Context context) {
          checkListResponse responsD=response.body();
                if(responsD!=null) {
                    int code = responsD.getResponseCode();
                    String message = responsD.getMessage();
                    List<checkListdata> data=responsD.getData();
                    if (code == GeneralConstantsV2.RESPONSE_CODE_OK) {
                        if (data != null) {
                            presenter.setChecList(data);
                            presenter.hideDialog();
                        } else {
                            presenter.hideDialog();
                            Toast.makeText(context, "" + responsD.getMessage() + " " + responsD.getResponseCode(), Toast.LENGTH_SHORT).show();
                        }
                    }else
                    {
                        presenter.hideDialog();
                        Toast.makeText(context, "" + responsD.getMessage() + " " + responsD.getResponseCode(), Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    presenter.hideDialog();
                    Toast.makeText(context, ""+responsD.getMessage()+" "+responsD.getResponseCode(), Toast.LENGTH_SHORT).show();
                }
    }

}
