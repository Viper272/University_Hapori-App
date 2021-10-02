package com.org.omicron.haporiapplication.restAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private API api;

    private RetrofitClient() {
        Retrofit retro = new Retrofit.Builder().baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        api = retro.create(API.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if(instance == null)
            instance = new RetrofitClient();

        return instance;
    }

    public API getApi() { return api; }

}
