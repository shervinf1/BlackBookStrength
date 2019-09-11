package com.shervinf.blackbookstrength;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalorieAdapter extends RecyclerView.Adapter<CalorieAdapter.MyViewHolder> {

    private ArrayList<CaloriePOJO> arrayList;

    //Default Constructor
    public CalorieAdapter(ArrayList<CaloriePOJO> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v("CreateViewHolder", "in onCreateViewHolder");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.calorie_list_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, calorie, calorieUnit;
        public MyViewHolder(View itemView) {
            super(itemView);
            Log.v("ViewHolder", "in View Holder");
            date = itemView.findViewById(R.id.dateTextView);
            calorie = itemView.findViewById(R.id.calorieTextView);
            calorieUnit = itemView.findViewById(R.id.calorieUnitTextView);
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.v("BindViewHolder", "in onBindViewHolder");
        CaloriePOJO calorie = arrayList.get(position);
        holder.calorie.setText(calorie.getCalorie());
        holder.calorieUnit.setText(calorie.getCalorieUnit());
        holder.date.setText(calorie.getDate());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
