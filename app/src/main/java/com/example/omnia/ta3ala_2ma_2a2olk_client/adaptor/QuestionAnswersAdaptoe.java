package com.example.omnia.ta3ala_2ma_2a2olk_client.adaptor;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Answer;
import java.util.List;

/**
 * Created by omnia on 6/2/2018.
 */

public class QuestionAnswersAdaptoe extends RecyclerView.Adapter<QuestionAnswersAdaptoe.ViewHolder>{

    private List<Answer> answers;
    Context mcContext;

    public QuestionAnswersAdaptoe(List<Answer> qd,Context c){
        this.answers=qd;
        this.mcContext=c;
    }

    @NonNull
    @Override
    public QuestionAnswersAdaptoe.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.answer_list_item, parent, false);

        return new QuestionAnswersAdaptoe.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Answer answer = answers.get(position);
        holder.answer.setText(answer.getAnswer());
        holder.personName.setText(answer.getPersonId().getFirst()+" "+answer.getPersonId().getLast());
        Glide.with(mcContext)
                .load(answer.getPersonId().getImage())
                .into(holder.personImage);

        String email=getUserOffAppEmail();
        Log.i("emailo", email);

    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView answer,personName;
        public ImageView personImage;

        public ViewHolder(View view) {
            super(view);
            answer=(TextView)view.findViewById(R.id.answerId);
            personName=(TextView)view.findViewById(R.id.personName);
            personImage=(ImageView) view.findViewById(R.id.personImage);
        }
    }

    public String  getUserOffAppEmail(){
        // get user email from sharedpref
        SharedPreferences userDetails = mcContext.getSharedPreferences("LoginPref", Context.MODE_PRIVATE);
        SharredPreferenceManager manager = new SharredPreferenceManager(mcContext);
        String email = manager.getString(userDetails, "email", "no");
        return email;
    }
}

