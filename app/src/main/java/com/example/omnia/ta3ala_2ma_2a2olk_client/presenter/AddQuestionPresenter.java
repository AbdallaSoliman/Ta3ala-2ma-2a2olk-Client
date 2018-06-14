package com.example.omnia.ta3ala_2ma_2a2olk_client.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.MVPInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.MainCategories;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.MainCategory;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Question;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.SubCategories;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.SubCategory;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.APIService;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.ApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HeshamMuhammed on 6/2/2018.
 */

public class AddQuestionPresenter implements MVPInterface.Presenter {

    // omnia samy
    // LOL
    MVPInterface.View view;
    APIService retrofitConnection;

    Call<List<MainCategories>> getAllCategories;
    List<MainCategories> getCategories;

    List<SubCategories> categories;

    HashMap<String, List<String>> categoriesMap, placesMap, customerServciceMap;
    HashMap<String, List<Integer>> categoriesMapId, placesMapId, customerServciceMapId;

    Call<Question> questionCalling;

    Context context;

    public AddQuestionPresenter(MVPInterface.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void showData() {
        retrofitConnection = ApiClient.getApiClient().create(APIService.class);
        getAllCategories = retrofitConnection.mainCategories("");
        getAllCategories = retrofitConnection.mainCategories(getToken());

        getAllCategories.enqueue(new Callback<List<MainCategories>>() {
            @Override
            public void onResponse(Call<List<MainCategories>> call, Response<List<MainCategories>> response) {
                //abdalla start
                if (response.body() != null) {
                getCategories = response.body();

                    for (int y = 0; y < 3; y++) {
                        categories = getCategories.get(y).getSubCatCollection();

                        HashMap<String, List<String>> hashMap = new HashMap<>();
                        HashMap<String, List<Integer>> hashMapId = new HashMap<>();

                        for (int i = 0; i < categories.size(); i++) {
                            SubCategories subCategory = categories.get(i);
                            if (hashMap.get(subCategory.getSubCatName()) == null) {
                                hashMap.put(subCategory.getSubCatName(), new ArrayList<String>());
                                hashMapId.put(subCategory.getSubCatName(), new ArrayList<Integer>());
                                if (subCategory.getDescription() != null) {
                                    hashMap.get(subCategory.getSubCatName()).add(subCategory.getDescription());
                                    hashMapId.get(subCategory.getSubCatName()).add(subCategory.getSubCatId());
                                }
                            } else {
                                if (subCategory.getDescription() != null) {
                                    hashMap.get(subCategory.getSubCatName()).add(subCategory.getDescription());
                                    hashMapId.get(subCategory.getSubCatName()).add(subCategory.getSubCatId());
                                }
                            }
                        }


                        if (y == 0) {
                            categoriesMap = hashMap;
                            categoriesMapId = hashMapId;
                        } else if (y == 1) {
                            placesMap = hashMap;
                            placesMapId = hashMapId;
                        } else if (y == 2) {
                            customerServciceMap = hashMap;
                            customerServciceMapId = hashMapId;
                        }
                    }


                if (response != null) {
                    view.showCategories(categoriesMap, placesMap, customerServciceMap, categoriesMapId, placesMapId, customerServciceMapId);
                }
                }
//abdalla end
            }


            @Override
            public void onFailure(Call<List<MainCategories>> call, Throwable t) {

            }

        });
    }

    @Override
    public void AddQuestion(Question question) {
        retrofitConnection = ApiClient.getApiClient().create(APIService.class);
        questionCalling = retrofitConnection.addQuestion(question, getToken());
        questionCalling.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                if (response != null) {
                    view.isAdded("Question Addedd");
                }
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                view.isAdded("Please Try Again Later");
                view.isAdded(t.getMessage().toString());
            }
        });
    }

    public String getToken() {
        SharredPreferenceManager shM;
        SharedPreferences pref = context.getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        shM = new SharredPreferenceManager(context);
        final String token = shM.getString(pref, "persontoken", "error");
        return token;
    }
}