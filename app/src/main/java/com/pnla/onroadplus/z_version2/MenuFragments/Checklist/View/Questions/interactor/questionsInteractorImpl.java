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
import java.util.ArrayList;
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
 private int finalscoore;
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

    @Override
    public void sendfullCheckList(int cve_checklist, boolean aproved) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String email = preferences.getString(GeneralConstantsV2.EMAIL_PREFERENCES, null);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        String cveV=preferences.getString(GeneralConstantsV2.CVE_CHECKLIST_VEHICLE, null);
        String NAMEVE=preferences.getString(GeneralConstantsV2.NAME_CHECKLIST_VEHICLE,null);
        if(token!=null)
        {
            requestSendFullChckelist(aproved,cve_checklist,cveV,token,email);
        }
    }

    private void requestSendFullChckelist(boolean aproved, int cve_checklist, String cveV, String token, String email) {

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
         finalscoore=0;
        List<mfQuestion> finalQ=new ArrayList<>();  //todo esto es pal json
        finalQ.clear();

        for(int i=0;i<Questions.fulChecklist.size();i++)
        {
            finalscoore=finalscoore+Questions.fulChecklist.get(i).getScore();
            finalQ.add(new mfQuestion(Questions.fulChecklist.get(i).getCveTripMgmQuestion(),Questions.fulChecklist.get(i).getCveTripMgmAnswer()));
        }

        //requestFullCheckList request=new requestFullCheckList("","","","","","","","","","");
        String imagabase64=
                //region imagebase64
                "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";
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

        requestFullCheckList request=new requestFullCheckList(aproved,cve_checklist, Integer.valueOf(cveV),0,email,image,mfjson,Questions.fulChecklist.get(0).getOrigin(),finalscoore,token);
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
                    presenter.gotoChecklistAgain(data,finalscoore);
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
