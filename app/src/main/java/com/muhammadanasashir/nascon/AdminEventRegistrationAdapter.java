package com.muhammadanasashir.nascon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminEventRegistrationAdapter extends RecyclerView.Adapter<AdminEventRegistrationAdapter.ViewHolder> {

    Context ctx ;
    ArrayList<String> eventsList ;

    AdminEventRegistrationAdapter(Context ctx , ArrayList<String> eventsList)
    {
        this.ctx = ctx;
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public AdminEventRegistrationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_row_event, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.event.setText(eventsList.get(position));



        holder.event_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx , AdminRegisteredTeams.class);
                intent.putExtra("EventName" , holder.event.getText().toString());
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView event ;
        RelativeLayout event_row ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            event = itemView.findViewById(R.id.event_name);
            event_row = itemView.findViewById(R.id.event);
        }
    }
}
