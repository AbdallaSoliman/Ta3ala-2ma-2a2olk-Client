package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.Search;
import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.adapters.NewsFeedsAdapter;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.NewsFeed;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Question;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.NewsFeedsPresenter;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.SearchPresenter;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.APIService;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements Search.view{
    private RecyclerView recyclerView;
    private List<Question> data;
    private NewsFeedsAdapter adapter;
    private APIService apiInterface;
    private SearchPresenter presenter;
    String query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new SearchPresenter(this);
        Intent intent = getIntent();
        query = intent.getStringExtra("query");
        Toast.makeText(getApplicationContext(),query,Toast.LENGTH_LONG).show();
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view1);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        presenter.loadSearchResults(query,getApplicationContext(),SearchActivity.this);
    }

    @Override
    public void showSearchResults(List<NewsFeed> data) {
        adapter = new NewsFeedsAdapter(data, SearchActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
