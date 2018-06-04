package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.content.Context;
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

import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.adaptor.CompanyQuestionAdaptor;
import com.example.omnia.ta3ala_2ma_2a2olk_client.adaptor.RecyclerTouchListener;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.CompanyQuestionForTitle;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.CompanyQuestionsListPresenter;

import java.util.ArrayList;
import java.util.List;

public class CompanyQuestionsList extends AppCompatActivity {

    String id;
      private RecyclerView recyclerView;
      private CompanyQuestionAdaptor mAdapter;
      SharredPreferenceManager shM;
      CompanyQuestionsListPresenter cQPresenter;
      private List<CompanyQuestionForTitle> questionForTitlesList;

      public void setCompaniesQuestionList(List<CompanyQuestionForTitle> qftlist){
          this.questionForTitlesList.addAll(qftlist);
          mAdapter.notifyDataSetChanged();
      }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_questions_list);

        cQPresenter=new CompanyQuestionsListPresenter(this);

        Intent intent = getIntent();
        id = intent.getStringExtra("companyId");

        // get token from sharedpref
        SharedPreferences pref = this.getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        shM = new SharredPreferenceManager(this);
        final String token = shM.getString(pref, "persontoken", "error");
        cQPresenter.getCompaniesQuestionsPresenter(id,token);

        // recycleview
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        questionForTitlesList=new ArrayList<>();
        mAdapter = new CompanyQuestionAdaptor(questionForTitlesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {

                Intent intent2=new Intent(CompanyQuestionsList.this,CompanyQuestionDetails.class);
                intent2.putExtra("questionID",String.valueOf(questionForTitlesList.get(position).getQuestionId()));
                startActivity(intent2);
               }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        cQPresenter=new CompanyQuestionsListPresenter(this);

         // get token from sharedpref
        SharedPreferences pref = this.getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        shM = new SharredPreferenceManager(this);
        final String token = shM.getString(pref, "persontoken", "error");
        cQPresenter.getCompaniesQuestionsPresenter(id,token);

        // recycleview
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        questionForTitlesList=new ArrayList<>();
        mAdapter = new CompanyQuestionAdaptor(questionForTitlesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {

                Intent intent2=new Intent(CompanyQuestionsList.this,CompanyQuestionDetails.class);
                intent2.putExtra("questionID",String.valueOf(questionForTitlesList.get(position).getQuestionId()));
                startActivity(intent2);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}

