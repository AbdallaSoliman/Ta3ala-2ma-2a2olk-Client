package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Answer;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.PersonId;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Question;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.AddAnswerPresenter;

public class AddAnswer extends AppCompatActivity {

    EditText answerEditText;
    Button postAnswer;
    Question qId;
    SharredPreferenceManager shM;

    // for back button on action bar
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_answer);

        // for back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Answer");
        Intent intent=getIntent();
        qId=(Question) intent.getSerializableExtra("questionId");

        answerEditText=(EditText)findViewById(R.id.answerId);
        postAnswer=(Button)findViewById(R.id.saveAnswer);

//        postAnswer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String answerText=answerEditText.getText().toString();
//
//                if(!answerText.trim().isEmpty()){
//
//                    // save answer to server
//                    Answer answer=new Answer();
//                    answer.setAnswer(answerText);
//                    answer.setQuestionId(qId.getQuestionId());
//                    PersonId p=new PersonId();
//                    //get person object from database
//                    p.setPersonId(1);
//                    answer.setPersonId(p);
//                    AddAnswerPresenter addAnswerPresenter=new AddAnswerPresenter();
//                    addAnswerPresenter.saveAnswerToServerPresenter(answer,getToken());
//                    Intent intent1=new Intent(this,CompanyQuestionDetails.class);
//                    finish();
//                }
//                else{
//                    answerEditText.setError("please enter data");
//                }
//            }
//        });
    }

    public void saveAnswer(View view){

        String answerText=answerEditText.getText().toString();

        if(!answerText.trim().isEmpty()){

            // save answer to server
            Answer answer=new Answer();
            answer.setAnswer(answerText);
            answer.setQuestionId(qId.getQuestionId());
            PersonId p=new PersonId();
            //get person object from database
            p.setPersonId(Integer.parseInt(getPersonId()));
            answer.setPersonId(p);
            AddAnswerPresenter addAnswerPresenter=new AddAnswerPresenter();
            addAnswerPresenter.saveAnswerToServerPresenter(answer,getToken());

            finish();
        }
        else{
            answerEditText.setError("please enter data");
        }
    }


    public String getToken(){
        SharedPreferences pref = this.getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        shM = new SharredPreferenceManager(this);
        final String token = shM.getString(pref, "persontoken", "error");
        return token;
    }

    public String getPersonId(){

        SharedPreferences pref = this.getSharedPreferences("LoginPref", Context.MODE_PRIVATE);
        shM = new SharredPreferenceManager(this);
        String personId = shM.getString(pref, "id", "error");
        return personId;
    }
}
