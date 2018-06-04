package com.example.omnia.ta3ala_2ma_2a2olk_client.model;

import java.io.Serializable;
import java.util.List;

public class MainCategories implements Serializable {
    private Integer mainCategoriesId;
    private String catName;
    private List<SubCategories> subCatCollection = null;


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

    public List<SubCategories> getSubCatCollection() {
        return subCatCollection;
    }

    public void setSubCatCollection(List<SubCategories> subCatCollection) {
        this.subCatCollection = subCatCollection;
    }
}
