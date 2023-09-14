package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.dataQuestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.requestmQuestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.responsemQuestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections.dataSections;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections.requestmySections;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections.responseSections;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sendquestionsmodel.Image;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sendquestionsmodel.requestFullCheckList;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sendquestionsmodel.responseFullCheckList;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.presenter.questionsPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.util.serviceQuestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.view.Questions;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.jsonforsender.mfQuestion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 private int finalscoore=0;
 private boolean aprobacionR=false;
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
                presenter.hidepDialog();
            }
        });

    }
    private void validateCode(Response<responseSections> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getSectionsdata(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
                presenter.hidepDialog();
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

    @Override
    public void sendfullCheckList(int cve_checklist, boolean aproved, String emailaprovador, String startdate) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String email = emailaprovador;
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        String cveV=preferences.getString(GeneralConstantsV2.CVE_CHECKLIST_VEHICLE, null);
        String NAMEVE=preferences.getString(GeneralConstantsV2.NAME_CHECKLIST_VEHICLE,null);
        aprobacionR=aproved;
        if(token!=null)
        {
            requestSendFullChckelist(aproved,cve_checklist,cveV,token,email,startdate);
        }
    }

    private void requestSendFullChckelist(boolean aproved, int cve_checklist, String cveV, String token, String email, String startdate) {

        //todo aqui caen los campos
        //region checklist
        /**
         * {
         * “approvement”:”bool”,               x
         * “cve_trip_mgm_checklist”:integer,   x listo
         * "cve_vehicle": integer,             ?
         * "destination_trip": integer,        x listo
         * “e-mail”:”string”,                  x listo
         *   "imagebase64": [                  x null
         *     {
         *       “cve_trip_mgm_question”: int,
         *       "image": "string"
         *     }
         *   ],
         * "json_answer": string,              null
         * "origin_trip": integer,             x listo
         * "score": integer,                   x listo
         * "token": "string"                   x listo
         * }
         * */
        // private int origin;
        //    private int questioId;
        //    private int section;
        //    private int answerPos;
        //    private String foto;
        //    private Integer cveTripMgmQuestion;
        //endregion
        Log.e("senderdatacehcklist",""+Questions.fulChecklist);
        Log.e("senderdatacehcklist",""+Questions.fulChecklist);
         finalscoore=0;
        List<mfQuestion> finalQ=new ArrayList<>();  //todo esto es pal json
        finalQ.clear();

        for(int i=0;i<Questions.fulChecklist.size();i++)
        {

            finalscoore=finalscoore+Questions.fulChecklist.get(i).getScore();
            finalQ.add(new mfQuestion(Questions.fulChecklist.get(i).getCveTripMgmQuestion(),Questions.fulChecklist.get(i).getCveTripMgmAnswer(),Questions.fulChecklist.get(i).getDesc_trip_mgm_answer()));
        }

        //requestFullCheckList request=new requestFullCheckList("","","","","","","","","","");
        Log.e("checklistAnserScore","fS: "+finalscoore);
        String imagabase64=
                //region imagebase64
                "AA=";
                //endregion
        String json
                //region json;
        ="{\\\"Questions\\\": [{\\\"cve_trip_mgm_question\\\": 1,\\\"cve_trip_mgm_answer\\\": 1}]}";
        //endregion

        Gson gson = new Gson();
        String mfjson = gson.toJson(finalQ);

        List<Image> image=new ArrayList<>();
        image.clear();
        //image.add(new Image(2,imagabase64));
        for(int j=0;j<Questions.fulChecklist.size();j++)
        {
            image.add(new Image(Questions.fulChecklist.get(j).getCveTripMgmQuestion(),Questions.fulChecklist.get(j).getFoto()));
        }
        Date currentDate = new Date();

        // Define the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Format the current date and time
        String formattedDateTime = dateFormat.format(currentDate);
        requestFullCheckList request=new requestFullCheckList(aproved,cve_checklist, Integer.valueOf(cveV),0,email,image,mfjson,Questions.fulChecklist.get(0).getOrigin(),finalscoore,token,startdate,formattedDateTime);
       //  ejemplo      requestFullCheckList request=new requestFullCheckList(true,28,13756,0,"efren@newlandapps.com",image,json,1,2,token);
        presenter.showpDialog();
        Call<responseFullCheckList> call=service.setQuestions(request);
        call.enqueue(new Callback<responseFullCheckList>() {
            @Override
            public void onResponse(Call<responseFullCheckList> call, Response<responseFullCheckList> response) {
                validateCodesetQuestions(response, context);
            }

            @Override
            public void onFailure(Call<responseFullCheckList> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateCodesetQuestions(Response<responseFullCheckList> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                setQuestionsdata(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setQuestionsdata(Response<responseFullCheckList> response, Context context) {
        responseFullCheckList respons=response.body();
        if(respons!=null)
        {
            String message = respons.getMessage();
            int responseCode = respons.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                String data=respons.getData();
                if(data!=null)
                {
                    presenter.gotoChecklistAgain(data,finalscoore,aprobacionR);
                    presenter.hidepDialog();
                }else
                {
                    Toast.makeText(context, "data vacia:105", Toast.LENGTH_SHORT).show();
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
                presenter.hidepDialog();
            }
        });
    }

    private void validateCodeQuestions(Response<responsemQuestions> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getQuestionsdata(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
                presenter.hidepDialog();
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
