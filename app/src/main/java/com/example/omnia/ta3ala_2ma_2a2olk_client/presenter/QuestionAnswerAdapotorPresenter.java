package com.example.omnia.ta3ala_2ma_2a2olk_client.presenter;

import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Answer;
import com.example.omnia.ta3ala_2ma_2a2olk_client.network.QuestionAnswerAdapotorNetwork;

/**
 * Created by omnia on 6/4/2018.
 */

public class QuestionAnswerAdapotorPresenter {

    QuestionAnswerAdapotorNetwork qAAnetwork=new QuestionAnswerAdapotorNetwork();


    public void deleteAnswerPresenter(int answerId,String token){
        qAAnetwork.deleteAnswerNetwork(answerId,token);
    }
    public void editAnswerPresenter(Answer answer,String token){
        qAAnetwork.editAnswerNetwork(answer,token);
    }
}
