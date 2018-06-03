package com.example.omnia.ta3ala_2ma_2a2olk_client.presenter;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.Tab2CustomerServiceInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.SubCategory;
import com.example.omnia.ta3ala_2ma_2a2olk_client.network.Tab2CustomerServiceNetwork;
import com.example.omnia.ta3ala_2ma_2a2olk_client.view.Tab2CustomerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omnia on 5/30/2018.
 */

public class Tab2CustomerServicePresenter implements Tab2CustomerServiceInterface {

    Tab2CustomerServiceNetwork networkRef;
    Tab2CustomerService ts;

    public Tab2CustomerServicePresenter(Tab2CustomerService ts) {
        this.ts = ts;
    }

    public void getCompaniesPresenter(String token){
        networkRef=new Tab2CustomerServiceNetwork(this);
        networkRef.getCompaniesFromNetwork(token);
    }

    @Override
    public void setCompaniesList(List<SubCategory> list){
        ts.setCompanies(list);
    }

}
