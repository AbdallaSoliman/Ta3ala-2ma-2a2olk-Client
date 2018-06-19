package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.PlacesInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.PlacesPresenter;

/**
 * Created by omnia on 5/28/2018.
 */

public class Tab4Places extends Fragment implements PlacesInterface.view {

    ExpandableListView myExpandableListView;
    PlacesPresenter presenter ;

    public Tab4Places(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab4, container, false);
        myExpandableListView = (ExpandableListView) rootView.findViewById(R.id.myexpandablelistview2);
        presenter = new PlacesPresenter(this , getActivity(), getContext());
//        Toast.makeText(getContext(), "Places !!! ", Toast.LENGTH_SHORT).show();
        presenter.initData(getActivity());
        return rootView;
    }
}

