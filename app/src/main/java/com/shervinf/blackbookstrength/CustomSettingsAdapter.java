package com.shervinf.blackbookstrength;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomSettingsAdapter extends RecyclerView.Adapter<CustomSettingsAdapter.MyViewHolder> {
    // declaring some fields.
    private ArrayList<SettingsPOJO> settingsList = new ArrayList<>();
    private OnExerciseClickListener listener;


    public CustomSettingsAdapter(ArrayList<SettingsPOJO> settingsList, OnExerciseClickListener listener) {
        this.listener = listener;
        this.settingsList = settingsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView settingsLabel, settingsSubLabel;

        public MyViewHolder(View itemView) {
            super(itemView);
            Log.v("BlackBook Strength", "in View Holder");
            settingsLabel = itemView.findViewById(R.id.settingsLabel);
            settingsSubLabel = itemView.findViewById(R.id.settingsSubLabel);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v("Blackbook Strength", "in onCreateViewHolder");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_list_layout, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.v("BindViewHolder", "in onBindViewHolder");
        SettingsPOJO settings = settingsList.get(position);
        holder.settingsLabel.setText(settings.getSettingsLabel());
        holder.settingsSubLabel.setText(settings.getSettingsSubLabel());
    }

    @Override
    public int getItemCount() {
        return settingsList.size();
    }

}
