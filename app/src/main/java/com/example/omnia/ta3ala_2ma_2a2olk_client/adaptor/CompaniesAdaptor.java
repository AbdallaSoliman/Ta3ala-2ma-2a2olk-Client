package com.example.omnia.ta3ala_2ma_2a2olk_client.adaptor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.SubCategory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omnia on 5/30/2018.
 */

public class CompaniesAdaptor extends ArrayAdapter<SubCategory> {

    private List<SubCategory> companies;
    Context mContext;

    public CompaniesAdaptor( Context context,  List<SubCategory> companies) {
        super(context, R.layout.list_item, companies);
        this.mContext=context;
        this.companies=companies;
    }

    @Override
    public int getCount() {
        if(companies != null)
        return companies.size();
        else return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder=new ViewHolder();
        LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.list_item,parent,false);

        viewHolder.companyName = (TextView) convertView.findViewById(R.id.companyName);
        //abdalla start
        viewHolder.imageViewCompanyLogo = (ImageView) convertView.findViewById(R.id.imageViewCompanyLogo);
        Picasso.get().load(companies.get(position).getImgUrl()).placeholder(R.drawable.call_center).into(viewHolder.imageViewCompanyLogo);
        //abdalla end
        viewHolder.companyName.setText(companies.get(position).getSubCatName());
        return convertView;
       }

    public class ViewHolder{
        TextView companyName;
        //adballa start
        ImageView imageViewCompanyLogo;
        //abdalla end
    }

}
