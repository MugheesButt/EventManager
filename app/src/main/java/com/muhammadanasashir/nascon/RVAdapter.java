package com.muhammadanasashir.nascon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {
    private ArrayList<String> mDataset;
    int View_flag = 0;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public TextView tv;
        public ImageView image ;
        public MyViewHolder(View v) {
            super(v);
            view = v;
            tv = view.findViewById(R.id.id_row_notification);
            image = view.findViewById(R.id.id_row_memories);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RVAdapter(ArrayList<String> myDataset, int flag) {

        mDataset = myDataset;
        View_flag = flag;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = null ;
        if (View_flag == 0)
        {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_notification, parent, false);
        }

        else if (View_flag == 2)
        {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_admin_notification, parent, false);
        }

        else
        {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_memories, parent, false);
        }

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        if (View_flag == 0)
        {
            holder.tv.setText(mDataset.get(position));
        }

        else if (View_flag == 1)
        {
            Picasso.get().load(mDataset.get(position)).into(holder.image);
        }

        else
        {
            holder.tv.setText(mDataset.get(position));
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}