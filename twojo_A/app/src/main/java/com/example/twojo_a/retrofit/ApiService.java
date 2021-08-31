package com.example.twojo_a.retrofit;

import com.example.twojo_a.retrofit.dto.Authorization;
import com.example.twojo_a.retrofit.dto.Login;
import com.example.twojo_a.retrofit.dto.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("accounts/{accountid}")
    Call<Authorization> getAccountInfo(@Header("Authorization")String authKey,
    @Path("accountId")String accountId);


    @POST("login")
    Call<Authorization> getLoginResponse(@Body Login login);



}

