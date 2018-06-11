package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.content.DialogInterface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.MVPInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Question;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.SubCategory;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.AddQuestionPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AddQuestion extends AppCompatActivity implements MVPInterface.View {
    NumberPicker stringMainPicker;
    NumberPicker numberPicker2;
    NumberPicker numberPicker3;
    List<SubCategory> subCatCollection;
    Switch aSwitch;
    TextView textView;
    ListView listView;
    EditText title, description;
    int postion;
    Button button, addQuestion;
    int check = 1;
    int temp;

    String mainCategories[] = {"Categories", "Places"};

    String customerService[] = {"Customer Service"};

    ArrayList<String> list = new ArrayList<>();

    ArrayAdapter arrayAdapter;

    ConstraintLayout.LayoutParams layoutParams;

    AddQuestionPresenter presenter;

    HashMap<String, List<String>> categoriesMap, placesMap, customerServciceMap;
    HashMap<String, List<Integer>> categoriesMapId, placesMapId, customerServciceMapId;

    Set<String> categoriesSet, placesSet, customerSet;
    String[] categoriesArray, placesArray, customerArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        presenter = new AddQuestionPresenter(this, getApplicationContext());
        presenter.showData();

        stringMainPicker = findViewById(R.id.numberPicker);
        numberPicker2 = findViewById(R.id.numberPicker2);
        numberPicker3 = findViewById(R.id.numberPicker3);
        textView = findViewById(R.id.switchtext);
        listView = findViewById(R.id.myListView);
        addQuestion = findViewById(R.id.questionAdder);
        button = findViewById(R.id.addingButton);
        title = findViewById(R.id.questiontitle);
        description = findViewById(R.id.questiondescrption);

        subCatCollection = new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempPickerTwo[] = numberPicker2.getDisplayedValues();
                String tempPickerThree[] = numberPicker3.getDisplayedValues();
                list.add(tempPickerTwo[numberPicker2.getValue()] + "/" + tempPickerThree[numberPicker3.getValue()]);
                layoutParams.height = layoutParams.height + 100;
                listView.setLayoutParams(layoutParams);
                arrayAdapter.notifyDataSetChanged();
            }
        });

        addQuestion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (title.getText().toString().trim().isEmpty()) {
                    title.setError("Please Enter Title");
                } else if (description.getText().toString().trim().isEmpty()) {
                    description.setError("Please Enter Description");
                } else {
                    subCatCollection = new ArrayList<>();
                    if (check == 1) {
                        for (int i = 0; i < list.size(); i++) {
                            String[] myList = list.get(i).split("/");
                            try {
                                if (categoriesMapId.get(myList[0]) != null) {
                                    int index = categoriesMap.get(myList[0]).indexOf(myList[1]);
                                    int index2 = categoriesMapId.get(myList[0]).get(index);
                                    SubCategory subCategory = new SubCategory();
                                    subCategory.setSubCatId(index2);
                                    subCatCollection.add(subCategory);
                                    Toast.makeText(getApplicationContext(), "ID is  " + index2, Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {

                            }
                            try {
                                if (placesMapId.get(myList[0]) != null) {
                                    int index = placesMap.get(myList[0]).indexOf(myList[1]);
                                    int index2 = placesMapId.get(myList[0]).get(index);
                                    SubCategory subCategory = new SubCategory();
                                    subCategory.setSubCatId(index2);
                                    subCatCollection.add(subCategory);
                                    Toast.makeText(getApplicationContext(), "ID is  " + index2, Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {

                            }
                        }

                    } else if (check == 0) {
                        String tempPickerTwo[] = numberPicker2.getDisplayedValues();
                        String key = tempPickerTwo[numberPicker2.getValue()];
                        int value = customerServciceMapId.get(key).get(0);
                        SubCategory subCategory = new SubCategory();
                        subCategory.setSubCatId(value);
                        subCatCollection.add(subCategory);
                    }
                    Question question = new Question();
                    question.setTitle(title.getText().toString());
                    question.setBody(description.getText().toString());
                    question.setSubCatCollection(subCatCollection);
                    question.setIsdeleted(0);
                    Toast.makeText(getApplicationContext(), "Addedd", Toast.LENGTH_LONG).show();
                    presenter.AddQuestion(question);
                }
            }
        });

        textView.setText("Categories");
        aSwitch = findViewById(R.id.mySwitch);
        aSwitch.setChecked(false);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (!checked) {
                    textView.setText("Categories");
                    stringMainPicker.setMinValue(0);
                    stringMainPicker.setDisplayedValues(null);
                    stringMainPicker.setMaxValue(mainCategories.length - 1);  // Array -1
                    stringMainPicker.setWrapSelectorWheel(false);
                    stringMainPicker.setDisplayedValues(mainCategories);
                    numberPicker2.setDisplayedValues(null);
                    numberPicker2.setMinValue(0);
                    numberPicker2.setMaxValue(categoriesArray.length - 1);
                    numberPicker2.setDisplayedValues(categoriesArray);
                    numberPicker2.setWrapSelectorWheel(false);
                    numberPicker3.setVisibility(View.VISIBLE);
                    List<String> tempList = categoriesMap.get(categoriesArray[0]);
                    String[] tempArray = new String[tempList.size()];
                    tempArray = tempList.toArray(tempArray);
                    numberPicker3.setDisplayedValues(null);
                    numberPicker3.setMinValue(0);
                    numberPicker3.setMaxValue(tempArray.length - 1);
                    numberPicker3.setWrapSelectorWheel(false);
                    numberPicker3.setDisplayedValues(tempArray);
                    check = 1;
                    button.setVisibility(View.VISIBLE);
                    numberPicker2.setValue(0);

                } else {
                    textView.setText("Customer Service");
                    stringMainPicker.setMinValue(0);
                    stringMainPicker.setDisplayedValues(null);
                    stringMainPicker.setMaxValue(customerService.length - 1);  // Array -1
                    stringMainPicker.setWrapSelectorWheel(false);
                    stringMainPicker.setDisplayedValues(customerService);
                    numberPicker2.setDisplayedValues(null);
                    numberPicker2.setMinValue(0);
                    numberPicker2.setMaxValue(customerArray.length - 1);
                    numberPicker2.setWrapSelectorWheel(false);
                    numberPicker2.setDisplayedValues(customerArray);
                    numberPicker3.setVisibility(View.INVISIBLE);
                    check = 0;
                    button.setVisibility(View.INVISIBLE);
                    numberPicker2.setValue(0);
                    /////////////////
                    list.clear();
                    arrayAdapter.notifyDataSetChanged();

                    layoutParams = (ConstraintLayout.LayoutParams) listView.getLayoutParams();
                    layoutParams.height = 0;
                    listView.setLayoutParams(layoutParams);
                }
            }
        });

        stringMainPicker.setMinValue(0);
        stringMainPicker.setMaxValue(mainCategories.length - 1);  // Array -1
        stringMainPicker.setDisplayedValues(mainCategories);
        stringMainPicker.setWrapSelectorWheel(false);

        stringMainPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                numberPicker3.setDisplayedValues(null);
                numberPicker3.setMaxValue(0);
                numberPicker3.setMinValue(0);
                //
                numberPicker2.setValue(0);
                numberPicker3.setValue(0);
                //
                if (check == 1) {
                    if (newValue == 0) {
                        postion = 0;
                        numberPicker2.setDisplayedValues(null);
                        numberPicker2.setMinValue(0);
                        numberPicker2.setMaxValue(categoriesArray.length - 1);
                        numberPicker2.setWrapSelectorWheel(false);
                        numberPicker2.setDisplayedValues(categoriesArray);
                        ///////////////////////////////////////////////////
                        List<String> tempList = categoriesMap.get(categoriesArray[0]);
                        String[] tempArray = new String[tempList.size()];
                        tempArray = tempList.toArray(tempArray);
                        numberPicker3.setDisplayedValues(null);
                        numberPicker3.setMinValue(0);
                        numberPicker3.setMaxValue(tempArray.length - 1);
                        numberPicker3.setDisplayedValues(tempArray);
                        numberPicker3.setWrapSelectorWheel(false);
                    } else if (newValue == 1) {
                        postion = 1;
                        numberPicker2.setDisplayedValues(null);
                        numberPicker2.setMinValue(0);
                        numberPicker2.setMaxValue(placesArray.length - 1);
                        numberPicker2.setWrapSelectorWheel(false);
                        numberPicker2.setDisplayedValues(placesArray);
                        ///////////////////////////////////////////////////
                        List<String> tempList = placesMap.get(placesArray[0]);
                        String[] tempArray = new String[tempList.size()];
                        tempArray = tempList.toArray(tempArray);
                        numberPicker3.setDisplayedValues(null);
                        numberPicker3.setMinValue(0);
                        numberPicker3.setMaxValue(tempArray.length - 1);
                        numberPicker3.setDisplayedValues(tempArray);
                        numberPicker3.setWrapSelectorWheel(false);
                    }
                } else if (check == 0) {
                    numberPicker2.setDisplayedValues(null);
                    numberPicker2.setMinValue(0);
                    numberPicker2.setMaxValue(customerArray.length - 1);
                    numberPicker2.setWrapSelectorWheel(false);
                    numberPicker2.setDisplayedValues(customerArray);
                }
            }
        });

        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int first, int second) {
                temp = second;
                numberPicker3.setDisplayedValues(null);
                numberPicker3.setMaxValue(0);
                numberPicker3.setMinValue(0);
                if (check == 1) {
                    if (postion == 0) {
                        List<String> tempList = categoriesMap.get(categoriesArray[second]);
                        String[] tempArray = new String[tempList.size()];
                        tempArray = tempList.toArray(tempArray);
                        if (tempArray.length > 0) {
                            numberPicker3.setDisplayedValues(null);
                            numberPicker3.setMinValue(0);
                            numberPicker3.setMaxValue(tempArray.length - 1);
                            numberPicker3.setWrapSelectorWheel(false);
                            numberPicker3.setDisplayedValues(tempArray);
                        }
                    } else if (postion == 1) {
                        List<String> tempList = placesMap.get(placesArray[second]);   //
                        String[] tempArray = new String[tempList.size()];
                        tempArray = tempList.toArray(tempArray);
                        if (tempArray.length > 0) {
                            numberPicker3.setDisplayedValues(null);
                            numberPicker3.setMinValue(0);
                            numberPicker3.setMaxValue(tempArray.length - 1);
                            numberPicker3.setWrapSelectorWheel(false);
                            numberPicker3.setDisplayedValues(tempArray);

                        }
                    }
                } else if (check == 0) {
                    // Do Nothing There Is No Number Picker 3
                }
            }
        });

        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                ShowMyDialog(position);
                return true;
            }
        });

        layoutParams = (ConstraintLayout.LayoutParams) listView.getLayoutParams();
        layoutParams.height = 0;
        listView.setLayoutParams(layoutParams);
    }


    @Override
    public void showCategories(HashMap<String, List<String>> categoriesName, HashMap<String, List<String>> placesName
            , HashMap<String, List<String>> customerServiceName, HashMap<String, List<Integer>> categoriesId, HashMap<String, List<Integer>> placesId
            , HashMap<String, List<Integer>> customerServiceId) {

        this.categoriesMap = categoriesName;
        this.placesMap = placesName;
        this.customerServciceMap = customerServiceName;
        this.categoriesMapId = categoriesId;
        this.placesMapId = placesId;
        this.customerServciceMapId = customerServiceId;

        categoriesSet = categoriesName.keySet();
        categoriesArray = categoriesSet.toArray(new String[categoriesSet.size()]);

        placesSet = placesName.keySet();
        placesArray = placesSet.toArray(new String[placesSet.size()]);

        customerSet = customerServiceName.keySet();
        customerArray = customerSet.toArray(new String[customerSet.size()]);

        numberPicker2.setDisplayedValues(null);
        numberPicker2.setMinValue(0);
        numberPicker2.setMaxValue(categoriesArray.length - 1);
        numberPicker2.setWrapSelectorWheel(false);
        numberPicker2.setDisplayedValues(categoriesArray);
        ///////////////////////////////////////////////////
        List<String> tempList = categoriesMap.get(categoriesArray[0]);
        String[] tempArray = new String[tempList.size()];
        tempArray = tempList.toArray(tempArray);
        numberPicker3.setDisplayedValues(null);
        numberPicker3.setMinValue(0);
        numberPicker3.setMaxValue(tempArray.length - 1);
        numberPicker3.setDisplayedValues(tempArray);
        numberPicker3.setWrapSelectorWheel(false);
    }

    @Override
    public void isAdded(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        finish();
    }

    void ShowMyDialog(final int position) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Do You Want To Delete This Category");
        alertDialog.setCancelable(true);
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                list.remove(position);
                arrayAdapter.notifyDataSetChanged();
                dialogInterface.cancel();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }
}