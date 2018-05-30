package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.LoginMvpInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.User;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.*;


public class LoginActivity extends AppCompatActivity implements LoginMvpInterface.view {
 EditText username , password ;
 Button login , register;
    LoginPresenter presenter ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);
        presenter = new LoginPresenter(this);
        username = (EditText) findViewById(R.id.txtPass);
        password = (EditText) findViewById(R.id.txtEmail);
        login = (Button)  findViewById(R.id.btnSignin);
        register = (Button) findViewById(R.id.btnregister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             int valid =    presenter.checkInput(username.getText().toString() , password.getText().toString());
             if (valid ==1){
           presenter.loadDataFromServer(username.getText().toString() , password.getText().toString() , LoginActivity.this.getApplicationContext());
//           Toast.makeText(getApplicationContext() , u1.getLast() , Toast.LENGTH_LONG).show();
//                 Log.i("tag",u1.getLast());
//                 Intent intent = new Intent(LoginActivity.this, Tab1Home.class);
//                 startActivity(intent);
                 SharedPreferences tokenDetails = getApplicationContext().getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
                 SharedPreferences.Editor editor = tokenDetails.edit();
                 SharredPreferenceManager manager = new SharredPreferenceManager(getApplicationContext());
                 String token = manager.getString(tokenDetails, "persontoken", "no");
                 Toast.makeText(getApplicationContext(), token, Toast.LENGTH_LONG).show();
                 User user = new User(username.getText().toString(), password.getText().toString());
                 Log.e("usernameA" , user.getUsername());
                 Log.e("passwordA" , user.getPassword());
                 User u1 = presenter.loadUser(getApplicationContext(),token,user);
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
            Toast.makeText(this , "Please Enter username and password " , Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void questionActivity() {
        Intent intent = new Intent(LoginActivity.this, AddQuestion.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}
