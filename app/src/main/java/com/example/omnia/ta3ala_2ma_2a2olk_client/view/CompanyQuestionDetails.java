package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.adaptor.CompanyQuestionAdaptor;
import com.example.omnia.ta3ala_2ma_2a2olk_client.adaptor.QuestionAnswersAdaptoe;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Answer;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.PersonId;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Question;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.CompanyQuestionDetailsPresenter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CompanyQuestionDetails extends AppCompatActivity {

    SharredPreferenceManager shM;
    CompanyQuestionDetailsPresenter cQDPresenter;
    Question question;
    TextView titleTextView;
    TextView bodyTextView;
    TextView personNameTextView;
    TextView deleteTextView;
    ImageView deleteImageView;
    ImageView personImageView;
    List<Answer> questionAnswers;
    PersonId person;
    RecyclerView recyclerView;
    QuestionAnswersAdaptoe madaptor;
    String id;
    TextView editTextView;
    ImageView editImageView;

    public void setQuestionDetails(Question q, List<Answer> answers){
        this.question=q;
        questionAnswers.addAll(answers);
        person=question.getPersonId();
        titleTextView.setText(question.getTitle());
        bodyTextView.setText(question.getBody());
        if(person!=null){
            personNameTextView.setText(person.getFirst()+" "+person.getLast());
        Glide.with(this)
                .load(person.getImage())
                    .into(personImageView);
        }
        madaptor.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_question_details);

        questionAnswers=new ArrayList<>();
        titleTextView=(TextView)findViewById(R.id.qTitle);
        bodyTextView=(TextView)findViewById(R.id.qBody);
        personNameTextView=(TextView)findViewById(R.id.personeName);
        personImageView=(ImageView)findViewById(R.id.personImage);

        cQDPresenter=new CompanyQuestionDetailsPresenter(this);

        final String token = getToken();

        Intent intent = getIntent();
        id = intent.getStringExtra("questionID");

        cQDPresenter.getQuestionDetailsPresenter(id,token);

        // recycleview
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        madaptor = new QuestionAnswersAdaptoe(questionAnswers,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(madaptor);


        // delete&edit
        deleteTextView=(TextView)findViewById(R.id.deleteText);
        deleteImageView=(ImageView)findViewById(R.id.deleteImage);

        deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog();
            }
        });

        deleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog();
            }
        });

        editTextView=(TextView)findViewById(R.id.editText);
        editImageView=(ImageView)findViewById(R.id.editImage);

        editImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditDialog(question);
            }
        });

        editTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditDialog(question);
            }
        });

    }

    public void showDeleteDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Delete Question");
        alertDialog.setMessage("Do you need to delete this Question");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        cQDPresenter.deleteQuestionPresenter(id,getToken());
                         finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"NO",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void showEditDialog(final Question question){

        final  View view1=getLayoutInflater().inflate(R.layout.question_edit_dialog,null);
        final EditText titleEditText=(EditText)view1.findViewById(R.id.editQuestionTitle);
        final EditText bodyEditText=(EditText)view1.findViewById(R.id.editQuestionBody);
      //  final Button saveButton=(Button)view1.findViewById(R.id.saveChanges);
        titleEditText.setText(question.getTitle());
        bodyEditText.setText(question.getBody());

        final AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("Edit Question");
        dialog.setView(view1)

                .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
//                        if(titleEditText.getText().toString().trim().isEmpty()){
//                          titleEditText.setError("please enter data");
//                        }
//                        else if (bodyEditText.getText().toString().trim().isEmpty()){
//                            bodyEditText.setError("please enter data");
//                        }
//                        else{
//                            titleTextView.setText(titleEditText.getText().toString());
//                            bodyTextView.setText(bodyEditText.getText().toString());
//                            question.setTitle(titleEditText.getText().toString());
//                            question.setBody(bodyEditText.getText().toString());
//                            String token=getToken();
//                            cQDPresenter.editQuestionPresenter(question,token);
//
//                        }

                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false);
        final AlertDialog alert=dialog.create();
        alert.show();
        alert.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(titleEditText.getText().toString().trim().isEmpty()){
                    titleEditText.setError("please enter data");
                }
                else if (bodyEditText.getText().toString().trim().isEmpty()){
                    bodyEditText.setError("please enter data");
                }
                else{
                    titleTextView.setText(titleEditText.getText().toString());
                    bodyTextView.setText(bodyEditText.getText().toString());
                    question.setTitle(titleEditText.getText().toString());
                    question.setBody(bodyEditText.getText().toString());
                    String token=getToken();
                    cQDPresenter.editQuestionPresenter(question,token);
                    alert.dismiss();
                }

            }
        });



    }

    public String getToken(){
        SharedPreferences pref = this.getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        shM = new SharredPreferenceManager(this);
        final String token = shM.getString(pref, "persontoken", "error");
        return token;
    }

}
