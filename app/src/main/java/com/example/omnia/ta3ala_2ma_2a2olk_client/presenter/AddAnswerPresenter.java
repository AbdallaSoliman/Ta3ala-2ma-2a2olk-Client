package com.example.omnia.ta3ala_2ma_2a2olk_client.presenter;

import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Answer;
import com.example.omnia.ta3ala_2ma_2a2olk_client.network.AddAnswerNetwork;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by omnia on 6/6/2018.
 */

public class AddAnswerPresenter {


    AddAnswerNetwork addAnswerNetwork=new AddAnswerNetwork();

    public void saveAnswerToServerPresenter(Answer answer, String token){
        addAnswerNetwork.saveAnswerToServerNetwork(answer,token);
       //adballa start
        FirebaseMessaging.getInstance().subscribeToTopic("QuestionNotifications"+answer.getQuestionId());
//abdalla end
    }
}
