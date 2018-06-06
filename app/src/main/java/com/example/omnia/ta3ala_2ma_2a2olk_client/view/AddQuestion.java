package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.omnia.ta3ala_2ma_2a2olk_client.R;

public class AddQuestion extends AppCompatActivity {

    EditText titleText;
    EditText bodyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        titleText=(EditText)findViewById(R.id.titleid);
        bodyText=(EditText)findViewById(R.id.bodyid);
    }

    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        Intent intent = new Intent(AddQuestion.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}
