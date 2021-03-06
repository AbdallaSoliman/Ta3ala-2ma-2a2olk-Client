package com.example.omnia.ta3ala_2ma_2a2olk_client.model;

/**
 * Created by omnia on 6/1/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CompanyQuestionForTitle implements Serializable {

    @SerializedName("questionId")
    @Expose
    private Integer questionId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("numOfAns")
    @Expose
    private Integer numOfAns;
    @SerializedName("verified")
    @Expose
    private int verified;
    @SerializedName("questionDate")
    @Expose
    private String questionDate;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumOfAns() {
        return numOfAns;
    }

    public void setNumOfAns(Integer numOfAns) {
        this.numOfAns = numOfAns;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public String getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(String questionDate) {
        this.questionDate = questionDate;
    }

}
