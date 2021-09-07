package com.example.twojo_a.retrofit.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Authorization {


    @SerializedName("mem_idx")
    private String mem_idx;

    @SerializedName("staff")
    public String staff;

    @SerializedName("token")
    private String token;

    @SerializedName("result")
    public String resultCode;

    public String getMem_idx() {
        return mem_idx;
    }

    public void setMem_idx(String mem_idx) {
        this.mem_idx = mem_idx;
    }

    public Authorization(String token) {
        this.token = token;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
