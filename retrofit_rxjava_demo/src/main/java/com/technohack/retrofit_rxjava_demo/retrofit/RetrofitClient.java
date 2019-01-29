package com.technohack.retrofit_rxjava_demo.retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private  static final String BASE_URL="https://jsonplaceholder.typicode.com/";
    static Retrofit retrofit;

    RetrofitClient(){}


    public static Retrofit getRetrofitInstance(){

        //TODO  we are adding the RxJava adapterFactory
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }



}
