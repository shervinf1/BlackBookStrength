package com.shervinf.blackbookstrength;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class WeightInAdapter extends FirestoreRecyclerAdapter<WeightInPOJO, WeightInAdapter.WeightInHolder> {

    public WeightInAdapter(@NonNull FirestoreRecyclerOptions<WeightInPOJO> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull WeightInHolder weightInHolder, int i, @NonNull WeightInPOJO weightInPOJO) {
        weightInHolder.weightInTextView.setText(weightInPOJO.getWeight());
        weightInHolder.weightInUnitTextView.setText(weightInPOJO.getWeightUnit());
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        String dateString = formatter.format(weightInPOJO.getTimeStamp());
        weightInHolder.dateTextView.setText(dateString);
    }

    @NonNull
    @Override
    public WeightInHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.weightin_list_layout,parent,false);
        return new WeightInHolder(v);
    }

    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    class WeightInHolder extends RecyclerView.ViewHolder{
        TextView dateTextView;
        TextView weightInTextView;
        TextView weightInUnitTextView;

        public WeightInHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            weightInTextView = itemView.findViewById(R.id.weightInTextView);
            weightInUnitTextView = itemView.findViewById(R.id.weightInUnitTextView);
        }
    }
}