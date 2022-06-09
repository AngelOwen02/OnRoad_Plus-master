package com.pnla.onroadplus.z_version2.fragments.commands.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.fragments.commands.models.get.CommandV2;

import java.util.List;

public class CommandsV2Adapter extends RecyclerView.Adapter<CommandsV2Adapter.ViewHolder> {

    private List<CommandV2> commands;
    private Typeface latoBoldTypeface;
    private OnClickCommandListener listener;

    public CommandsV2Adapter(List<CommandV2> commands, Context context) {
        this.commands = commands;
        this.latoBoldTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Bold.ttf");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_command_v2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txvCommandName.setTypeface(latoBoldTypeface);
        holder.txvCommandName.setText(commands.get(position).getDescRoutine());

        if (!commands.get(position).isSelected()) {
            holder.imgvCommandSelected.setVisibility(View.GONE);
        } else {
            holder.imgvCommandSelected.setVisibility(View.VISIBLE);
        }

        holder.btnSendCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    if (!commands.get(position).isSelected()) {
                        listener.sendCommand(commands.get(position).getDescRoutine(), commands.get(position).getCveDevice(),
                                commands.get(position).getCveRoutine(), position);
                    } else {
                        listener.commandSent("Ya se ha enviado el comando: " + commands.get(position).getDescRoutine() + " a ");
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return commands.size();
    }

    public void updateItemSelected(int position) {
        commands.get(position).setSelected(true);
        notifyDataSetChanged();
    }

    public void setOnClickCommandListener(OnClickCommandListener listener) {
        this.listener = listener;
    }

    public interface OnClickCommandListener {
        void sendCommand(String commandName, int deviceCveToSend, int routineCveToSend, int position);

        void commandSent(String message);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txvCommandName;
        private ImageView btnSendCommand, imgvCommandSelected;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txvCommandName = itemView.findViewById(R.id.txvCommandName);
            btnSendCommand = itemView.findViewById(R.id.btnSendCommand);
            imgvCommandSelected = itemView.findViewById(R.id.imgvCommandSelected);
        }
    }


}
