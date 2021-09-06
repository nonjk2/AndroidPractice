package com.example.twojo_a.retrofit;

import com.example.twojo_a.retrofit.dto.Authorization;
import com.example.twojo_a.retrofit.dto.Cheting;
import com.example.twojo_a.retrofit.dto.ChetingInsert;
import com.example.twojo_a.retrofit.dto.Login;
import com.example.twojo_a.retrofit.dto.MatchingRoom;
import com.example.twojo_a.retrofit.dto.Resister;
import com.example.twojo_a.retrofit.dto.Result;
import com.example.twojo_a.retrofit.dto.Status;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {




    @POST("login")
    Call<Authorization> getLoginResponse(@Body Login login);

    @POST("member/registerMem")
    Call<Status> resisterUser(@Body Resister resister);

    @POST("matching/insert")
    Call<MatchingRoom> matching(@Header("Authorization") String token);

    @POST("message/list")
    Call<List<Cheting>> chetinglist(@Body MatchingRoom matchingRoom);

    @POST("message/insert")
    Call<Status> chetingInsert(@Header("Authorization") String token,@Body ChetingInsert chetingInsert);

}

