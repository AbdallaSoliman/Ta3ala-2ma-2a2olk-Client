package com.example.omnia.ta3ala_2ma_2a2olk_client.adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Question;
import com.squareup.picasso.Picasso;

import java.text.Bidi;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NewsFeedsAdapter extends RecyclerView.Adapter<NewsFeedsAdapter.ViewHolder> {
    List<Question> questions;

    public NewsFeedsAdapter(List<Question> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public NewsFeedsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsfeeds_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsFeedsAdapter.ViewHolder holder, int position) {
        holder.tv_head.setText(questions.get(position).getTitle());
        holder.tv_body.setText(questions.get(position).getQuestionDate());
        holder.QACount.setText(questions.get(position).getQuestionId() + "");
//abdalla start
        if (questions.get(position).getVerified()==1) {
            holder.QACount.setTextColor(Color.parseColor("#ffffff"));
            holder.QACount.setBackgroundResource(R.drawable.rounded_rectangle_with_backcolor);
        }
        AssetManager am = holder.tv_body.getContext().getApplicationContext().getAssets();
        holder.itemView.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        Typeface custom_font_Hedder = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "al_jazeera_arabic_regular.ttf"));


        Bidi bidi = new Bidi(questions.get(position).getTitle(), Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT);
        if (bidi.baseIsLeftToRight()) {
            holder.itemView.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        } else {
            holder.itemView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            holder.tv_head.setTypeface(custom_font_Hedder);
        }
//abdalla end
        //Picasso.get().load(questions.get(position).getImage()).into(holder.logo);

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_head, tv_body, QACount;
        ImageView logo;

        public ViewHolder(View view) {
            super(view);
            tv_head = (TextView) view.findViewById(R.id.QTitle);
            tv_body = (TextView) view.findViewById(R.id.QDate);
            QACount = (TextView) view.findViewById(R.id.QACount);
            //  logo = (ImageView) view.findViewById(R.id.cardview_image);

        }
    }
}
