package com.muhammadanasashir.nascon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdminEventAdapter extends RecyclerView.Adapter<AdminEventAdapter.ViewHolder> {

    Context ctx ;
    ArrayList<EventNode> eventsList ;

    AdminEventAdapter(Context ctx , ArrayList<EventNode> eventsList)
    {
        this.ctx = ctx;
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public AdminEventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_row_event, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final EventNode event = eventsList.get(position);
        holder.event.setText(event.getName());

        holder.event_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx , AdminEvent_Add.class);
                intent.putExtra("EventName" , event.getName());
                intent.putExtra("category" , event.getCategory());
                intent.putExtra("Edit" , 1);
                ctx.startActivity(intent);
            }
        });

        holder.event_row.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                //builder.setMessage("Are you sure you want to delete?").setTitle("Delete?");

                AlertDialog dialog;

                builder.setMessage("Delete event '" + event.getName() + "' ?")
                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Events");
                                reference.child(event.getName()).removeValue();
                            }
                        });

                dialog = builder.create();
                dialog.show();

                return true;
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
