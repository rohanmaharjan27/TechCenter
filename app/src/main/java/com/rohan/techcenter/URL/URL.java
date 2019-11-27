package com.rohan.techcenter.URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class URL {
    public static  final String BASE_URL="http://192.168.100.5:8000/";

    public static Retrofit getInstance()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
