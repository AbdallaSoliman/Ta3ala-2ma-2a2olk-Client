package com.example.omnia.ta3ala_2ma_2a2olk_client.model;

/**
 * Created by omnia on 5/22/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question {

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
    private Object image;
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
    private Object verified;
    @SerializedName("isdeleted")
    @Expose
    private Object isdeleted;
    @SerializedName("notifi")
    @Expose
    private Object notifi;
    @SerializedName("questionDate")
    @Expose
    private Object questionDate;
    @SerializedName("closed")
    @Expose
    private Object closed;
    @SerializedName("idQuestion")
    @Expose
    private Object idQuestion;
    @SerializedName("subCatCollection")
    @Expose
    private List<Object> subCatCollection = null;
    @SerializedName("taaUserCollection")
    @Expose
    private List<Object> taaUserCollection = null;
    @SerializedName("personId")
    @Expose
    private Object personId;
    @SerializedName("answersCollection")
    @Expose
    private List<Object> answersCollection = null;

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

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
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

    public Object getVerified() {
        return verified;
    }

    public void setVerified(Object verified) {
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

    public Object getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(Object questionDate) {
        this.questionDate = questionDate;
    }

    public Object getClosed() {
        return closed;
    }

    public void setClosed(Object closed) {
        this.closed = closed;
    }

    public Object getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Object idQuestion) {
        this.idQuestion = idQuestion;
    }

    public List<Object> getSubCatCollection() {
        return subCatCollection;
    }

    public void setSubCatCollection(List<Object> subCatCollection) {
        this.subCatCollection = subCatCollection;
    }

    public List<Object> getTaaUserCollection() {
        return taaUserCollection;
    }

    public void setTaaUserCollection(List<Object> taaUserCollection) {
        this.taaUserCollection = taaUserCollection;
    }

    public Object getPersonId() {
        return personId;
    }

    public void setPersonId(Object personId) {
        this.personId = personId;
    }

    public List<Object> getAnswersCollection() {
        return answersCollection;
    }

    public void setAnswersCollection(List<Object> answersCollection) {
        this.answersCollection = answersCollection;
    }

}
