package com.pnla.onroadplus.z_version2.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientV2 {
    private static Retrofit retrofit;
    private static final String BASE_URL = RetrofitEndPointsV2.URL_SERVER;  //  private static final String URL_MAP_API2 = RetrofitEndPointsV2.URL_MAP_API;
    private static OkHttpClient okHttpClient;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(120, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .build();

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }


}
