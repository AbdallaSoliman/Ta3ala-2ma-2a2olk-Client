package com.example.omnia.ta3ala_2ma_2a2olk_client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by omnia on 6/2/2018.
 */

public class Answer {

    @SerializedName("answersId")
    @Expose
    private Integer answersId;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("isdeleted")
    @Expose
    private Object isdeleted;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("audio")
    @Expose
    private Object audio;
    @SerializedName("video")
    @Expose
    private Object video;
    @SerializedName("notifi")
    @Expose
    private Object notifi;
    @SerializedName("answersDate")
    @Expose
    private Object answersDate;
    @SerializedName("personId")
    @Expose
    private PersonId personId;

    public Integer getAnswersId() {
        return answersId;
    }

    public void setAnswersId(Integer answersId) {
        this.answersId = answersId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Object getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Object isdeleted) {
        this.isdeleted = isdeleted;
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

    public Object getNotifi() {
        return notifi;
    }

    public void setNotifi(Object notifi) {
        this.notifi = notifi;
    }

    public Object getAnswersDate() {
        return answersDate;
    }

    public void setAnswersDate(Object answersDate) {
        this.answersDate = answersDate;
    }

    public PersonId getPersonId() {
        return personId;
    }

    public void setPersonId(PersonId personId) {
        this.personId = personId;
    }

}
