package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.*;
import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.RegisterMvpInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.R;

public class SignUp extends AppCompatActivity implements RegisterMvpInterface.view {

    EditText email , password , first , last , username ;
    RadioGroup radioGroup;
    RadioButton male , female ;
    Button signup;
    SignupPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        presenter = new SignupPresenter(this);
       email = (EditText) findViewById(R.id.txtEmailUP);
       password = (EditText) findViewById(R.id.txtPassUP);
       first = (EditText) findViewById(R.id.txtFirst);
       last = (EditText) findViewById(R.id.txtLast);
       radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
       male = (RadioButton) findViewById(R.id.male);
       female = (RadioButton) findViewById(R.id.Female);
       signup = (Button) findViewById(R.id.btnSignup);
       username = (EditText) findViewById(R.id.txtUserName);
       signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int valid = presenter.checkInput(username.getText().toString() , email.getText().toString(), password.getText().toString(), first.getText().toString(), last.getText().toString());
               if (valid == 1) {
                   if (male.isChecked()) {
                       presenter.loadDataFromServer(username.getText().toString() , email.getText().toString(), password.getText().toString(), first.getText().toString(), last.getText().toString(), male.getText().toString(), SignUp.this);
//                       Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                       startActivity(intent);
                   } else if (female.isChecked()) {
                       presenter.loadDataFromServer(username.getText().toString() , email.getText().toString(), password.getText().toString(), first.getText().toString(), last.getText().toString(), female.getText().toString(), SignUp.this);
//                       Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                       startActivity(intent);
                   }
               }
           }
       });


    }

    @Override
    public void registerStatus(int key) {
        if ((key == 0)){
            Toast.makeText(this , "Please Enter a Valid E-mail " , Toast.LENGTH_LONG).show();
        }
        if (key == 1 ){
            Toast.makeText(this , "Please Enter a Password 6 characters or more " , Toast.LENGTH_LONG).show();
        }
        if (key == 2){
            Toast.makeText(this , "Please Fill all the input Fields " , Toast.LENGTH_LONG).show();
        }
    }
}
