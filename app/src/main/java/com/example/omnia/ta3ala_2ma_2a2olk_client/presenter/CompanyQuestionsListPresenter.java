package com.example.omnia.ta3ala_2ma_2a2olk_client.presenter;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.ComapnyQuestionListInterface;

import com.example.omnia.ta3ala_2ma_2a2olk_client.model.CompanyQuestionForTitle;
import com.example.omnia.ta3ala_2ma_2a2olk_client.network.CompanyQuestionsListNetwork;
import com.example.omnia.ta3ala_2ma_2a2olk_client.view.CompanyQuestionsList;

import java.util.List;

/**
 * Created by omnia on 5/31/2018.
 */

public class CompanyQuestionsListPresenter implements ComapnyQuestionListInterface{

    CompanyQuestionsList cQList;
    CompanyQuestionsListNetwork cQListNetwork=new CompanyQuestionsListNetwork(this);

    public CompanyQuestionsListPresenter(CompanyQuestionsList qcl){
        this.cQList=qcl;
    }

    public void getCompaniesQuestionsPresenter(String Qid,String token){
        cQListNetwork.getCompaniesQuestionNetwork(Qid,token);
    }

    public void setCompanyQuetionListPresenter(List<CompanyQuestionForTitle> q){

        cQList.setCompaniesQuestionList(q);
    }


}
