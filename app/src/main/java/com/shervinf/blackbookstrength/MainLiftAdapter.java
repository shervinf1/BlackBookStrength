package com.shervinf.blackbookstrength;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainLiftAdapter extends RecyclerView.Adapter<MainLiftAdapter.MyViewHolder> {

    private ArrayList<MainLift> arrayList;

    //Default Constructor
    public MainLiftAdapter(ArrayList<MainLift> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v("CreateViewHolder", "in onCreateViewHolder");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_lift_list_layout,parent,false);

        return new MyViewHolder(itemView);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView weight,weightUnit, percentage, rep ;
        public MyViewHolder(View itemView) {
            super(itemView);
            Log.v("ViewHolder","in View Holder");
            weight = itemView.findViewById(R.id.weightTextView);
            weightUnit = itemView.findViewById(R.id.weightUnitTextView);
            percentage = itemView.findViewById(R.id.percentageTextView);
            rep = itemView.findViewById(R.id.repTextView);
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int  position) {
        Log.v("BindViewHolder", "in onBindViewHolder");
        MainLift mainLift = arrayList.get(position);
        holder.weight.setText(mainLift.getWeight().toString());
        holder.weightUnit.setText(mainLift.getWeightUnit());
        holder.percentage.setText(mainLift.getPercentage().toString());
        holder.rep.setText(mainLift.getReps());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
