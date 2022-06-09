package com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.interactor;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import com.pnla.onroadplus.BuildConfig;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.model.MenuData;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.model.MenuRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.model.MenuResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.model.Version;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.model.VersionRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.model.VersionResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.presenter.menuPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.util.MenuService;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class menuInteractorImpl implements menuInteractor {


    private menuPresenter presenter;
    private Context context;
    private MenuService service;
    private Retrofit retrofitClient;
    private RetrofitValidationsV2 retrofitValidations;
    private  String name="OnRoad";
    private List<String> versiones;
    public Version version;
    public List<MenuData> menudata;
    public static List<String> valuesmenues=new ArrayList<>();

    public menuInteractorImpl( menuPresenter presenter,Context context)
    {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        service = retrofitClient.create(MenuService.class);
    }

    @Override
    public void getMenuitems() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            doitemMenuRequest(1144,token);
        }

    }

    @Override
    public void getVersionapp() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null) {
            doversionRequest(token);
        }
    }

    private void doversionRequest(String token)
    {
        final VersionRequest request = new VersionRequest(name,token);
    //    Retrofit retrofit = RetrofitEndPointsV2.getRetrofitInstance();
       // service  = retrofit.create(MenuService.class);
        Call<VersionResponse> call = service.getVersion(request);
        call.enqueue(new Callback<VersionResponse>() {
            @Override
            public void onResponse(Call<VersionResponse> call, Response<VersionResponse> response) {
                if (response.body().getResponseCode()==105) {
                    versiones = new ArrayList<>();
                    version = response.body().getData();
                    if(version!=null){
                        String [] string1 = BuildConfig.VERSION_NAME.split("[.]");
                        String [] string2 = version.getDesc_version().split("[.]");
                        Integer[] number2 = new Integer[string2.length];

                        Integer[] numbers = new Integer[string2.length];


                        for (int i = 0; i < string2.length; i++) {
                            if (string1.length-1<i)
                                numbers[i] = 0;
                            else
                                numbers[i] = Integer.parseInt(string1[i]);
                        }


                        for (int i = 0; i < string2.length; i++) {
                            number2[i] = Integer.parseInt(string2[i]);
                        }

                        for (int i = 0; i < number2.length; i++) {
                            if (number2[i] > numbers[i]) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogCustom);
                                builder.setTitle("Hay una nueva versión disponible de OnRoad");
                                builder.setMessage("Tu versión: " + BuildConfig.VERSION_NAME + "\n\n" + "Nueva versión: " + version.getDesc_version());
                                builder.setCancelable(true);
                                builder.setPositiveButton("Descargar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.newlandapps.onroad"));
                                        intent.setPackage("com.android.vending");
                                        context.startActivity(intent);
                                    }
                                });
                                builder.create();
                                builder.show();
                                break;
                            } else {

                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<VersionResponse> call, Throwable t) {

            }
        });
    }

    private void doitemMenuRequest(int appcode ,String token){
        MenuRequest menuRequest=new MenuRequest(appcode,token);
        Call<MenuResponse> call=service.getMenu(menuRequest);
        call.enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(Call<MenuResponse> call, Response<MenuResponse> response) {
                getMenuResponse(response);
            }

            @Override
            public void onFailure(Call<MenuResponse> call, Throwable t) {
                presenter.setErrorToView(retrofitValidations.getOnFailureResponse(t, context));
            }
        });
    }
    private void getMenuResponse(Response<MenuResponse> response)
    {
        if (retrofitValidations.checkSuccessCode(response.code())) {
            getMenuSucces(response);
        }else {
            presenter.setErrorToView(retrofitValidations.getErrorByStatus(response.code(), context));
        }
    }

    private void getMenuSucces(Response<MenuResponse> response){
        MenuResponse menuresponse=    response.body();
        if(menuresponse!= null) {
            int responseCode = menuresponse.getResponseCode();
            if (responseCode == 105) {

                menudata=menuresponse.getData();
                if (menudata != null) {
                    valuesmenues.clear();
                    for(int i=0;i<menudata.size(); i++)
                    {
                        Log.e("datalistmenus",""+menudata.get(i).getObj_name()+": "+menudata.get(i).isAccess_flag());
                        valuesmenues.add(menudata.get(i).getObj_name()+":"+menudata.get(i).isAccess_flag());
                    }
                    presenter.nameslistitems(valuesmenues);
                    //List<String> asd=menudata.get(0).isAccess_flag();
                }



            }
            else if (responseCode == 104) {


                SharedPreferences preferences = context.getSharedPreferences("credentials", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                presenter.closeAppSessionExpired();
            }
        }else {
            presenter.setErrorToView(  context.getString(R.string.errorEmptyLogoutResponse));

        }
    }
}
