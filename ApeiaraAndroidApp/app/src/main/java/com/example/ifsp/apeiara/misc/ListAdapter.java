package com.example.ifsp.apeiara.misc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.example.ifsp.apeiara.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Henrique on 3/27/2016.
 */
public class ListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listHeader;
    private HashMap<String, List<String>> listChild;


    public ListAdapter (Context context,List<String> listHeader, HashMap<String, List<String>> listChild ) {
        this.context=context;
        this.listHeader=listHeader;
        this.listChild=listChild;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String  groupTitle=(String) getGroup(groupPosition);
        if(convertView==null){
            LayoutInflater inflater=
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cuidador_initial,parent,false);
        }

return convertView;
    }
}
