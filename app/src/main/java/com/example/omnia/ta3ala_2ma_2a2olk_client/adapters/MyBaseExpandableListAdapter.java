package com.example.omnia.ta3ala_2ma_2a2olk_client.adapters;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.squareup.picasso.Picasso;

public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listGroup;
    private HashMap<String, List<String>> listChild;

    public MyBaseExpandableListAdapter(Context c, List<String> lg,
                                       HashMap<String, List<String>> lc) {
        context = c;
        listGroup = lg;
        listChild = lc;
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
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater infalInflater =
                    (LayoutInflater)context
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_layout, null);
        }

        TextView textViewItem =
                (TextView)convertView.findViewById(R.id.item);

        String text = (String)getChild(groupPosition, childPosition);

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

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition,
                             boolean isExpanded, View convertView,
                             ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater)context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.group_layout, null);
        }

        String textGroup = (String)getGroup(groupPosition);

        //get application resource/drawable not in Activity class, using context
        Resources contextResources = context.getResources();
        //Drawable groupDrawable = contextResources.getDrawable(R.drawable.ic_launcher);
        //Set ImageView
        ImageView groupImage = (ImageView)convertView.findViewById(R.id.ivGroupIndicator);
        ImageView groupIcon = (ImageView)convertView.findViewById(R.id.groupimage);
        //groupImage.setImageDrawable(groupDrawable);
        switch((String)getGroup(groupPosition))
        {
            case "Group A":
                //groupImage set correct image
                Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(groupIcon);
                break;
            case "Group B":
                //groupImage set correct image
                Picasso.get().load("https://res.cloudinary.com/demo/image/upload/sample.jpg").into(groupIcon);

                break;
            case "Group C":
                //groupImage set correct image
                break;
        }
        groupImage.setSelected(isExpanded);
        TextView textViewGroup = (TextView)convertView.findViewById(R.id.group);
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