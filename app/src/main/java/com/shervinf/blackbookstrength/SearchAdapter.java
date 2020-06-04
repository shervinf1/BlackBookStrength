package com.shervinf.blackbookstrength;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList arrayList;
    private OnAssistanceExerciseClickListener listener;

    public SearchAdapter(ArrayList arrayList, OnAssistanceExerciseClickListener listener) {
        this.arrayList = arrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new SearchViewHolder(view);
    }





   public class SearchViewHolder extends RecyclerView.ViewHolder {
        public TextView assistance_exercise_name;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            assistance_exercise_name = itemView.findViewById(R.id.assistance_exercise_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnAssistanceExerciseViewItemClicked(getAdapterPosition(),v.getId());
                }
            });
        }
    }




    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        setExerciseName(((SearchViewHolder) holder).assistance_exercise_name, arrayList.get(position).toString());
        ((SearchViewHolder) holder).assistance_exercise_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnAssistanceExerciseViewItemClicked(position, v.getId());
            }
        });
    }




    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    private void setExerciseName(TextView textView, String text) {
        textView.setText(text);
    }

    public void filterList(ArrayList<String> filterList) {
        arrayList = filterList;
        notifyDataSetChanged();
    }


}
