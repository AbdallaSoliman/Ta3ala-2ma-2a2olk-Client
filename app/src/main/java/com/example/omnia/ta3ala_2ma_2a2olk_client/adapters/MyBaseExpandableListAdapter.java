package com.example.omnia.ta3ala_2ma_2a2olk_client.adapters;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.TabHomeInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.squareup.picasso.Picasso;

public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listGroup;
    private HashMap<String, List<String>> listChild;
    HashMap<String , List<Integer>> id;
    private List<String> LogoUrl;
    public MyBaseExpandableListAdapter(Context c, List<String> lg, HashMap<String, List<String>> lc , HashMap<String , List<Integer>> id , List<String>LogoUrl) {
        context = c;
        listGroup = lg;
        listChild = lc;
        this.id=id;
        this.LogoUrl = LogoUrl;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listChild.get(listGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_layout, null);
        }

        TextView textViewItem = (TextView) convertView.findViewById(R.id.item);
        String text = (String) getChild(groupPosition, childPosition);
        textViewItem.setText(text);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listChild.get(listGroup.get(groupPosition)).size();
    }
    @Override
    public Object getGroup(int groupPosition) {
        return listGroup.get(groupPosition);
    }
    //@Override
    public Object getimage(int groupPosition) {
        return LogoUrl.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.group_layout, null);
        }

        String textGroup = (String) getGroup(groupPosition);
        //get application resource/drawable not in Activity class, using context
        Resources contextResources = context.getResources();
        //Drawable groupDrawable = contextResources.getDrawable(R.drawable.ic_launcher);
        //Set ImageView
        ImageView groupImage = (ImageView) convertView.findViewById(R.id.ivGroupIndicator);
        String imagepos = (String) getimage(groupPosition);
        ImageView groupIcon = (ImageView) convertView.findViewById(R.id.groupimage);

        //groupImage.setImageDrawable(groupDrawable);
//        switch ((String) getGroup(groupPosition)) {
//            case "Group A":
//                //groupImage set correct image
//                Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(groupIcon);
//                break;
//            case "Group B":
//                //groupImage set correct image
//                Picasso.get().load("https://res.cloudinary.com/demo/image/upload/sample.jpg").into(groupIcon);
//
//                break;
//            case "Group C":
//                //groupImage set correct image
//                break;
//        }
            Picasso.get().load(imagepos).resize(50,50).placeholder(R.drawable.loading).error(R.drawable.loading).into(groupIcon);
       // Picasso.get().load("https://cdn.zeplin.io/5b0c00ae2223cbb958ed28a6/assets/56150dd7-c820-48d7-a251-d12be417e9f9.png").into(groupIcon);
        groupImage.setSelected(isExpanded);
        TextView textViewGroup = (TextView) convertView.findViewById(R.id.group);
        textViewGroup.setText(textGroup);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}