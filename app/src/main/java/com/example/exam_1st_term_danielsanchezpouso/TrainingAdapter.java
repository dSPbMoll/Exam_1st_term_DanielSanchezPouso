package com.example.exam_1st_term_danielsanchezpouso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class TrainingAdapter extends ArrayAdapter<Training> {

    public TrainingAdapter(Context context, ArrayList<Training> trainings) {
        super(context, 0, trainings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1. Get current data object
        Training currentTraining = getItem(position);

        // 2. Inflate view if necessary
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_training, parent, false);
        }

        // 3. Bind UI elements (Updated IDs to English)
        ImageView imgIcon = convertView.findViewById(R.id.imgItemIcon);
        TextView txtTitle = convertView.findViewById(R.id.txtItemTitle);

        // 4. Populate data
        if (currentTraining != null) {
            txtTitle.setText(currentTraining.getTitle());
            imgIcon.setImageResource(currentTraining.getImageResId());
        }

        return convertView;
    }
}