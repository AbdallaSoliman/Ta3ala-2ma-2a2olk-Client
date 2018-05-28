package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omnia.ta3ala_2ma_2a2olk_client.R;

/**
 * Created by omnia on 5/28/2018.
 */

public class Tab3Profile extends Fragment {

    public Tab3Profile(){}

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

