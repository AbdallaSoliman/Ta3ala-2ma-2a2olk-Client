package com.example.omnia.ta3ala_2ma_2a2olk_client.model;

import java.io.Serializable;

/**
 * Created by omnia on 6/2/2018.
 */

public class CustomerService implements Serializable {

    private String service;
    private String joinDate;
    private String expDate;
    private Integer personId;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

}