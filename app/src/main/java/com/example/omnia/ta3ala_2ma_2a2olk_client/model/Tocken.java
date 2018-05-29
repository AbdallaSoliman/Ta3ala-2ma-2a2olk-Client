package com.example.omnia.ta3ala_2ma_2a2olk_client.model;

import com.google.gson.annotations.SerializedName;

public class Tocken {

    @SerializedName("username")
    String username ;
    @SerializedName("password")
    String password ;

    public Tocken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
