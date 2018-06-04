package com.example.omnia.ta3ala_2ma_2a2olk_client.model;

import java.io.Serializable;

public class SubCategories implements Serializable {
    private Integer subCatId;
    private String subCatName;
    private String description;

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
