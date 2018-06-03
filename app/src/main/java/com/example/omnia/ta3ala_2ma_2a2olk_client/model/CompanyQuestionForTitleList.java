package com.example.omnia.ta3ala_2ma_2a2olk_client.model;

/**
 * Created by omnia on 6/1/2018.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyQuestionForTitleList {

    @SerializedName("questions")
    @Expose
    private List<CompanyQuestionForTitle> questions = null;

    public List<CompanyQuestionForTitle> getQuestions() {
        return questions;
    }

    public void setQuestions(List<CompanyQuestionForTitle> questions) {
        this.questions = questions;
    }

}
