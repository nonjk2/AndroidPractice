package com.example.twojo_a.retrofit.dto;

import android.content.SharedPreferences;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Cheting {
    @SerializedName("message_idx")
    @Expose
    private int message_idx;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("mem_idx")
    @Expose
    private int mem_idx;
    @SerializedName("room_idx")
    @Expose
    private int room_idx;
    @SerializedName("time")
    @Expose
    private Timestamp time;
    @SerializedName("viewType")
    @Expose
    private int viewType;


    public Cheting(int message_idx, String message, int mem_idx, int room_idx, Timestamp time ,int viewType) {
        this.message_idx = message_idx;
        this.message = message;
        this.mem_idx = mem_idx;
        this.room_idx = room_idx;
        this.time = time;
        this.viewType = viewType;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public int getMessage_idx() {
        return message_idx;
    }

    public void setMessage_idx(int message_idx) {
        this.message_idx = message_idx;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMem_idx() {
        return mem_idx;
    }

    public void setMem_idx(int mem_idx) {
        this.mem_idx = mem_idx;
    }

    public int getRoom_idx() {
        return room_idx;
    }

    public void setRoom_idx(int room_idx) {
        this.room_idx = room_idx;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }



}

