package com.example.exam_1st_term_danielsanchezpouso;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 1. Inflate Layout
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        // 2. UI References (Updated IDs to English)
        ImageView imgDetail = view.findViewById(R.id.imgDetail);
        TextView txtTitle = view.findViewById(R.id.txtDetailTitle);
        TextView txtDesc = view.findViewById(R.id.txtDetailDesc);

        // 3. Get Data from Arguments
        // Note: Keys must match those used in MainActivity ("TITLE", "DESCRIPTION", "IMAGE")
        Bundle args = getArguments();
        if (args != null) {
            String receivedTitle = args.getString("TITLE");
            String receivedDesc = args.getString("DESCRIPTION");
            int receivedImg = args.getInt("IMAGE");

            // 4. Update UI
            txtTitle.setText(receivedTitle);
            txtDesc.setText(receivedDesc);
            imgDetail.setImageResource(receivedImg);
        }

        return view;
    }
}