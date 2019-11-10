package com.shervinf.blackbookstrength;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MainLiftAdapter extends RecyclerView.Adapter<MainLiftAdapter.MyViewHolder> {
    private ArrayList<MainLiftPOJO> arrayList;
    private OnMainLiftClickListener listener;
    private SparseBooleanArray itemStateArray= new SparseBooleanArray();






    //Default Constructor
    public MainLiftAdapter(ArrayList<MainLiftPOJO> arrayList,OnMainLiftClickListener listener) {
        this.arrayList = arrayList;
        this.listener = listener;
    }





    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.v("CreateViewHolder", "in onCreateViewHolder");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_lift_list_layout, parent, false);
        return new MyViewHolder(itemView);
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView weight, weightUnit, percentage, rep;
        CheckBox checkBoxView;

        public MyViewHolder(final View itemView) {
            super(itemView);
            Log.v("ViewHolder", "in View Holder");
            weight = itemView.findViewById(R.id.weightTextView);
            weightUnit = itemView.findViewById(R.id.weightUnitTextView);
            percentage = itemView.findViewById(R.id.percentageTextView);
            rep = itemView.findViewById(R.id.repTextView);
            checkBoxView = itemView.findViewById(R.id.checkMarkImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getAdapterPosition();
                    if (!itemStateArray.get(adapterPosition, false)) {
                        checkBoxView.setChecked(true);
                        itemStateArray.put(adapterPosition, true);
                    }
                    else  {
                        checkBoxView.setChecked(false);
                        itemStateArray.put(adapterPosition, false);
                    }
                }
            });
        }
    }




    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Log.v("BindViewHolder", "in onBindViewHolder");
        MainLiftPOJO mainLiftPOJO = arrayList.get(position);
        holder.weight.setText(mainLiftPOJO.getWeight().toString());
        holder.weightUnit.setText(mainLiftPOJO.getWeightUnit());
        holder.percentage.setText(mainLiftPOJO.getPercentage().toString());
        holder.rep.setText(mainLiftPOJO.getReps());
//        holder.checkBoxView.setImageResource(mainLiftPOJO.getIsFinished());
    }




    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
