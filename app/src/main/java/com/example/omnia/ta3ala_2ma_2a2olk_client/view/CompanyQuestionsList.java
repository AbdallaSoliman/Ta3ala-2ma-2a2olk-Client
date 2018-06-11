package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
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
import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.Test2Listener;
import com.example.omnia.ta3ala_2ma_2a2olk_client.adaptor.CompanyQuestionAdaptor;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.CompanyQuestionForTitle;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.CompanyQuestionsListPresenter;

import java.util.ArrayList;
import java.util.List;

public class CompanyQuestionsList extends AppCompatActivity implements Test2Listener {

      String id;
      private RecyclerView recyclerView;
      private CompanyQuestionAdaptor mAdapter;
      SharredPreferenceManager shM;
      CompanyQuestionsListPresenter cQPresenter;
      private List<CompanyQuestionForTitle> questionForTitlesList;

    // for back button on action bar
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void setCompaniesQuestionList(List<CompanyQuestionForTitle> qftlist){
          this.questionForTitlesList.addAll(qftlist);
          mAdapter.notifyDataSetChanged();
      }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_questions_list);

        //circle button

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Intent intent = new Intent(getApplicationContext(), AddQuestion.class);
//                startActivity(intent);
                SharedPreferences userDetails = getSharedPreferences("LoginPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = userDetails.edit();
                SharredPreferenceManager manager = new SharredPreferenceManager(getApplicationContext());
                String email = manager.getString(userDetails, "email", "no");
                // Toast.makeText(getApplicationContext(), "email is " + email, Toast.LENGTH_LONG).show();
                Log.e("prefMail",email);
                if (email.equals("no")) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), AddQuestion.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });

        // for back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        mAdapter = new CompanyQuestionAdaptor(questionForTitlesList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

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
        mAdapter = new CompanyQuestionAdaptor(questionForTitlesList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(int po) {
        Intent intent2=new Intent(CompanyQuestionsList.this,CompanyQuestionDetails.class);
        intent2.putExtra("questionID",String.valueOf(questionForTitlesList.get(po).getQuestionId()));
        startActivity(intent2);
    }
}

