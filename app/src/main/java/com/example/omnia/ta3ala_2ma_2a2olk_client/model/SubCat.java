package com.example.omnia.ta3ala_2ma_2a2olk_client.model;

import com.google.gson.annotations.SerializedName;

public class SubCat {
    @SerializedName("numberOfQues")
    int numberOfQues ;
    @SerializedName("catName")
    String catName;
    @SerializedName("catId")
    int catId;

    public int getNumberOfQues() {
        return numberOfQues;
    }

    public void setNumberOfQues(int numberOfQues) {
        this.numberOfQues = numberOfQues;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }
}
