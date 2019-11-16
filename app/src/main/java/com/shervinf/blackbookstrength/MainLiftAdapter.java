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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MainLiftAdapter extends FirestoreRecyclerAdapter<MainLiftPOJO, MainLiftAdapter.MainLiftHolder> {

    private OnItemClickListener mListener;
    private static String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private static FirebaseFirestore db =FirebaseFirestore.getInstance();
    private static DocumentReference docRef = db.collection("users").document(userID);
    private static double Max;



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






    public static void prepareData(final CollectionReference collectionReference, final MainLiftAdapter mlAdapter, final MainLiftPOJO mp1,final MainLiftPOJO mp2,final  MainLiftPOJO mp3,final  MainLiftPOJO mp4,final  MainLiftPOJO mp5,final  MainLiftPOJO mp6){
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) {
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                collectionReference.add(mp1);
                                collectionReference.add(mp2);
                                collectionReference.add(mp3);
                                collectionReference.add(mp4);
                                collectionReference.add(mp5);
                                collectionReference.add(mp6);
                                mlAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static double getMax() {
        return Max;
    }

    public static void setMax(double max) {
        Max = max;
    }

    public static double retrieveDeadliftMax(){
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    UserPOJO newUser = document.toObject(UserPOJO.class);
                    setMax(newUser.getDeadliftMax());
                }
            }
        });
        return getMax();
    }





    public static Double retrieveSquatMax(){
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    UserPOJO newUser = document.toObject(UserPOJO.class);
                    setMax(newUser.getSquatMax());
                }
            }
        });
        return getMax();
    }




    public static Double retrieveBenchMax(){
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    UserPOJO newUser = document.toObject(UserPOJO.class);
                    setMax(newUser.getBenchMax());
                }
            }
        });
        return getMax();
    }






    public static Double retrieveOHPMax(){
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    UserPOJO newUser = document.toObject(UserPOJO.class);
                    setMax(newUser.getOhpMax());
                }
            }
        });
        return getMax();
    }





    public void setOnItemClickListener(OnItemClickListener mListener){
        this.mListener = mListener;
    }
//    @Override
//    public long getItemId(int position) {
//        return super.getItemId(position);
//    }
}
