package com.example.omnia.ta3ala_2ma_2a2olk_client.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.TabHomeInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.adapters.MyBaseExpandableListAdapter;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.MainCategories;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.SubCategories;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.APIService;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.ApiClient;
import com.example.omnia.ta3ala_2ma_2a2olk_client.view.CompanyQuestionDetails;
import com.example.omnia.ta3ala_2ma_2a2olk_client.view.CompanyQuestionsList;
import com.example.omnia.ta3ala_2ma_2a2olk_client.view.Tab1Home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomePresenter implements TabHomeInterface.presenter , ExpandableListView.OnChildClickListener {
    MyBaseExpandableListAdapter myBaseExpandableListAdapter;
    ExpandableListView myExpandableListView;
    List<String> myListForGroup;
    HashMap<String, List<String>> myMapForChild;
    HashMap<String, List<String>> categoriesMap;
    HashMap<String, List<Integer>> categoriesMapId;


    private APIService apiInterface;
    List<com.example.omnia.ta3ala_2ma_2a2olk_client.model.MainCategories> getCategories;
    List<SubCategories> categories;
    List<String> MainCategories = new ArrayList<>();

    int mainNumber, subNumber;

    TabHomeInterface.view view;
    Activity activity;
    Context mContext;

    public HomePresenter(TabHomeInterface.view view , Activity activity , Context mContext) {
        this.view = view;
        this.activity = activity;
        this.mContext = mContext;
    }

    @Override
    public void initData(final Context mContext) {
        apiInterface = ApiClient.getApiClient().create(APIService.class);
        SharedPreferences tokenDetails = mContext.getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = tokenDetails.edit();
        SharredPreferenceManager manager = new SharredPreferenceManager(mContext.getApplicationContext());
        String token = manager.getString(tokenDetails, "persontoken", "no");
        Log.e("subcat", "sub" + token);
        Call<List<MainCategories>> call = apiInterface.mainCategories(token);
        call.enqueue(new Callback<List<MainCategories>>() {
            @Override
            public void onResponse(Call<List<MainCategories>> call, Response<List<MainCategories>> response) {
                if (response.isSuccessful()) {
                    getCategories = response.body();
                    for (int y = 0; y < 1; y++) {
                        categories = getCategories.get(y).getSubCatCollection();
                        HashMap<String, List<String>> hashMap = new HashMap<>();
                        HashMap<String, List<Integer>> hashId = new HashMap<>();
                        for (int i = 0; i < categories.size(); i++) {
                            SubCategories subCategory = categories.get(i);
                            if (hashMap.get(subCategory.getSubCatName()) == null) {
                                hashMap.put(subCategory.getSubCatName(), new ArrayList<String>());
                                hashId.put(subCategory.getSubCatName(), new ArrayList<Integer>());
                                if (subCategory.getDescription() != null) {
                                    hashMap.get(subCategory.getSubCatName()).add(subCategory.getDescription());
                                    hashId.get(subCategory.getSubCatName()).add(subCategory.getSubCatId());
                                    MainCategories.add(subCategory.getSubCatName());
                                }
                            } else {
                                if (subCategory.getDescription() != null) {
                                    hashMap.get(subCategory.getSubCatName()).add(subCategory.getDescription());
                                    hashId.get(subCategory.getSubCatName()).add(subCategory.getSubCatId());
                                }
                            }
                        }
                        if (y == 0) {
                            categoriesMap = hashMap;
                            categoriesMapId = hashId;

                        }
                    }
                }
                myBaseExpandableListAdapter = new MyBaseExpandableListAdapter(mContext, MainCategories, categoriesMap, categoriesMapId);
                myExpandableListView = (ExpandableListView) activity.findViewById(R.id.myexpandablelistview);
                myExpandableListView.setAdapter(myBaseExpandableListAdapter);
                myExpandableListView.setOnChildClickListener(HomePresenter.this);
            }

            @Override
            public void onFailure(Call<List<MainCategories>> call, Throwable t) {
                Toast.makeText(mContext.getApplicationContext(), "Register failed ", Toast.LENGTH_LONG).show();
                String message = t.getMessage();
                Log.d("failuress", message);
            }
        });


    }

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
        mainNumber = i;
        subNumber = i1;
        Log.e("mytag","value is "+(categoriesMapId.get(MainCategories.get(i))).get(i1));
        int id = categoriesMapId.get(MainCategories.get(i)).get(i1);
         transferToIntent(mContext , id);
        return true;
    }

    private void transferToIntent(Context mContext, int id) {
        Intent intent = new Intent(mContext, CompanyQuestionsList.class);
        intent.putExtra("companyId",id);
        mContext.startActivity(intent);
    }

}
