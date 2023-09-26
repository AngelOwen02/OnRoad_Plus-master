package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import com.pnla.onroadplus.R;
public class AlgorithmAdapter extends ArrayAdapter<AlgorithmItem> {

    public AlgorithmAdapter(Context context,
                            ArrayList<AlgorithmItem> algorithmList)
    {
        super(context, 0, algorithmList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable
    View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable
    View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView,
                          ViewGroup parent)
    {
        // It is used to set our custom view.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.algorithm_spinner, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.text_view);
        AlgorithmItem currentItem = getItem(position);

        // It is used the name to the TextView when the
        // current item is not null.
        if (currentItem != null) {
            textViewName.setText(currentItem.getAlgorithmName());
        }
        return convertView;
    }
}