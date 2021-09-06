package com.example.twojo_a.retrofit.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChetingInsert {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("room_idx")
    @Expose
    private int room_idx;

    public ChetingInsert(String message, int room_idx) {
        this.message = message;
        this.room_idx = room_idx;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRoom_idx() {
        return room_idx;
    }

    public void setRoom_idx(int room_idx) {
        this.room_idx = room_idx;
    }
}
