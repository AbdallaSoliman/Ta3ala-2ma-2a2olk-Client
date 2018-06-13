package com.example.omnia.ta3ala_2ma_2a2olk_client.adaptor;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.Test2Listener;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.CompanyQuestionForTitle;

import java.text.Bidi;
import java.util.List;
import java.util.Locale;

/**
 * Created by omnia on 5/31/2018.
 */

public class CompanyQuestionAdaptor extends RecyclerView.Adapter<CompanyQuestionAdaptor.ViewHolder>{

    private List<CompanyQuestionForTitle> questionsData;
    private Test2Listener listener;

    public CompanyQuestionAdaptor(List<CompanyQuestionForTitle> qd,Test2Listener listener){
       this.questionsData=qd;
       this.listener=listener;
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

        holder.setitemPosition(position);
        CompanyQuestionForTitle companyQuestionsData = questionsData.get(position);
        holder.Qtitle.setText(companyQuestionsData.getTitle());
        holder.Qdate.setText(companyQuestionsData.getQuestionDate());
        holder.Qacount.setText(String.valueOf(companyQuestionsData.getNumOfAns()));

            if (companyQuestionsData.getVerified()==1) {
                holder.Qacount.setTextColor(Color.parseColor("#ffffff"));
                holder.Qacount.setBackgroundResource(R.drawable.rounded_rectangle_with_backcolor);
            }
//abdalla start
        AssetManager am = holder.Qtitle.getContext().getApplicationContext().getAssets();
        holder.itemView.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        Typeface custom_font_Hedder = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "al_jazeera_arabic_regular.ttf"));




        Bidi bidi = new Bidi(companyQuestionsData.getTitle(), Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT);
        if(bidi.baseIsLeftToRight())
            holder.itemView.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
         else {
            holder.itemView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            holder.Qtitle.setTypeface(custom_font_Hedder);
        }
//abdalla end


    }

    @Override
    public int getItemCount() {
        return questionsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView Qtitle,Qdate,Qacount;
        int itemPosition;

        public ViewHolder(View view) {
            super(view);
            Qtitle=(TextView)view.findViewById(R.id.QTitle);
            Qdate=(TextView)view.findViewById(R.id.QDate);
            Qacount=(TextView)view.findViewById(R.id.QACount);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(itemPosition);
                }
            });
        }

        public void setitemPosition(int po){
            this.itemPosition=po;
        }
        public int getitemPosition(){
            return itemPosition;
        }
    }
}
