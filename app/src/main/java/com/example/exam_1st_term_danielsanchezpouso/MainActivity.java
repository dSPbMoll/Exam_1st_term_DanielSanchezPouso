package com.example.exam_1st_term_danielsanchezpouso;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements AddTrainingDialog.AddTrainingListener {

    private ArrayList<Training> trainingList;
    private TrainingAdapter adapter;
    private int selectedPosition = -1;

    // Keys for saving state
    private static final String KEY_LIST = "KEY_LIST";
    private static final String KEY_POSITION = "KEY_POSITION";

    // Keys for Fragment arguments
    public static final String KEY_TITLE = "TITLE";
    public static final String KEY_DESC = "DESCRIPTION";
    public static final String KEY_IMG = "IMAGE";

    // Array of available image resources for new entries
    private final int[] availableImages = {
            R.drawable.extremitats_a_tope,
            R.drawable.agonia_maxima,
            R.drawable.entrenament_especial,
            R.drawable.forca_i_longitud
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // --- 1. DATA RECOVERY LOGIC ---
        if (savedInstanceState != null) {
            // Restore list and position after rotation
            trainingList = (ArrayList<Training>) savedInstanceState.getSerializable(KEY_LIST);
            selectedPosition = savedInstanceState.getInt(KEY_POSITION, -1);
        } else {
            // First time load: Initialize base data
            trainingList = new ArrayList<>();
            trainingList.add(new Training("Extremitats a Tope", "Cremar grasa", R.drawable.extremitats_a_tope));
            trainingList.add(new Training("Agonia Màxima", "Ganar múscul", R.drawable.agonia_maxima));
            trainingList.add(new Training("Entrenament Especial", "Flexibilitat", R.drawable.entrenament_especial));
            trainingList.add(new Training("Força i Longitud", "Postura", R.drawable.forca_i_longitud));
        }

        // --- ADAPTER SETUP ---
        adapter = new TrainingAdapter(this, trainingList);
        ListView listView = findViewById(R.id.listView); // Renamed ID in XML (pending)
        listView.setAdapter(adapter);

        // --- LISTENERS ---
        listView.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            showDetail(position);
        });

        Button btnAction = findViewById(R.id.btnAction); // Renamed ID in XML (pending)
        if (btnAction != null) {
            btnAction.setOnClickListener(v -> {
                AddTrainingDialog dialog = new AddTrainingDialog();
                dialog.show(getSupportFragmentManager(), "AddDialog");
            });
        }

        // --- 2. RESTORE FRAGMENT IF NEEDED ---
        // If an item was selected, reload the fragment immediately (handles rotation)
        if (selectedPosition != -1) {
            if (selectedPosition < trainingList.size()) {
                showDetail(selectedPosition);
            }
        }
    }

    // --- 3. SAVE STATE BEFORE ROTATION ---
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the current selection and the full list (including new user entries)
        outState.putInt(KEY_POSITION, selectedPosition);
        outState.putSerializable(KEY_LIST, trainingList);
    }

    /**
     * Helper method to handle Fragment transactions.
     * Detects if we are in Landscape or Portrait mode based on container existence.
     */
    private void showDetail(int position) {
        Training selectedTraining = trainingList.get(position);

        // Prepare Fragment
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, selectedTraining.getTitle());
        bundle.putString(KEY_DESC, selectedTraining.getDescription());
        bundle.putInt(KEY_IMG, selectedTraining.getImageResId());
        fragment.setArguments(bundle);

        // Check for Landscape Container
        View landContainer = findViewById(R.id.detailContainerLand); // Renamed ID in XML (pending)

        if (landContainer != null) {
            // Landscape Mode: Update side fragment
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detailContainerLand, fragment)
                    .commit();
        } else {
            // Portrait Mode: Replace main screen
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mainContainer, fragment) // Renamed ID in XML (pending)
                    .addToBackStack(null)
                    .commit();
        }
    }

    // --- INTERFACE IMPLEMENTATION ---
    @Override
    public void onTrainingAdded(String title, String description) {
        // Pick random image
        int randomIndex = new Random().nextInt(availableImages.length);
        int randomImage = availableImages[randomIndex];

        // Create and add new object
        Training newTraining = new Training(title, description, randomImage);
        trainingList.add(newTraining);
        adapter.notifyDataSetChanged();
    }
}