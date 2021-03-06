package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.adaptor.QuestionAnswersAdaptoe;
import com.example.omnia.ta3ala_2ma_2a2olk_client.dialog.MyDialog;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Answer;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.PersonId;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Question;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Report;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.CompanyQuestionDetailsPresenter;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CompanyQuestionDetails extends AppCompatActivity {

    SharredPreferenceManager shM;
    CompanyQuestionDetailsPresenter cQDPresenter;
    Question question;
    TextView titleTextView, bodyTextView, rateCount,questionDateTextView;
    TextView personNameTextView, editTextView, deleteTextView, reportTextView;
    ImageView deleteImageView, personImageView, editImageView, reportImageView;
    List<Answer> questionAnswers;
    PersonId person;
    RecyclerView recyclerView;
    QuestionAnswersAdaptoe madaptor;
    String id;
    ImageView upRate, downRate;
    View v1, v2, h3;
    Button addComment;
    MyDialog myDialog;
    // for back button on action bar
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void setQuestionDetails(Question q, List<Answer> answers) throws ParseException {

        this.question = q;
        questionAnswers.addAll(answers);
        person = question.getPersonId();

        titleTextView.setText(question.getTitle());
        bodyTextView.setText(question.getBody());
        if (person!= null) {
            personNameTextView.setText(person.getFirst() + " " + person.getLast());
            //abdalla start
//            String mytime = pref.getString("announcementtime" + count, null);

// it comes out like this 2013-08-31 15:55:22 so adjust the date format
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date = df.parse(question.getQuestionDate());
//            CharSequence timePassedString = DateUtils.getRelativeTimeSpanString (epoch, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
            questionDateTextView.setText(question.getQuestionDate());
            Picasso.get()
                    .load(person.getImage())
                    .placeholder(R.drawable.profile)
                    .into(personImageView);
        }

        rateCount.setText(String.valueOf(question.getRate()));
        madaptor.notifyDataSetChanged();

        showWhichCompnent(person);
        //hesham start
        myDialog.dismiss();
        // hesham end
    }

    public void showWhichCompnent(PersonId personId) {

        if (checkIfUserLoginOrNot() == 0) {
            // user not logged in
            deleteTextView.setVisibility(View.GONE);
            deleteImageView.setVisibility(View.GONE);
            v1.setVisibility(View.GONE);
            editTextView.setVisibility(View.GONE);
            editImageView.setVisibility(View.GONE);
            v2.setVisibility(View.GONE);
            reportTextView.setVisibility(View.GONE);
            reportImageView.setVisibility(View.GONE);
            addComment.setVisibility(View.GONE);
            h3.setVisibility(View.GONE);
        } else { // user login
            // check if question is mine
            if (personId.getPersonId() == getUserId()) {
                reportTextView.setVisibility(View.GONE);
                reportImageView.setVisibility(View.GONE);
                v1.setVisibility(View.GONE);

                deleteImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDeleteDialog();
                    }
                });
                deleteTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDeleteDialog();
                    }
                });
                editImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showEditDialog(question);
                    }
                });
                editTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showEditDialog(question);
                    }
                });
            } else {
                // question is not mine
                ((ViewGroup) rateCount.getParent()).removeView(deleteTextView);
                ((ViewGroup) rateCount.getParent()).removeView(deleteImageView);
                ((ViewGroup) rateCount.getParent()).removeView(editTextView);
                ((ViewGroup) rateCount.getParent()).removeView(editImageView);
                ((ViewGroup) rateCount.getParent()).removeView(v1);
                ((ViewGroup) rateCount.getParent()).removeView(v2);

                reportImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        reportDialog();
                    }
                });
                reportTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        reportDialog();
                    }
                });

                // check if user rate befor or not
                if (checkIfUserRateOrNot() == 1) {
                } else {
                    upRate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cQDPresenter.questionUpRatePresenter(String.valueOf(question.getQuestionId()), getToken());
                            int rate = question.getRate() + 1;
                            rateCount.setText(String.valueOf(rate));
                        }
                    });
                    downRate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cQDPresenter.questionDownRatePresenter(String.valueOf(question.getQuestionId()), getToken());
                            int rate = question.getRate() - 1;
                            rateCount.setText(String.valueOf(rate));
                        }
                    });
                }
            }

        }
    }

    public void afterAddAnswer(Answer newAnswer) {
        questionAnswers.add(newAnswer);
        madaptor.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_question_details);
        myDialog = new MyDialog();
        myDialog.show(this);
        Log.i("tokenomnia", getToken());
        // for back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id = intent.getStringExtra("questionID");
        cQDPresenter = new CompanyQuestionDetailsPresenter(this);
        cQDPresenter.getQuestionDetailsPresenter(id, getToken());


        questionAnswers = new ArrayList<>();
        titleTextView = (TextView) findViewById(R.id.qTitle);
        bodyTextView = (TextView) findViewById(R.id.qBody);
        personNameTextView = (TextView) findViewById(R.id.personeName);
        personImageView = (ImageView) findViewById(R.id.personImage);
        questionDateTextView=findViewById(R.id.questionDate);
        // recycleview
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        madaptor = new QuestionAnswersAdaptoe(questionAnswers, this, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(madaptor);

        // delete&edit
        deleteTextView = (TextView) findViewById(R.id.deleteText);
        deleteImageView = (ImageView) findViewById(R.id.deleteImage);
        editTextView = (TextView) findViewById(R.id.editText);
        editImageView = (ImageView) findViewById(R.id.editImage);

        h3 = findViewById(R.id.h2);
        v1 = findViewById(R.id.v1);
        v2 = findViewById(R.id.v2);

        rateCount = (TextView) findViewById(R.id.rateCount);

        upRate = findViewById(R.id.upRate);
        downRate = findViewById(R.id.downRate);
        reportTextView = (TextView) findViewById(R.id.reportText);
        reportImageView = (ImageView) findViewById(R.id.reportImage);

        addComment = findViewById(R.id.addCommentButton);
    }

    public void onRestart() {
        super.onRestart();

        questionAnswers = new ArrayList<>();
        titleTextView = (TextView) findViewById(R.id.qTitle);
        bodyTextView = (TextView) findViewById(R.id.qBody);
        personNameTextView = (TextView) findViewById(R.id.personeName);
        personImageView = (ImageView) findViewById(R.id.personImage);
        questionDateTextView=findViewById(R.id.questionDate);
        // recycleview
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        madaptor = new QuestionAnswersAdaptoe(questionAnswers, this, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(madaptor);

        // delete&edit
        deleteTextView = (TextView) findViewById(R.id.deleteText);
        deleteImageView = (ImageView) findViewById(R.id.deleteImage);
        editTextView = (TextView) findViewById(R.id.editText);
        editImageView = (ImageView) findViewById(R.id.editImage);

        h3 = findViewById(R.id.h2);
        v1 = findViewById(R.id.v1);
        v2 = findViewById(R.id.v2);

        rateCount = (TextView) findViewById(R.id.rateCount);

        upRate = findViewById(R.id.upRate);
        downRate = findViewById(R.id.downRate);
        reportTextView = (TextView) findViewById(R.id.reportText);
        reportImageView = (ImageView) findViewById(R.id.reportImage);

        addComment = findViewById(R.id.addCommentButton);

    }

    public int checkIfUserRateOrNot() {

        int x = 0, i = 0;
        List<PersonId> ratedPerson = question.getPersonRateCollection();

        while (x == 0 && i < ratedPerson.size()) {

            if (ratedPerson.get(i).getPersonId() == getUserId()) {
                x = 1;
            } else {
                i++;
            }
        }
        return x;
    }

    public int getUserId() {
        SharedPreferences userDetails = getSharedPreferences("LoginPref", Context.MODE_PRIVATE);
        SharredPreferenceManager manager = new SharredPreferenceManager(getApplicationContext());
        String idstr = manager.getString(userDetails, "id", "0");
        return Integer.parseInt(idstr);
    }

    public int checkIfUserLoginOrNot() {
        int x = 0;
        SharedPreferences userDetails = getSharedPreferences("LoginPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userDetails.edit();
        SharredPreferenceManager manager = new SharredPreferenceManager(getApplicationContext());
        String email = manager.getString(userDetails, "email", "no");

        if (email.equals("no")) {
            x = 0;
        } else {
            x = 1;
        }
        return x;
    }

    public String getToken() {
        String token = null;
        SharedPreferences pref = this.getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        shM = new SharredPreferenceManager(this);
         token = shM.getString(pref, "persontoken", "error");
         if (token.equals("error")){
             token = shM.getString(pref, "persontoken1", "error");
         }
        Log.i("Hesham",token+"");
        return token;
    }

    public void addAnswer(View view) {
        Intent intent2 = new Intent(this, AddAnswer.class);
        intent2.putExtra("questionId", question);
        startActivity(intent2);
    }

    public Question getQuestion() {
        return question;
    }

    //////////////////////////// dialogs//////////////////////////

    public void showDeleteDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Delete Question");
        alertDialog.setMessage("Do you need to delete this Question");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // make q object deleted
                        question.setIsdeleted(1);
                        cQDPresenter.deleteQuestionPresenter(question, getToken());
                        finish();
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

    public void showEditDialog(final Question question) {

        final View view1 = getLayoutInflater().inflate(R.layout.question_edit_dialog, null);
        final EditText titleEditText = (EditText) view1.findViewById(R.id.editQuestionTitle);
        final EditText bodyEditText = (EditText) view1.findViewById(R.id.editQuestionBody);
        titleEditText.setText(question.getTitle());
        bodyEditText.setText(question.getBody());

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Edit Question");
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

                if (titleEditText.getText().toString().trim().isEmpty()) {
                    titleEditText.setError("please enter data");
                } else if (bodyEditText.getText().toString().trim().isEmpty()) {
                    bodyEditText.setError("please enter data");
                } else {
                    titleTextView.setText(titleEditText.getText().toString());
                    bodyTextView.setText(bodyEditText.getText().toString());
                    question.setTitle(titleEditText.getText().toString());
                    question.setBody(bodyEditText.getText().toString());
                    String token = getToken();
                    cQDPresenter.editQuestionPresenter(question, token);
                    alert.dismiss();
                }
            }
        });
    }

    public void reportDialog() {
        final View view1 = getLayoutInflater().inflate(R.layout.question_report_dialog, null);
        final EditText reportTitleEditText = (EditText) view1.findViewById(R.id.reportTitle);
        final EditText reportBodyEditText = (EditText) view1.findViewById(R.id.reportBody);
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Question Report");
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
                    report.setChecked(question.getQuestionId());
                    // get date
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
                    String currentDate = mdformat.format(calendar.getTime());
                    report.setReportDate(currentDate);
                    Log.i("datee", currentDate);
                    report.setType("question");
                    // get person id from sharedpref
                    PersonId person = new PersonId();
                    SharedPreferences userDetails = getSharedPreferences("LoginPref", Context.MODE_PRIVATE);
                    SharredPreferenceManager manager = new SharredPreferenceManager(getApplicationContext());
                    String idstr = manager.getString(userDetails, "id", "0");
                    person.setPersonId(Integer.parseInt(idstr));
                    report.setPersonId(person);

                    cQDPresenter.reportQuestionPresenter(report, getToken());
                    alert.dismiss();
                }
            }
        });
    }
//adballa start
    public static String parseDate(long timeAtMiliseconds) {
        if (timeAtMiliseconds==0) {
            return "";
        }
        //API.log("Day Ago "+dayago);
        String result = "now";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String todayDate = formatter.format(new Date());
        Calendar calendar = Calendar.getInstance();

        long dayagolong = timeAtMiliseconds * 1000;

        String agoformater = formatter.format(calendar.getTime());

        Date CurrentDate = null;
        Date CreateDate = null;

        try {
            CurrentDate = formatter.parse(todayDate);
            CreateDate = formatter.parse(agoformater);

            long different = Math.abs(CurrentDate.getTime() - CreateDate.getTime());

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;

            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;

            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;

            long elapsedSeconds = different / secondsInMilli;

            different = different % secondsInMilli;
            if (elapsedDays == 0) {
                if (elapsedHours == 0) {
                    if (elapsedMinutes == 0) {
                        if (elapsedSeconds < 0) {
                            return "0" + " s";
                        } else {
                            if (elapsedDays > 0 && elapsedSeconds < 59) {
                                return "now";
                            }
                        }
                    } else {
                        return String.valueOf(elapsedMinutes) + "m ago";
                    }
                } else {
                    return String.valueOf(elapsedHours) + "h ago";
                }

            } else {
                if (elapsedDays <= 29) {
                    return String.valueOf(elapsedDays) + "d ago";
                }
                if (elapsedDays > 29 && elapsedDays <= 58) {
                    return "1Mth ago";
                }
                if (elapsedDays > 58 && elapsedDays <= 87) {
                    return "2Mth ago";
                }
                if (elapsedDays > 87 && elapsedDays <= 116) {
                    return "3Mth ago";
                }
                if (elapsedDays > 116 && elapsedDays <= 145) {
                    return "4Mth ago";
                }
                if (elapsedDays > 145 && elapsedDays <= 174) {
                    return "5Mth ago";
                }
                if (elapsedDays > 174 && elapsedDays <= 203) {
                    return "6Mth ago";
                }
                if (elapsedDays > 203 && elapsedDays <= 232) {
                    return "7Mth ago";
                }
                if (elapsedDays > 232 && elapsedDays <= 261) {
                    return "8Mth ago";
                }
                if (elapsedDays > 261 && elapsedDays <= 290) {
                    return "9Mth ago";
                }
                if (elapsedDays > 290 && elapsedDays <= 319) {
                    return "10Mth ago";
                }
                if (elapsedDays > 319 && elapsedDays <= 348) {
                    return "11Mth ago";
                }
                if (elapsedDays > 348 && elapsedDays <= 360) {
                    return "12Mth ago";
                }

                if (elapsedDays > 360 && elapsedDays <= 720) {
                    return "1 year ago";
                }

                if (elapsedDays > 720) {
                    SimpleDateFormat formatterYear = new SimpleDateFormat("MM/dd/yyyy");
                    Calendar calendarYear = Calendar.getInstance();
                    calendarYear.setTime(new Date(timeAtMiliseconds));
                    return formatterYear.format(calendarYear.getTime()) + "";
                }

            }

        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
    //abdalla end
}
