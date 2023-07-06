package com.example.medi_consult;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<Doctor> list;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView drName;
        TextView drDegree;
        TextView drSpecialization;


        public ViewHolder(View view) {
            super(view);
            drName = (TextView) view.findViewById(R.id.doctoritem_textViewName);
            drDegree = (TextView) view.findViewById(R.id.doctoritem_textViewDegree);
            drSpecialization = (TextView) view.findViewById(R.id.doctoritem_textViewSpecialization);
        }
    }

    public CustomAdapter(ArrayList<Doctor> list,Context context) {
        this.list=list;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_item_doctor, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final int index=viewHolder.getAdapterPosition();

        String name=list.get(position).getFirstName()+" "+list.get(position).getLastName();
        String degree=list.get(position).getDegree();
        String specialization=list.get(position).getSpecialization();

        viewHolder.drName.setText(name);
        viewHolder.drDegree.setText(degree);
        viewHolder.drSpecialization.setText(specialization);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
