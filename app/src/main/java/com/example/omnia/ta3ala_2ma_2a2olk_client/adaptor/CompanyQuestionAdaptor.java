package com.example.omnia.ta3ala_2ma_2a2olk_client.adaptor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.CompanyQuestionForTitle;

import java.util.List;

/**
 * Created by omnia on 5/31/2018.
 */

public class CompanyQuestionAdaptor extends RecyclerView.Adapter<CompanyQuestionAdaptor.ViewHolder>{

    private List<CompanyQuestionForTitle> questionsData;

    public CompanyQuestionAdaptor(List<CompanyQuestionForTitle> qd){
       this.questionsData=qd;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CompanyQuestionForTitle companyQuestionsData = questionsData.get(position);
        holder.Qtitle.setText(companyQuestionsData.getTitle());
        holder.Qdate.setText(companyQuestionsData.getQuestionDate());
        holder.Qacount.setText(String.valueOf(companyQuestionsData.getNumOfAns()));
    }

    @Override
    public int getItemCount() {
        return questionsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView Qtitle,Qdate,Qacount;

        public ViewHolder(View view) {
            super(view);
            Qtitle=(TextView)view.findViewById(R.id.QTitle);
            Qdate=(TextView)view.findViewById(R.id.QDate);
            Qacount=(TextView)view.findViewById(R.id.QACount);
        }
    }
}
