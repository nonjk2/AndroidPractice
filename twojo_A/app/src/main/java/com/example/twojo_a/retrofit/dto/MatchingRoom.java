package com.example.twojo_a.retrofit.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchingRoom {

    @SerializedName("room_idx")
    @Expose
    private int room_idx;

    public MatchingRoom(int room_idx) {
        this.room_idx = room_idx;
    }

    public int getRoom_idx() {
        return room_idx;
    }

    public void setRoom_idx(int room_idx) {
        this.room_idx = room_idx;
    }
}
