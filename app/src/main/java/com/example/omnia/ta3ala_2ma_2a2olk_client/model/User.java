package com.example.omnia.ta3ala_2ma_2a2olk_client.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {


    @SerializedName("personId")
    private int personId;
    @SerializedName("image")
    private String image;
    @SerializedName("first")
    private String first;
    @SerializedName("last")
    private String last;
    @SerializedName("password")
    private String password;
    @SerializedName("email")
    private String email;
    @SerializedName("type")
    private String type;
    @SerializedName("gender")
    private String gender;
    @SerializedName("enabled")
    private Boolean enabled;
    @SerializedName("customerService")
    private String customerService;
    //    @SerializedName("taaUser")
//    private String taaUser;
    @SerializedName("username")
    private String username;
    @SerializedName("taaUser")
    private Tauser taaUser;



    public User() {
    }

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public User(String first, String last, String password, String email, String type, String gender, Boolean enabled, String username) {
        this.first = first;
        this.last = last;
        this.password = password;
        this.email = email;
        this.type = type;
        this.gender = gender;
        this.enabled = enabled;
        this.username = username;
    }

    public User(int personId, String image, String first, String last, String password, String email, String type, String gender, Boolean enabled, String username) {
        this.personId = personId;
        this.image = image;
        this.first = first;
        this.last = last;
        this.password = password;
        this.email = email;
        this.type = type;
        this.gender = gender;
        this.enabled = enabled;
        this.username = username;
    }

    public Tauser getTaaUser() {
        return taaUser;
    }

    public void setTaaUser(Tauser taaUser) {
        this.taaUser = taaUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
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

    public String getCustomerService() {
        return customerService;
    }

    public void setCustomerService(String customerService) {
        this.customerService = customerService;
    }


}
