package com.example.omnia.ta3ala_2ma_2a2olk_client.presenter;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.CompanyQuestionDetailsInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Question;
import com.example.omnia.ta3ala_2ma_2a2olk_client.network.CompanyQuestionDetailsNetwork;
import com.example.omnia.ta3ala_2ma_2a2olk_client.view.CompanyQuestionDetails;

/**
 * Created by omnia on 6/1/2018.
 */

public class CompanyQuestionDetailsPresenter implements CompanyQuestionDetailsInterface {

    CompanyQuestionDetails cQDetails;
    CompanyQuestionDetailsNetwork cQDNetwork=new CompanyQuestionDetailsNetwork(this);

    public CompanyQuestionDetailsPresenter(CompanyQuestionDetails cd){
        this.cQDetails=cd;
    }

    public void getQuestionDetailsPresenter(String Qid,String token){
        cQDNetwork.getQuestionDetailsNetwork(Qid,token);
    }

    public void setCompanyQuetionDetailsPresenter(Question q){
        cQDetails.setQuestionDetails(q);
    }

}
