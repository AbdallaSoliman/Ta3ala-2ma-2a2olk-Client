package com.example.omnia.ta3ala_2ma_2a2olk_client.model;

import java.io.Serializable;

/**
 * Created by HeshamMuhammed on 6/18/2018.
 */

public class SpecialUser implements Serializable{
    private Integer personId;
    private String image;
    private String first;
    private String last;
    private String password;
    private String email;
    private String type;
    private String gender;
    private Boolean enabled;
    private String username;
    private Object lastPasswordResetDate;
    private Integer numOfAskedQuestions;
    private Tauser taaUser;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Object lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public Integer getNumOfAskedQuestions() {
        return numOfAskedQuestions;
    }

    public void setNumOfAskedQuestions(Integer numOfAskedQuestions) {
        this.numOfAskedQuestions = numOfAskedQuestions;
    }

    public Tauser getTaaUser() {
        return taaUser;
    }

    public void setTaaUser(Tauser taaUser) {
        this.taaUser = taaUser;
    }
}
