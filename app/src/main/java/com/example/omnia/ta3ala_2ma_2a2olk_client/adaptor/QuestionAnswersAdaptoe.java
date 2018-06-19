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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.MVPInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Answer;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.PersonId;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Question;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Report;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.QuestionAnswerAdapotorPresenter;
import com.example.omnia.ta3ala_2ma_2a2olk_client.view.CompanyQuestionDetails;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by omnia on 6/2/2018.
 */

public class QuestionAnswersAdaptoe extends RecyclerView.Adapter<QuestionAnswersAdaptoe.ViewHolder> {


    private List<Answer> answers;
    Context mcContext;
    CompanyQuestionDetails ii;
    Answer answer;
    QuestionAnswerAdapotorPresenter qAAPresenter = new QuestionAnswerAdapotorPresenter();

    public QuestionAnswersAdaptoe(List<Answer> qd, Context c, CompanyQuestionDetails s) {
        this.answers = qd;
        this.mcContext = c;
        ii = s;
    }

    @NonNull
    @Override
    public QuestionAnswersAdaptoe.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.answer_list_item, parent, false);
        return new QuestionAnswersAdaptoe.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final @NonNull ViewHolder holder, final int position) {
//
//        Log.i("testu", getUserId()+"nkjkj");
//        Log.i("testu", ii.getQuestion().getPersonId().getPersonId()+"");
//      //  Toast.makeText(mcContext, getUserId()+"user id", Toast.LENGTH_LONG).show();
//        Toast.makeText(mcContext, ii.getQuestion().getPersonId().getPersonId()+"qid", Toast.LENGTH_LONG).show();

        final Answer answer = answers.get(position);
        holder.answer.setText(answer.getAnswer());

        holder.personRate.setText(answer.getRate()+"");
        holder.personDate.setText(answer.getAnswersDate());
        holder.personName.setText(answer.getPersonId().getFirst() + " " + answer.getPersonId().getLast());
        Picasso.get()
                .load(answer.getPersonId().getImage())
                .placeholder(R.drawable.profile)
                .into(holder.personImage);

        String userEmail = answers.get(position).getPersonId().getEmail();
        String email = getUserOffAppEmail();

        holder.answerRateCount.setText(String.valueOf(answers.get(position).getRate()));

        if (checkIfUserLoginOrNot() == 0) {
            holder.editImageView.setVisibility(View.GONE);
            holder.editTextView.setVisibility(View.GONE);
            holder.deleteImageView.setVisibility(View.GONE);
            holder.deleteTextView.setVisibility(View.GONE);
            holder.reportImageView.setVisibility(View.GONE);
            holder.reportTextView.setVisibility(View.GONE);
            holder.horizontalLine1.setVisibility(View.GONE);
            holder.horizontalLine2.setVisibility(View.GONE);
            holder.v1.setVisibility(View.GONE);
            holder.v2.setVisibility(View.GONE);

        } else {
            // check if answer is mine
            if (answer.getPersonId().getPersonId() == getUserId()) {
                holder.reportImageView.setVisibility(View.GONE);
                holder.reportTextView.setVisibility(View.GONE);

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

            } else {// answer is not mine
                holder.editImageView.setVisibility(View.GONE);
                holder.editTextView.setVisibility(View.GONE);
                holder.deleteImageView.setVisibility(View.GONE);
                holder.deleteTextView.setVisibility(View.GONE);
                holder.v1.setVisibility(View.GONE);
                holder.v2.setVisibility(View.GONE);

                holder.reportImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showReportDialog(position);
                    }
                });
                holder.reportTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showReportDialog(position);
                    }
                });

                // check if user rate this answer before
                if (checkIfUserRateBefore(answers.get(position)) == 0) {
                    // answer not rated before
                    holder.answerUpRate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            holder.answerRateCount.setText(String.valueOf(answers.get(position).getRate() + 1));
                            qAAPresenter.answerRateUpPresenter(String.valueOf(answers.get(position).getAnswersId()), getToken());
                        }
                    });
                    holder.answerDownRate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            holder.answerRateCount.setText(String.valueOf(answers.get(position).getRate() - 1));
                            qAAPresenter.answerRateDownPresenter(String.valueOf(answers.get(position).getAnswersId()), getToken());
                        }
                    });
                } else {
                }
            }
        }

        // verify handel cases
        final Question qq = ii.getQuestion();
        if (qq.getPersonId().getPersonId() == getUserId()) {
            // queestion is mine
//            Toast.makeText(mcContext, "==v", Toast.LENGTH_LONG).show();

            // check if question is verified
            if (qq.getVerified() == 0) {// question is not verified
                holder.answerVerified.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        qq.setVerified(answer.getAnswersId());
                        qAAPresenter.verifyAnswerPresenter(qq, getToken());
                        holder.answerVerified.setImageResource(R.drawable.greenverified);
                    notifyDataSetChanged();
                    }
                });
            } else {// set green verified right
                if (answer.getAnswersId() == qq.getVerified()) {
                    holder.answerVerified.setImageResource(R.drawable.greenverified);
                }
                else {
                    holder.answerVerified.setVisibility(View.GONE);
                }
            }

        } else {// question is not mine
            if (answer.getAnswersId() == qq.getVerified()) {
                holder.answerVerified.setImageResource(R.drawable.greenverified);
            } else {
                holder.answerVerified.setVisibility(View.GONE);
            }
        }

//        if(answer.getAnswersId()==qq.getVerified()){
//            holder.answerVerified.setImageResource(R.drawable.greenverified);
//        }
//        else if(qq.getVerified()==0){
//            holder.answerVerified.setVisibility(View.GONE);
//        }


    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView answer, personName , personDate , personRate;
        public ImageView personImage;
        public ImageView editImageView, deleteImageView, reportImageView;
        public TextView editTextView, deleteTextView, reportTextView;
        public View horizontalLine1, horizontalLine2, v1, v2;
        public ImageView answerUpRate, answerDownRate, answerVerified;
        public TextView answerRateCount;

        public ViewHolder(View view) {
            super(view);
            answer = (TextView) view.findViewById(R.id.answerId);
            personName = (TextView) view.findViewById(R.id.personName);
            personRate = (TextView) view.findViewById(R.id.personeRate);
            personDate = view.findViewById(R.id.personeDate);
            personImage = (ImageView) view.findViewById(R.id.personImage);
            editImageView = (ImageView) view.findViewById(R.id.editimage);
            editTextView = (TextView) view.findViewById(R.id.edittext);
            deleteImageView = (ImageView) view.findViewById(R.id.deleteimage);
            deleteTextView = (TextView) view.findViewById(R.id.deletetext);
            reportImageView = (ImageView) view.findViewById(R.id.reportImage);
            reportTextView = (TextView) view.findViewById(R.id.reportText);

            horizontalLine1 = (View) view.findViewById(R.id.h1);
            horizontalLine2 = (View) view.findViewById(R.id.h2);
            v1 = (View) view.findViewById(R.id.v1);
            v2 = (View) view.findViewById(R.id.v2);

            answerUpRate = view.findViewById(R.id.AupRate);
            answerDownRate = view.findViewById(R.id.AdownRate);
            answerRateCount = (TextView) view.findViewById(R.id.ArateCount);

            answerVerified = (ImageView) view.findViewById(R.id.verified);
        }
    }


    public void deleteAnswer(int answerId) {
        final String token = getToken();
        // send request to delete data
        qAAPresenter.deleteAnswerPresenter(answerId, token);
        if(answerId==ii.getQuestion().getVerified()){
            Question question=ii.getQuestion();
            question.setVerified(0);
            qAAPresenter.verifyAnswerPresenter(question,getToken());
        }
        notifyDataSetChanged();
    }

    public void editAnswer(Answer answer) {
        final String token = getToken();
        QuestionAnswerAdapotorPresenter qAAPresenter = new QuestionAnswerAdapotorPresenter();
        qAAPresenter.editAnswerPresenter(answer, token);
    }

    public int checkIfUserRateBefore(Answer answer) {
        int x = 0, i = 0;
        List<PersonId> ratedPerson = answer.getPersonCollection();
        while (x == 0 && i < ratedPerson.size()) {

            if (ratedPerson.get(i).getPersonId() == getUserId()) {
                x = 1;
            } else {
                i++;
            }
        }
        return x;
    }

    public int checkIfUserLoginOrNot() {
        int x = 0;
        SharedPreferences userDetails = mcContext.getSharedPreferences("LoginPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userDetails.edit();
        SharredPreferenceManager manager = new SharredPreferenceManager(mcContext);
        String email = manager.getString(userDetails, "email", "no");
        if (email.equals("no")) {
            x = 0;
        } else {
            x = 1;
        }
        return x;
    }

    public String getUserOffAppEmail() {
        // get user email from sharedpref
        SharedPreferences userDetails = mcContext.getSharedPreferences("LoginPref", Context.MODE_PRIVATE);
        SharredPreferenceManager manager = new SharredPreferenceManager(mcContext);
        String email = manager.getString(userDetails, "email", "no");
        return email;
    }

    public int getUserId() {
        SharedPreferences userDetails = mcContext.getSharedPreferences("LoginPref", Context.MODE_PRIVATE);
        SharredPreferenceManager manager = new SharredPreferenceManager(mcContext);
        String idstr = manager.getString(userDetails, "id", "0");
        return Integer.parseInt(idstr);
    }

    public String getToken() {
        SharedPreferences pref = mcContext.getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        SharredPreferenceManager shM = new SharredPreferenceManager(mcContext);
        final String token = shM.getString(pref, "persontoken", "error");
        return token;
    }
///////////////// dialogs////////////////////////////////

    public void showDeleteDialog(final int answerPosition) {

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
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void showEditDialog(final int answerPosition) {

        LayoutInflater linflater = (LayoutInflater) mcContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view1 = linflater.inflate(R.layout.answer_edit_dialog, null);

        final EditText answerEditText = (EditText) view1.findViewById(R.id.editedanswer);
        answerEditText.setText(answers.get(answerPosition).getAnswer());

        AlertDialog alertDialog = new AlertDialog.Builder(mcContext).create();
        alertDialog.setView(view1);
        alertDialog.setTitle("Edit Answer");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SAVE",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // edit this ansewer from database and recycleview
                        String editAnswer = answerEditText.getText().toString();
                        Answer answer = answers.get(answerPosition);
                        answer.setAnswer(editAnswer);
                        Question qq = ii.getQuestion();
                        answer.setQuestionId(qq.getQuestionId());
                        Log.i("qq", qq.getQuestionId().toString());
                        editAnswer(answer);
                        notifyDataSetChanged();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void showReportDialog(final int position) {

        LayoutInflater linflater = (LayoutInflater) mcContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view1 = linflater.inflate(R.layout.question_report_dialog, null);

        final EditText reportTitleEditText = (EditText) view1.findViewById(R.id.reportTitle);
        final EditText reportBodyEditText = (EditText) view1.findViewById(R.id.reportBody);
        final AlertDialog.Builder dialog = new AlertDialog.Builder(mcContext);
        dialog.setTitle("Answer Report");
        dialog.setView(view1)

                .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false);
        final AlertDialog alert = dialog.create();
        alert.show();
        alert.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (reportTitleEditText.getText().toString().trim().isEmpty()) {
                    reportTitleEditText.setError("please enter data");
                } else if (reportBodyEditText.getText().toString().trim().isEmpty()) {
                    reportBodyEditText.setError("please enter data");
                } else {
                    Report report = new Report();
                    report.setTitle(reportTitleEditText.getText().toString());
                    report.setMsg(reportBodyEditText.getText().toString());
                    report.setChecked(answers.get(position).getAnswersId());
                    // get date
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
                    String currentDate = mdformat.format(calendar.getTime());
                    report.setReportDate(currentDate);
                    report.setType("answer");
                    // get person id from sharedpref
                    PersonId person = new PersonId();
                    person.setPersonId(getUserId());
                    report.setPersonId(person);
                    qAAPresenter.reportAnswerPresenter(report, getToken());
                    alert.dismiss();
                }
            }
        });
    }


}

