package com.example.omnia.ta3ala_2ma_2a2olk_client.adaptor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Answer;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Question;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.QuestionAnswerAdapotorPresenter;
import com.example.omnia.ta3ala_2ma_2a2olk_client.view.CompanyQuestionDetails;

import java.util.List;

/**
 * Created by omnia on 6/2/2018.
 */

public class QuestionAnswersAdaptoe extends RecyclerView.Adapter<QuestionAnswersAdaptoe.ViewHolder>{

    private List<Answer> answers;
    Context mcContext;
    CompanyQuestionDetails ii;
    Answer answer;


    public QuestionAnswersAdaptoe(List<Answer> qd,Context c,CompanyQuestionDetails s){
        this.answers=qd;
        this.mcContext=c;
        ii=s;
    }


    @NonNull
    @Override
    public QuestionAnswersAdaptoe.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.answer_list_item, parent, false);

        return new QuestionAnswersAdaptoe.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Answer answer = answers.get(position);
        holder.answer.setText(answer.getAnswer());
        holder.personName.setText(answer.getPersonId().getFirst()+" "+answer.getPersonId().getLast());
        Glide.with(mcContext)
                .load(answer.getPersonId().getImage())
                .into(holder.personImage);

        String userEmail=answers.get(position).getPersonId().getEmail();
        String email=getUserOffAppEmail();

//        if(!email.equals(auerEmail)){
//            holder.editImageView.setVisibility(View.GONE);
//            holder.editTextView.setVisibility(View.GONE);
//            holder.deleteImageView.setVisibility(View.GONE);
//            holder.deleteTextView.setVisibility(View.GONE);
//            holder.horizontalLine1.setVisibility(View.GONE);
//            holder.horizontalLine2.setVisibility(View.GONE);
//        }


        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog(position);
                }
        });

        holder.deleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog(position);
            }
        });

        holder.editImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditDialog(position);
            }
        });
        holder.editTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditDialog(position);
            }
        });
        holder.reportImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.reportTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView answer,personName;
        public ImageView personImage;
        public ImageView editImageView,deleteImageView,reportImageView;
        public TextView editTextView,deleteTextView,reportTextView;
        public View horizontalLine1,horizontalLine2;

        public ViewHolder(View view) {
            super(view);
            answer=(TextView)view.findViewById(R.id.answerId);
            personName=(TextView)view.findViewById(R.id.personName);
            personImage=(ImageView) view.findViewById(R.id.personImage);
            editImageView=(ImageView)view.findViewById(R.id.editimage);
            editTextView=(TextView)view.findViewById(R.id.edittext);
            deleteImageView=(ImageView)view.findViewById(R.id.deleteimage);
            deleteTextView=(TextView)view.findViewById(R.id.deletetext);
            reportImageView=(ImageView)view.findViewById(R.id.reportImage);
            reportTextView=(TextView)view.findViewById(R.id.reportText);
            horizontalLine1=(View)view.findViewById(R.id.view1);
            horizontalLine2=(View)view.findViewById(R.id.view2);
        }
    }

    public String  getUserOffAppEmail(){
        // get user email from sharedpref
        SharedPreferences userDetails = mcContext.getSharedPreferences("LoginPref", Context.MODE_PRIVATE);
        SharredPreferenceManager manager = new SharredPreferenceManager(mcContext);
        String email = manager.getString(userDetails, "email", "no");
        return email;
    }

    public void showDeleteDialog(final int answerPosition){

        AlertDialog alertDialog = new AlertDialog.Builder(mcContext).create();
        alertDialog.setTitle("Delete Answer");
        alertDialog.setMessage("Do you need to delete this answer");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // delete this ansewer from database and recycleview
                        deleteAnswer(answers.get(answerPosition).getAnswersId());
                        answers.remove(answerPosition);
                        notifyItemRemoved(answerPosition);
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"NO",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
          alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void deleteAnswer(int answerId){
        final String token = getToken();
        // send request to delete data
        QuestionAnswerAdapotorPresenter qAAPresenter=new QuestionAnswerAdapotorPresenter();
        qAAPresenter.deleteAnswerPresenter(answerId,token);
        notifyDataSetChanged();
    }

    public void showEditDialog(final int answerPosition){

        LayoutInflater linflater = (LayoutInflater) mcContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final  View view1=linflater.inflate(R.layout.answer_edit_dialog,null);

        final EditText answerEditText=(EditText)view1.findViewById(R.id.editedanswer);
        answerEditText.setText(answers.get(answerPosition).getAnswer());

        AlertDialog alertDialog = new AlertDialog.Builder(mcContext).create();
        alertDialog.setView(view1);
        alertDialog.setTitle("Edit Answer");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SAVE",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // edit this ansewer from database and recycleview
                        String editAnswer =answerEditText.getText().toString();
                        Answer answer=answers.get(answerPosition);
                        answer.setAnswer(editAnswer);
                        Question qq=ii.getQuestion();
                        answer.setQuestionId(qq.getQuestionId());
                        Log.i("qq", qq.getQuestionId().toString());
                        editAnswer(answer);
                        notifyDataSetChanged();
                      }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"CANCEL",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void editAnswer(Answer answer){
        final String token = getToken();
        QuestionAnswerAdapotorPresenter qAAPresenter=new QuestionAnswerAdapotorPresenter();
        qAAPresenter.editAnswerPresenter(answer,token);
    }

    public String getToken(){
         SharedPreferences pref = mcContext.getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        SharredPreferenceManager shM = new SharredPreferenceManager(mcContext);
        final String token = shM.getString(pref, "persontoken", "error");
        return token;
    }
}

