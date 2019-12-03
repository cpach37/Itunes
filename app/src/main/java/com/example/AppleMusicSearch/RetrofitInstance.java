package com.example.AppleMusicSearch;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class  RetrofitInstance {

    private static Retrofit retrofit;
    private static String BASE_URL= "https://itunes.apple.com";
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}