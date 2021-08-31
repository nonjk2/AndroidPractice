package com.example.twojo_a.retrofit.dto;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("name")
    private String name;
    @SerializedName("age")
    private int age;
    @SerializedName("addr")
    private String addr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
