package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.adaptor.CompaniesAdaptor;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.SubCategory;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.Tab2CustomerServicePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omnia on 5/28/2018.
 */

public class Tab2CustomerService extends Fragment {

    SharredPreferenceManager shM;
    List<SubCategory> companies;
    CompaniesAdaptor adapter;
    Tab2CustomerServicePresenter tSP;
    ListView listView;

    public void setCompanies(List<SubCategory> cList){
        this.companies.clear();
        this.companies.addAll(cList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("lifeCycle", "onCreate: ");

        tSP=new Tab2CustomerServicePresenter(this);
        SharedPreferences pref = this.getActivity().getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        shM = new SharredPreferenceManager(this.getActivity());
        String token = shM.getString(pref, "persontoken", "error");
        Log.i("token", token);
        // get data
       // tSP.getCompaniesPresenter(token);
    }

    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("lifeCycle", "onCreateView: ");

        View rootView = inflater.inflate(R.layout.tab2, container, false);

        companies = new ArrayList<>();
        adapter = new CompaniesAdaptor(this.getActivity(), companies);
        listView = (ListView) rootView.findViewById(R.id.companiesList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // open new acticity to show questions
                Intent intent=new Intent(getActivity(),CompanyQuestionsList.class);
                String id=String.valueOf(companies.get(position).getSubCatId());
                intent.putExtra("companyId",id);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            getData();
        } else {

        }
    }

    public void getData(){

        SharedPreferences pref = this.getActivity().getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        shM = new SharredPreferenceManager(this.getActivity());
        String token = shM.getString(pref, "persontoken", "error");
        tSP.getCompaniesPresenter(token);
    }
}
