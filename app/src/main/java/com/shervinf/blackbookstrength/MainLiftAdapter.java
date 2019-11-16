package com.shervinf.blackbookstrength;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class MainLiftAdapter extends FirestoreRecyclerAdapter<MainLiftPOJO, MainLiftAdapter.MainLiftHolder> {

    private OnItemClickListener mListener;


    public MainLiftAdapter(@NonNull FirestoreRecyclerOptions<MainLiftPOJO> options) {
        super(options);
    }




    @Override
    protected void onBindViewHolder(@NonNull MainLiftHolder mainLiftHolder, int position, @NonNull MainLiftPOJO mainLiftPOJO) {
        Log.v("BindViewHolder", "in onBindViewHolder");
//        MainLiftPOJO mainLiftPOJO = arrayList.get(position);
        mainLiftHolder.weight.setText(mainLiftPOJO.getWeight().toString());
        mainLiftHolder.weightUnit.setText(mainLiftPOJO.getWeightUnit());
        mainLiftHolder.percentage.setText(mainLiftPOJO.getPercentage().toString());
        mainLiftHolder.rep.setText(mainLiftPOJO.getReps());
        if(!mainLiftPOJO.getChecked()) {
            mainLiftHolder.checkBoxView.setChecked(false);
        }
        else{
            mainLiftHolder.checkBoxView.setChecked(mainLiftPOJO.getChecked());
        }
    }





    @NonNull
    @Override
    public MainLiftHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_lift_list_layout,parent,false);
        return new MainLiftHolder(v);
    }





    class MainLiftHolder extends RecyclerView.ViewHolder{
        TextView weight, weightUnit, percentage, rep;
        CheckBox checkBoxView;

        public MainLiftHolder(@NonNull View itemView) {
            super(itemView);
            Log.v("ViewHolder", "in View Holder");
            weight = itemView.findViewById(R.id.weightTextView);
            weightUnit = itemView.findViewById(R.id.weightUnitTextView);
            percentage = itemView.findViewById(R.id.percentageTextView);
            rep = itemView.findViewById(R.id.repTextView);
            checkBoxView = itemView.findViewById(R.id.mainLiftCheckBox);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getAdapterPosition();
                    if(adapterPosition != RecyclerView.NO_POSITION && mListener!=null){
                        mListener.onItemClick(getSnapshots().getSnapshot(adapterPosition),adapterPosition);
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mListener){
        this.mListener = mListener;
    }
//    @Override
//    public long getItemId(int position) {
//        return super.getItemId(position);
//    }
}
