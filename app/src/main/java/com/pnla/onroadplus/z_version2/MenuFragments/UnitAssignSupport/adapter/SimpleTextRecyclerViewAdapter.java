package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;

import java.util.List;

public class SimpleTextRecyclerViewAdapter extends RecyclerView.Adapter<TextViewHolder> {
    private final List<String> items;
    private final LayoutInflater inflater;

    public SimpleTextRecyclerViewAdapter(Context context, List<String> items) {
        this.inflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_recycler_view, parent, false);
        return new TextViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {
        holder.bindText(items.get(position));
    }
}
