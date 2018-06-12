package com.example.omnia.ta3ala_2ma_2a2olk_client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainCategorySpecial {
    @SerializedName("mainCategoriesId")
    @Expose
    private Integer mainCategoriesId;
    @SerializedName("catName")
    @Expose
    private String catName;
    @SerializedName("subCatCollection")
    @Expose
    private List<SubCatCollection> subCatCollection = null;

    public Integer getMainCategoriesId() {
        return mainCategoriesId;
    }

    public void setMainCategoriesId(Integer mainCategoriesId) {
        this.mainCategoriesId = mainCategoriesId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public List<SubCatCollection> getSubCatCollection() {
        return subCatCollection;
    }

    public void setSubCatCollection(List<SubCatCollection> subCatCollection) {
        this.subCatCollection = subCatCollection;
    }
}
