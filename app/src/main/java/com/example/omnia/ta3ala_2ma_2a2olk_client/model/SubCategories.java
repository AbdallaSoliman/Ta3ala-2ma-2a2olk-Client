package com.example.omnia.ta3ala_2ma_2a2olk_client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SubCategories implements Serializable {
    @SerializedName("subCatId")
    @Expose
    private Integer subCatId;
    @SerializedName("subCatName")
    @Expose
    private String subCatName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(Integer subCatId) {
        this.subCatId = subCatId;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
