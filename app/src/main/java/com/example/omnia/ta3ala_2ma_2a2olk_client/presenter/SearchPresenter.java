package com.example.omnia.ta3ala_2ma_2a2olk_client.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.Search;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.NewsFeed;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.APIService;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter implements Search.presenter {
    Search.view view;
    private APIService apiInterface;
    private List<NewsFeed> data;

    public SearchPresenter(Search.view view) {
        this.view = view;
    }

    @Override
    public void loadSearchResults(final String query, final Context context, Activity activity) {
        apiInterface = ApiClient.getApiClient().create(APIService.class);
        Toast.makeText(context,query+"from presenter", Toast.LENGTH_LONG).show();
        SharedPreferences tokenDetails = context.getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = tokenDetails.edit();
        SharredPreferenceManager manager = new SharredPreferenceManager(context.getApplicationContext());
        String token = manager.getString(tokenDetails, "persontoken", "no");
        Log.e("subcat", "sub" + token);
        retrofit2.Call<List<NewsFeed>> call = apiInterface.search(token, query);
        call.enqueue(new Callback<List<NewsFeed>>() {
            @Override
            public void onResponse(Call<List<NewsFeed>> call, Response<List<NewsFeed>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context,"response success from presenter", Toast.LENGTH_LONG).show();
                    data = response.body();
//                    Log.i("datas", data.get(0).getTitle() + "hii");
                    view.showSearchResults(data);
                }

            }

            @Override
            public void onFailure(Call<List<NewsFeed>> call, Throwable t) {
                String message = t.getMessage();
                Log.d("failuress", message);
            }
        });


    }
}
