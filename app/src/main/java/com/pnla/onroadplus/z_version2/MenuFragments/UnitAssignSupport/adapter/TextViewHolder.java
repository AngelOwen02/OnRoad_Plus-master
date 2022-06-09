package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;

class TextViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView textView;
    private final TextView realPercent;
    private final TextView unit_rute;
    private final TextView unit_name;
    private final TextView obj_percent;
    private final TextView difference;
    private final TextView unit_status;
    private final ImageView ic_unit_support;

    TextViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
        realPercent = itemView.findViewById(R.id.txt_unit_real);
        unit_rute = itemView.findViewById(R.id.txt_unit_rute);
        unit_name = itemView.findViewById(R.id.txt_unit_name);
        obj_percent = itemView.findViewById(R.id.txt_unit_objective);
        difference = itemView.findViewById(R.id.txt_unit_difference);
        unit_status = itemView.findViewById(R.id.txt_unit_status);
        ic_unit_support = itemView.findViewById(R.id.img_unit);
        itemView.setOnClickListener(this);
    }

    void bindText(String text) {
        textView.setText(text);
        unit_rute.setText("4.01");
        unit_name.setText("CTSSSL12");
        realPercent.setText("35%");
        obj_percent.setText("75%");
        difference.setText("-40%");
        difference.setTextColor(Color.RED);
        unit_status.setText("Atrasado");
        unit_status.setTextColor(Color.RED);
        ic_unit_support.setImageResource(R.drawable.ic_camion_verde);

        ic_unit_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Si funciona", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), textView.getText(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(view.getContext(),realPercent.getText(), Toast.LENGTH_SHORT).show();
    }
}
