package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.adapters.MyBaseExpandableListAdapter;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.MainCat;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.SubCat;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.User;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.APIService;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.ApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by omnia on 5/28/2018.
 */

public class Tab1Home extends Fragment {
    MyBaseExpandableListAdapter myBaseExpandableListAdapter;
    ExpandableListView myExpandableListView;
    List<String> myListForGroup;
    HashMap<String, List<String>> myMapForChild;
    private APIService apiInterface;

    public Tab1Home() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);
        myExpandableListView = (ExpandableListView)
                rootView.findViewById(R.id.myexpandablelistview);
        initData();

        myBaseExpandableListAdapter = new
                MyBaseExpandableListAdapter(rootView.getContext(), myListForGroup, myMapForChild);

        myExpandableListView.setAdapter(myBaseExpandableListAdapter);
        return rootView;
    }

    private void initData() {
        myListForGroup = new ArrayList<String>();
        myMapForChild = new HashMap<String, List<String>>();

        final List<String> listGroupA = new ArrayList<String>();
        listGroupA.add("Electronics");
        listGroupA.add("Heath and Care");
        listGroupA.add("Science");
        listGroupA.add("Family");
        listGroupA.add("Entertainment");
        listGroupA.add("Language");
        List<String> listGroupB = new ArrayList<String>();
        listGroupB.add("B - 1");

        List<String> listGroupC = new ArrayList<String>();
        listGroupC.add("C - 1");
        listGroupC.add("C - 2");

        myListForGroup.add("Group A");
        myListForGroup.add("Group B");
        myListForGroup.add("Group C");

        myMapForChild.put(myListForGroup.get(0), listGroupA);
        myMapForChild.put(myListForGroup.get(1), listGroupB);
        myMapForChild.put(myListForGroup.get(2), listGroupC);
        apiInterface = ApiClient.getApiClient().create(APIService.class);
        SharedPreferences tokenDetails = getActivity().getApplicationContext().getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = tokenDetails.edit();
        SharredPreferenceManager manager = new SharredPreferenceManager(getActivity().getApplicationContext());
        String token = manager.getString(tokenDetails, "persontoken", "no");
        Log.e("subcat", "sub" + token);
//        Call<User> call = apiInterface.loginUserWithMail("application/json" ,token , user);
        Call<List<MainCat>> call = apiInterface.getCategories("application/json", token);
        call.enqueue(new Callback<List<MainCat>>() {
            @Override
            public void onResponse(Call<List<MainCat>> call, Response<List<MainCat>> response) {
                if (response.isSuccessful()) {
                    String name = response.body().get(0).getCatName();
                    //                    Log.e("subcat2", "sub" + m1.get(0).getCatName().toString());
                    for (int i = 0; i < response.body().size(); i++) {
                        myListForGroup.add(response.body().get(i).getCatName());
                        // Log.i("tag","data"+response.body().get(i).getSubs().get(i+1).getCatName());
                        // myMapForChild.put(my)
                    }
                    Toast.makeText(getActivity().getApplicationContext(), "sub" + name, Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "error1", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<List<MainCat>> call, Throwable t) {
                String message = t.getMessage();
                Log.d("failure", message);
            }
        });
    }


}

