package com.example.omnia.ta3ala_2ma_2a2olk_client.model;

import com.google.gson.annotations.SerializedName;

import java.security.PrivateKey;
import java.util.List;

public class MainCat {

    @SerializedName("imgUrl")
    String imgUrl;
    @SerializedName("numberOfQues")
    int numberOfQues;
    @SerializedName("catName")
    String catName;
    @SerializedName("subs")
    List<SubCat> subs ;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

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

    public List<SubCat> getSubs() {
        return subs;
    }

    public void setSubs(List<SubCat> subs) {
        this.subs = subs;
    }
}
