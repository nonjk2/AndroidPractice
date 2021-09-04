package com.example.twojo_a.retrofit.dto;

import java.io.Serializable;

public class Status implements Serializable {

    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
