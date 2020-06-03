package com.shervinf.blackbookstrength;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.MyViewHolder> {
    private ArrayList<ExercisePOJO> arrayList;
    private OnExerciseClickListener listener;




    //custom constructor.
    public ExerciseAdapter(ArrayList<ExercisePOJO> arrayList, OnExerciseClickListener listener) {
        this.listener = listener;
        this.arrayList = arrayList;
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int   viewType) {
        Log.v("CreateViewHolder", "in onCreateViewHolder");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_list_layout,parent,false);
        return new MyViewHolder(itemView);
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image, sets, reps;
        public MyViewHolder(View itemView) {
            super(itemView);
            Log.v("ViewHolder", "in View Holder");

            image = itemView.findViewById(R.id.exerciseImage);

            //Calling custom onclick listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onExerciseViewItemClicked(getAdapterPosition(), view.getId());
                }
            });
        }
    }





    @Override
    public void onBindViewHolder(MyViewHolder holder, final int  position) {
        Log.v("BindViewHolder", "in onBindViewHolder");
        ExercisePOJO exercisePOJO = arrayList.get(position);
        holder.image.setImageResource(exercisePOJO.getmIcon());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onExerciseViewItemClicked(position,view.getId());
            }
        });
    }




    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
