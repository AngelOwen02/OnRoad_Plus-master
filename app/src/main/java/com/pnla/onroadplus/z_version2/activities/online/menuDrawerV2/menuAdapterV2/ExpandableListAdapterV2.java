package com.pnla.onroadplus.z_version2.activities.online.menuDrawerV2.menuAdapterV2;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.activities.online.menuDrawerV2.menuModelV2.MenuModelV2;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapterV2 extends BaseExpandableListAdapter {

    private Context context;
    private List<MenuModelV2> listDataHeader;
    private HashMap<MenuModelV2, List<MenuModelV2>> listDataChild;
    private Typeface latoRegular, latoBold;

    public ExpandableListAdapterV2(Context context, List<MenuModelV2> listDataHeader, HashMap<MenuModelV2, List<MenuModelV2>> listChildData, Typeface latoRegular, Typeface latoBold) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;
        this.latoRegular = latoRegular;
        this.latoBold = latoBold;
    }

    @Override
    public MenuModelV2 getChild(int groupPosition, int childPosititon) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = getChild(groupPosition, childPosition).getMenuName();

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_child, null);
        }

        TextView txtListChild = convertView.findViewById(R.id.lblListItem);
        txtListChild.setText(childText);
        txtListChild.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
        txtListChild.setTypeface(latoRegular);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (this.listDataChild.get(this.listDataHeader.get(groupPosition)) == null)
            return 0;
        else
            return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                    .size();
    }

    @Override
    public MenuModelV2 getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = getGroup(groupPosition).getMenuName();
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_header, null);
        }

        ImageView imgvIcon = convertView.findViewById(R.id.imgvIcon);
        imgvIcon.setImageResource(getGroup(groupPosition).getImageIcon());

        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(latoBold);
        lblListHeader.setText(headerTitle);
        lblListHeader.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));

        ImageView imgvExpand = (ImageView) convertView.findViewById(R.id.imgvExpand);

        if (groupPosition == 0) {
            imgvExpand.setVisibility(View.VISIBLE);
        } else {
            imgvExpand.setVisibility(View.GONE);
        }

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
