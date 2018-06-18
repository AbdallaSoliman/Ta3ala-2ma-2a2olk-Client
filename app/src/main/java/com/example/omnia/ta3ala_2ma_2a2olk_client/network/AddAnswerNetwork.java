package com.example.omnia.ta3ala_2ma_2a2olk_client.network;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.omnia.ta3ala_2ma_2a2olk_client.dialog.MyDialog;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Answer;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.TockenReturn;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.AddAnswerPresenter;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.APIService;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.ApiClient;
import com.example.omnia.ta3ala_2ma_2a2olk_client.view.CompanyQuestionDetails;

import okhttp3.CacheControl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by omnia on 6/6/2018.
 */

public class AddAnswerNetwork {



    public void saveAnswerToServerNetwork(Answer answer, String token , final Context context , final int id){

        APIService apiService = ApiClient.getApiClient().create(APIService.class);
        final MyDialog myDialog = new MyDialog();
        myDialog.show(context);
        Call<TockenReturn> call = apiService.saveAnswer(answer, token);
//        abdalla start
        call.request().newBuilder().cacheControl(CacheControl.FORCE_NETWORK);
//        abdalla end
        call.enqueue(new Callback<TockenReturn>() {
            @Override
            public void onResponse(Call<TockenReturn> call, Response<TockenReturn> response) {

                if (response != null){
                    myDialog.dismiss();
                    Intent intent = new Intent(context,CompanyQuestionDetails.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("questionID",id+"");
                    context.startActivity(intent);
                }
//                if(response.body().equals("Add Done successfully")){
//                    Log.i("hh", "onResponse: ");
//                }
            }
            @Override
            public void onFailure(Call<TockenReturn> call, Throwable t) {
            }
        });
    }
}
