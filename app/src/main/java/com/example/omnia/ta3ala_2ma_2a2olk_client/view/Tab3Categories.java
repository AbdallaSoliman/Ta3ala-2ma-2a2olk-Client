package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omnia.ta3ala_2ma_2a2olk_client.R;

/**
 * Created by omnia on 6/3/2018.
 */

public class Tab3Categories extends android.support.v4.app.Fragment{

    public Tab3Categories(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab3, container, false);
        return rootView;
    }
}
