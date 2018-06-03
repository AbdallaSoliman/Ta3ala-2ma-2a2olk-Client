package com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces;

import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Answer;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Question;

import java.util.List;

/**
 * Created by omnia on 6/1/2018.
 */

public interface CompanyQuestionDetailsInterface {

    public void setCompanyQuetionDetailsPresenter(Question q, List<Answer> answers);
}
