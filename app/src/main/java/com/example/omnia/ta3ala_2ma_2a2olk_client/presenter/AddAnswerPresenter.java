package com.example.omnia.ta3ala_2ma_2a2olk_client.presenter;

import android.content.Context;

import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Answer;
import com.example.omnia.ta3ala_2ma_2a2olk_client.network.AddAnswerNetwork;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by omnia on 6/6/2018.
 */

public class AddAnswerPresenter {


    AddAnswerNetwork addAnswerNetwork=new AddAnswerNetwork();

    public void saveAnswerToServerPresenter(Answer answer, String token , Context context ,int qid){
        addAnswerNetwork.saveAnswerToServerNetwork(answer,token,context ,qid);
       //adballa start
        FirebaseMessaging.getInstance().subscribeToTopic("QuestionNotifications"+answer.getQuestionId());
//abdalla end
    }
}
