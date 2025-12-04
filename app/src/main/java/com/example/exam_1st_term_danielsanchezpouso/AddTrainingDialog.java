package com.example.exam_1st_term_danielsanchezpouso;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddTrainingDialog extends DialogFragment {

    // 1. Interface Definition
    public interface AddTrainingListener {
        void onTrainingAdded(String title, String description);
    }

    private AddTrainingListener listener;

    // 2. Attach Listener
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (AddTrainingListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement AddTrainingListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 3. Inflate Layout
        View view = inflater.inflate(R.layout.dialog_add_training, container, false);

        // UI References (Updated IDs to English)
        EditText etTitle = view.findViewById(R.id.etDialogTitle);
        EditText etDesc = view.findViewById(R.id.etDialogDesc);
        Button btnSave = view.findViewById(R.id.btnDialogSave);
        Button btnCancel = view.findViewById(R.id.btnDialogCancel);

        // Cancel Logic
        btnCancel.setOnClickListener(v -> dismiss());

        // 4. Save Logic
        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String description = etDesc.getText().toString();

            if (!title.isEmpty()) {
                listener.onTrainingAdded(title, description);
                dismiss();
            } else {
                etTitle.setError("Enter a title");
            }
        });

        return view;
    }
}