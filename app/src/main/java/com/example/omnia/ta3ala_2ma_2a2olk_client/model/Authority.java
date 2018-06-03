package com.example.omnia.ta3ala_2ma_2a2olk_client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by omnia on 6/2/2018.
 */

public class Authority {

    @SerializedName("authorityId")
    @Expose
    private Integer authorityId;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
