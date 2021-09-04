package com.example.twojo_a.retrofit.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Resister {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("pw")
    @Expose
    private String pw;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("addr")
    @Expose
    private String addr;
    @SerializedName("age")
    @Expose
    private int age;

    public Resister(String id, String pw, String name, String addr, int age) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.addr = addr;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
