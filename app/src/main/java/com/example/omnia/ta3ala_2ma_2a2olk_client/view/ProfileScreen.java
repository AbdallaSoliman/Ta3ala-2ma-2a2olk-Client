package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.ProfileInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.SpecialUser;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.ProfilePresenter;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import databasepkg.FirebaseDatabaseDAO;
import databasepkg.SaveImageCallBack;

public class ProfileScreen extends AppCompatActivity implements View.OnClickListener, SaveImageCallBack, ProfileInterface.view {
    ImageView profile, editusername;
    TextView email, gender;
    TextView editpassword;
    EditText username, firstname, lastname;
    TextView place;
    SharedPreferences userDetails;
    SharredPreferenceManager manager;
    String emails, usernames, firstnames, lastnames, genders, image, password;
    String idstr;
    int id;
    Button update;
    private Spinner spinner1, spinner2;
    ProfilePresenter presenter;
    private Uri filePath;
    File imageFile;
    private static final int PICK_IMAGE_REQUEST = 234;
    Bitmap bitmap;
    boolean checkFocus = false;
    HashMap<String, List<String>> placesMap;
    List<String> placeList;
    String tempUserName, tempFirstName, tempLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new ProfilePresenter(this);
        presenter.getMyLocations(this);
        presenter.getSpecialUser(this);
        placeList = new ArrayList<>();
        profile = findViewById(R.id.profileimg);
        profile.setOnClickListener(this);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        editusername = findViewById(R.id.editusername);
        editpassword = findViewById(R.id.editpassword);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        gender = findViewById(R.id.gender);
        place = findViewById(R.id.myplace);
//        firstname.setFocusable(false);
//        lastname.setFocusable(false);
//        username.setFocusable(false);

        userDetails = getSharedPreferences("LoginPref", Context.MODE_PRIVATE);
        manager = new SharredPreferenceManager(getApplicationContext());
        emails = manager.getString(userDetails, "email", "no");
        email.setText(emails);
        usernames = manager.getString(userDetails, "username", "no");
        username.setText(usernames);
        firstnames = manager.getString(userDetails, "first", "no");
        firstname.setText(firstnames);
        lastnames = manager.getString(userDetails, "last", "no");
        lastname.setText(lastnames);
        genders = manager.getString(userDetails, "gender", "no");
        gender.setText(genders);
        idstr = manager.getString(userDetails, "id", "0");
        id = Integer.parseInt(idstr);

        if (id == 0) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(ProfileScreen.this);
            builder1.setMessage("You Need To Login To view Your Profile..........");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Login",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(ProfileScreen.this, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(intent);
                        }
                    });

            builder1.setNegativeButton(
                    "Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(ProfileScreen.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("companyId", id);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(intent);
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
        Log.i("iduser", id + "");
        image = manager.getString(userDetails, "image", "no");
        Picasso.get().load(image).placeholder(R.drawable.profile).into(profile);
        password = manager.getString(userDetails, "password", "no");

        editusername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkFocus == false) {
                    firstname.setFocusable(true);
                    lastname.setFocusable(true);
                    username.setFocusable(true);
                    firstname.setTextColor(Color.RED);
                    lastname.setTextColor(Color.RED);
                    username.setTextColor(Color.RED);
                    checkFocus = true;
                } else if (checkFocus == true) {

                    firstname.setFocusable(false);
                    lastname.setFocusable(false);
                    username.setFocusable(false);
                    firstname.setTextColor(Color.parseColor("#00a88f"));
                    lastname.setTextColor(Color.parseColor("#00a88f"));
                    username.setTextColor(Color.parseColor("#00a88f"));
                    checkFocus = false;
                    String city = String.valueOf(spinner1.getSelectedItem());
                    String district = String.valueOf(spinner2.getSelectedItem());
                    tempUserName = username.getText().toString();
                    tempFirstName = firstname.getText().toString();
                    tempLastName = lastname.getText().toString();
                    presenter.addLocation(getApplicationContext(), city, district, id);
                }
            }
        });

        editpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText input = new EditText(ProfileScreen.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProfileScreen.this);
                LinearLayout layout = new LinearLayout(getApplicationContext());
                layout.setOrientation(LinearLayout.VERTICAL);

// Add a TextView here for the "Title" label, as noted in the comments
                final EditText titleBox = new EditText(getApplicationContext());
                titleBox.setInputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PASSWORD);
                titleBox.setHint("Enter New Password");
                layout.addView(titleBox); // Notice this is an add method

// Add another TextView here for the "Description" label
                final EditText descriptionBox = new EditText(getApplicationContext());
                descriptionBox.setHint("Confirm new Password");
                descriptionBox.setInputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PASSWORD);
                layout.addView(descriptionBox); // Another add method

                alertDialog.setView(layout); // Again this is a set method, not add
                alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String YouEditTextValue = titleBox.getText().toString();
                        String confirm = descriptionBox.getText().toString();
                        if (YouEditTextValue.equals(confirm)) {
                            presenter.updateUsername(getApplicationContext(), emails, YouEditTextValue, firstnames, lastnames, genders, id, image, password);
                        } else {
                            Toast.makeText(getApplicationContext(), "Your Password Doesn't Match", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sp1 = String.valueOf(spinner1.getSelectedItem());
                int selectedcat = spinner1.getSelectedItemPosition();
                SharedPreferences pref = getSharedPreferences("select", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("country", selectedcat);
                editor.commit();

                List<String> tempList = new ArrayList<String>();
                for (String value : placesMap.get(sp1)) {
                    tempList.add(value);
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ProfileScreen.this, android.R.layout.simple_spinner_item, tempList);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter.notifyDataSetChanged();
                spinner2.setAdapter(dataAdapter);
                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        SharedPreferences pref = getSharedPreferences("select", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        int subcat = spinner2.getSelectedItemPosition();
                        editor.putInt("scountry", subcat);
                        editor.commit();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
              //  int sub = pref.getInt("scountry", 0);
               // spinner2.setSelection(sub);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            imageFile = new File("" + filePath);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                profile.setImageBitmap(bitmap);
                new FirebaseDatabaseDAO().uploadUserImage(ProfileScreen.this, bitmap, imageFile.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view == profile) {
            showFileChooser();
        }
    }

    @Override
    public void savedImageUrl(String url) {
        presenter.updateUsername(getApplicationContext(), emails, usernames, firstnames, lastnames, genders, id, url, password);
    }

    @Override
    public void setPlaces(HashMap<String, List<String>> placesMap) {
        this.placesMap = placesMap;
        Toast.makeText(getApplicationContext(), "Size is " + placesMap.size() + "", Toast.LENGTH_LONG).show();
        for (String key : placesMap.keySet()) {
            placeList.add(key);
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ProfileScreen.this,
                android.R.layout.simple_spinner_item, placeList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();
        spinner1.setAdapter(dataAdapter);
    }

    @Override
    public void setNames(String firstName, String lastName, String userName) {
        firstname.setText(firstName);
        lastname.setText(lastName);
        username.setText(userName);
    }

    @Override
    public void setPlace(String newPlace) {
        place.setText(newPlace);
        presenter.updateUsername(getApplicationContext(), emails, tempUserName, tempFirstName, tempLastName, genders, id, image, password);
    }

    @Override
    public void setSpecialUser(SpecialUser specialUser) {
        Toast.makeText(getApplicationContext(),"AAAAA" + specialUser.getPersonId(),Toast.LENGTH_LONG).show();
        firstname.setText(specialUser.getNumOfAskedQuestions()+" ");
    }
}