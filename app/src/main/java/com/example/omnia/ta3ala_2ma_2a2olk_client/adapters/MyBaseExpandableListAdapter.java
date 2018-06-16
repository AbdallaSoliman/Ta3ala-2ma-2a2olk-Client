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
    HashMap<String, List<Integer>> id;
    private List<String> LogoUrl;
    private List<Integer> noofquestions;

    public MyBaseExpandableListAdapter(Context c, List<String> lg, HashMap<String, List<String>> lc, HashMap<String, List<Integer>> id, List<String> LogoUrl, List<Integer> noofquestions) {
        context = c;
        listGroup = lg;
        listChild = lc;
        this.id = id;
        this.LogoUrl = LogoUrl;
        this.noofquestions = noofquestions;
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
        //abdalla start
        View lineSeparator = (View) convertView.findViewById(R.id.viewLineSupSeparator);
        if(isLastChild){
            lineSeparator.setVisibility(View.GONE);
        }else{
            lineSeparator.setVisibility(View.VISIBLE);
        }
        //abdalla end
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

    public Object getquestioncount(int groupPosition) {
        return noofquestions.get(groupPosition);
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
        int questionnumber = (Integer) getquestioncount(groupPosition);
        //get application resource/drawable not in Activity class, using context
        Resources contextResources = context.getResources();
        //Drawable groupDrawable = contextResources.getDrawable(R.drawable.ic_launcher);
        //Set ImageView
        ImageView groupImage = (ImageView) convertView.findViewById(R.id.ivGroupIndicator);
        String imagepos = (String) getimage(groupPosition);
        ImageView groupIcon = (ImageView) convertView.findViewById(R.id.groupimage);
        TextView question = (TextView) convertView.findViewById(R.id.Description);
        //abdalla start
        View lineSeparator = (View) convertView.findViewById(R.id.lineSeparator);
        if(isExpanded){
            lineSeparator.setVisibility(View.GONE);
        }else{
            lineSeparator.setVisibility(View.VISIBLE);
        }
        //abdalla end
        question.setText(questionnumber+"  "+ "Questions");
        Picasso.get().load(imagepos).resize(50, 50).placeholder(R.drawable.loading).onlyScaleDown().error(R.drawable.loading).into(groupIcon);
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