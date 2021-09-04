package com.example.twojo_a.retrofit;

import java.lang.invoke.ConstantCallSite;
import java.lang.reflect.Constructor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkHelper {
    private static NetworkHelper instance = null;
    private static ApiService apiService;
    private Retrofit retrofit;


    public NetworkHelper(){

        HttpLoggingInterceptor interceptor =new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://61.255.47.188:9090/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static NetworkHelper getInstance(){
        if (instance==null){
            instance =new NetworkHelper();
        }
        return instance;
    }

    public static ApiService getApiService(){
        return apiService;
    }

}
