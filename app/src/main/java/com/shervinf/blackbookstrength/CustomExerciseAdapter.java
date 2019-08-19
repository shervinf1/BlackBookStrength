package com.shervinf.blackbookstrength;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomExerciseAdapter extends RecyclerView.Adapter<CustomExerciseAdapter.MyViewHolder> {

    private ArrayList<Exercise> arrayList = new ArrayList<>();


    // A constructor.
    public CustomExerciseAdapter(ArrayList<Exercise> arrayList) {
        this.arrayList = arrayList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, sets, reps;
        public MyViewHolder(View itemView) {
            super(itemView);
            Log.v("ViewHolder","in View Holder");
            sets = itemView.findViewById(R.id.textView);
            reps = itemView.findViewById(R.id.textView2);
            name = itemView.findViewById(R.id.textView3);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int   viewType) {
        Log.v("CreateViewHolder", "in onCreateViewHolder");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_list_layout,parent,false);

        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, final int  position) {
        Log.v("BindViewHolder", "in onBindViewHolder");
        Exercise exercise = arrayList.get(position);
        holder.name.setText(exercise.getmName());
        holder.sets.setText(exercise.getmSets());
        holder.reps.setText(exercise.getmReps());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
