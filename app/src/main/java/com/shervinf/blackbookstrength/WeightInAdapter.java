package com.shervinf.blackbookstrength;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeightInAdapter extends RecyclerView.Adapter<WeightInAdapter.MyViewHolder> {

    private ArrayList<WeightInPOJO> arrayList;

    //Default Constructor
    public WeightInAdapter(ArrayList<WeightInPOJO> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v("CreateViewHolder", "in onCreateViewHolder");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weightin_list_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, weight, weightUnit;

        public MyViewHolder(View itemView) {
            super(itemView);
            Log.v("ViewHolder", "in View Holder");
            date = itemView.findViewById(R.id.dateTextView);
            weight = itemView.findViewById(R.id.weightInTextView);
            weightUnit = itemView.findViewById(R.id.weightInUnitTextView);
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.v("BindViewHolder", "in onBindViewHolder");
        WeightInPOJO weightIn = arrayList.get(position);
        holder.weight.setText(weightIn.getWeight().toString());
        holder.weightUnit.setText(weightIn.getWeightUnit());
        holder.date.setText(weightIn.getDate());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
