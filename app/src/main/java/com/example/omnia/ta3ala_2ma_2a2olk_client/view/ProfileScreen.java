package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
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
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.PlacesPresenter;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.ProfilePresenter;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import databasepkg.FirebaseDatabaseDAO;
import databasepkg.SaveImageCallBack;

public class ProfileScreen extends AppCompatActivity implements View.OnClickListener, SaveImageCallBack, ProfileInterface.view {
    ImageView profile, editusername, editpassword;
    TextView email, username, firstname, lastname, gender;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        presenter = new ProfilePresenter(this);
        profile = (ImageView) findViewById(R.id.profileimg);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        profile.setOnClickListener(this);
        editusername = (ImageView) findViewById(R.id.editusername);
        editpassword = (ImageView) findViewById(R.id.editpass);
        email = (TextView) findViewById(R.id.email);
        username = (TextView) findViewById(R.id.username);
        firstname = (TextView) findViewById(R.id.firstname);
        lastname = (TextView) findViewById(R.id.lastname);
        gender = (TextView) findViewById(R.id.gender);
        update = (Button) findViewById(R.id.btnupdate);
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
                            getApplicationContext().startActivity(intent);
                        }
                    });

            builder1.setNegativeButton(
                    "Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(ProfileScreen.this, MainActivity.class);
                            intent.putExtra("companyId", id);
                            getApplicationContext().startActivity(intent);
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
        Log.i("iduser", id + "");
        image = manager.getString(userDetails, "image", "no");
        Picasso.get().load(image).into(profile);
        password = manager.getString(userDetails, "password", "no");
        editusername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText input = new EditText(ProfileScreen.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProfileScreen.this);
                final EditText edittext = new EditText(getApplicationContext());
                alertDialog.setMessage("Edit UserName");
                alertDialog.setTitle("Enter Your New Username");
                alertDialog.setView(edittext);
                alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String YouEditTextValue = edittext.getText().toString();
                        username.setText(YouEditTextValue);
                        presenter.updateUsername(getApplicationContext(), emails, YouEditTextValue, firstnames, lastnames, genders, id, image, password);
                        Toast.makeText(getApplicationContext(), YouEditTextValue, Toast.LENGTH_LONG).show();
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
                        //     Toast.makeText(getApplicationContext(), YouEditTextValue, Toast.LENGTH_LONG).show();
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

        //  addItemsOnSpinner2();
        // addAction();
        SharedPreferences tokenDetails = getSharedPreferences("select", Context.MODE_PRIVATE);
        int selected = tokenDetails.getInt("country", 0);
        spinner1.setSelection(selected);
        //     spinner2.setSelection(3);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sp1 = String.valueOf(spinner1.getSelectedItem());
                int selectedcat = spinner1.getSelectedItemPosition();
                SharedPreferences pref = getSharedPreferences("select", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("country", selectedcat);
                editor.commit();
                Log.i("selectedcat", selectedcat + "");
                // Toast.makeText(this, sp1, Toast.LENGTH_SHORT).show();
                if (sp1.contentEquals("Cairo")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Maadi");
                    list.add("october");
                    list.add("Giza");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ProfileScreen.this,
                            android.R.layout.simple_spinner_item, list);
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
                    int sub = pref.getInt("scountry", 0);
                    spinner2.setSelection(sub);
                }
                if (sp1.contentEquals("Alexandria")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Victoria");
                    list.add("Smouha");
                    list.add("Miami");
                    ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(ProfileScreen.this,
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter2.notifyDataSetChanged();
                    spinner2.setAdapter(dataAdapter2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            int subcat = spinner2.getSelectedItemPosition();
                            Log.i("sub2", subcat + "");
                            SharedPreferences pref = getSharedPreferences("select", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putInt("scountry", subcat);
                            editor.commit();
                            Log.i("subcat", subcat + "");
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    int sub = pref.getInt("scountry", 0);
                    spinner2.setSelection(sub);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             String city =  String.valueOf(spinner1.getSelectedItem());
             String district = String.valueOf(spinner2.getSelectedItem());
             presenter.addLocation(getApplicationContext() , city , district , id);
            }
        });
    }


    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    //handling the image chooser activity result
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
        //if the clicked button is choose
        if (view == profile) {
            showFileChooser();
        }

    }

    @Override
    public void savedImageUrl(String url) {

        Log.i("ayData", url + "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        presenter.updateUsername(getApplicationContext(), emails, usernames, firstnames, lastnames, genders, id, url, password);
    }


}
