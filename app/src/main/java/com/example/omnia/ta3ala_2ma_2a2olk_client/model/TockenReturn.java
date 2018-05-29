package com.example.omnia.ta3ala_2ma_2a2olk_client.model;

import com.google.gson.annotations.SerializedName;

public class TockenReturn {

    @SerializedName("token")
    String tocken;

    public String getTocken() {
        return tocken;
    }

    public void setTocken(String tocken) {
        this.tocken = tocken;
    }
}
