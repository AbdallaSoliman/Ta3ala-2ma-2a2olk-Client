package com.example.omnia.ta3ala_2ma_2a2olk_client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by omnia on 6/10/2018.
 */

public class Report {

    @SerializedName("reportId")
    @Expose
    private Integer reportId;
    @SerializedName("msg")
    @Expose
    private String  msg;
    @SerializedName("type")
    @Expose
    private String  type;
    @SerializedName("reportDate")
    @Expose
    private String  reportDate;
    @SerializedName("checked")
    @Expose
    private Integer checked;
    @SerializedName("action")
    @Expose
    private Object action;
    @SerializedName("personId")
    @Expose
    private PersonId personId;
    @SerializedName("title")
    @Expose
    private String title;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String  getType() {
        return type;
    }

    public void setType(String  type) {
        this.type = type;
    }

    public String  getReportDate() {
        return reportDate;
    }

    public void setReportDate(String  reportDate) {
        this.reportDate = reportDate;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public Object getAction() {
        return action;
    }

    public void setAction(Object action) {
        this.action = action;
    }

    public PersonId getPersonId() {
        return personId;
    }

    public void setPersonId(PersonId personId) {
        this.personId = personId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
