package com.shervinf.blackbookstrength;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class CalorieAdapter extends FirestoreRecyclerAdapter<CaloriePOJO, CalorieAdapter.CalorieHolder> {

    public CalorieAdapter(@NonNull FirestoreRecyclerOptions<CaloriePOJO> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CalorieHolder calorieHolder, int i, @NonNull CaloriePOJO caloriePOJO) {
        calorieHolder.calorieTextView.setText(caloriePOJO.getCalorie());
        calorieHolder.calorieUnitTextView.setText(caloriePOJO.getCalorieUnit());
        calorieHolder.dateTextView.setText(caloriePOJO.getDate());
    }

    @NonNull
    @Override
    public CalorieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.calorie_list_layout,parent,false);
        return new CalorieHolder(v);
    }

    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    class CalorieHolder extends RecyclerView.ViewHolder{
        TextView dateTextView;
        TextView calorieTextView;
        TextView calorieUnitTextView;

        public CalorieHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            calorieTextView = itemView.findViewById(R.id.calorieTextView);
            calorieUnitTextView = itemView.findViewById(R.id.calorieUnitTextView);
        }
    }
}


