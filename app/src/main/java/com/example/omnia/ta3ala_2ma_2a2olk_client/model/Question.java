package com.example.omnia.ta3ala_2ma_2a2olk_client.model;

/**
 * Created by omnia on 5/22/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {

    @SerializedName("questionId")
    @Expose
    private Integer questionId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("audio")
    @Expose
    private Object audio;
    @SerializedName("video")
    @Expose
    private Object video;
    @SerializedName("rate")
    @Expose
    private Object rate;
    @SerializedName("verified")
    @Expose
    private int verified;
    @SerializedName("isdeleted")
    @Expose
    private Object isdeleted;
    @SerializedName("notifi")
    @Expose
    private Object notifi;
    @SerializedName("questionDate")
    @Expose
    private String questionDate;
    @SerializedName("closed")
    @Expose
    private Object closed;
    @SerializedName("subCatCollection")
    @Expose
    private List<SubCategory> subCatCollection = null;
    @SerializedName("personRateCollection")
    @Expose
    private List<Object> personRateCollection = null;
    @SerializedName("personId")
    @Expose
    private PersonId personId;
    @SerializedName("answersCollection")
    @Expose
    private List<Answer> answersCollection = null;

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Object getAudio() {
        return audio;
    }

    public void setAudio(Object audio) {
        this.audio = audio;
    }

    public Object getVideo() {
        return video;
    }

    public void setVideo(Object video) {
        this.video = video;
    }

    public Object getRate() {
        return rate;
    }

    public void setRate(Object rate) {
        this.rate = rate;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public Object getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Object isdeleted) {
        this.isdeleted = isdeleted;
    }

    public Object getNotifi() {
        return notifi;
    }

    public void setNotifi(Object notifi) {
        this.notifi = notifi;
    }

    public String getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(String questionDate) {
        this.questionDate = questionDate;
    }

    public Object getClosed() {
        return closed;
    }

    public void setClosed(Object closed) {
        this.closed = closed;
    }

    public List<SubCategory> getSubCatCollection() {
        return subCatCollection;
    }

    public void setSubCatCollection(List<SubCategory> subCatCollection) {
        this.subCatCollection = subCatCollection;
    }

    public List<Object> getPersonRateCollection() {
        return personRateCollection;
    }

    public void setPersonRateCollection(List<Object> personRateCollection) {
        this.personRateCollection = personRateCollection;
    }

    public PersonId getPersonId() {
        return personId;
    }

    public void setPersonId(PersonId personId) {
        this.personId = personId;
    }

    public List<Answer> getAnswersCollection() {
        return answersCollection;
    }

    public void setAnswersCollection(List<Answer> answersCollection) {
        this.answersCollection = answersCollection;
    }

}