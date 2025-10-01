package com.example.hsdeol1_emotilog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity - The main activity for the EmotiLog application.
 *
 * Purpose:
 * This activity serves as the primary interface for the emotion logging app.
 * It displays emoticon buttons that users can tap to log their feelings,
 * and shows a summary of logged emotions.
 *
 * Design Rationale:
 * - Uses a simple, intuitive layout with large emoticon buttons for easy access
 * - Implements a RecyclerView to display the log history efficiently
 * - Manages the emotion log list in memory (non-persistent as per requirements)
 * - Provides real-time feedback with Toast messages and summary updates
 *
 * Outstanding Issues:
 * - Data does not persist between sessions (by design requirement)
 * - No date range filtering implemented yet (could be future enhancement)
 * - Summary shows all-time data rather than daily breakdown
 */
public class MainActivity extends AppCompatActivity {

    // UI Components
    private Button btnHappy, btnSad, btnGrateful, btnAngry, btnExcited, btnTired, btnViewSummary;
    private RecyclerView recyclerViewLogs;
    private TextView tvSummary;

    // Data Management
    private EmotionLogManager logManager;
    private EmotionLogAdapter logAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Handle window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the emotion log manager
        logManager = new EmotionLogManager();

        // Initialize UI components
        initializeViews();
        setupRecyclerView();
        setupButtonListeners();
    }

    /**
     * Initializes all view components by finding them in the layout.
     */
    private void initializeViews() {
        // Emoticon buttons
        btnHappy = findViewById(R.id.btnHappy);
        btnSad = findViewById(R.id.btnSad);
        btnGrateful = findViewById(R.id.btnGrateful);
        btnAngry = findViewById(R.id.btnAngry);
        btnExcited = findViewById(R.id.btnExcited);
        btnTired = findViewById(R.id.btnTired);

        // Control buttons and views
        btnViewSummary = findViewById(R.id.btnViewSummary);
        recyclerViewLogs = findViewById(R.id.recyclerViewLogs);
        tvSummary = findViewById(R.id.tvSummary);
    }

    /**
     * Sets up the RecyclerView to display the emotion log history.
     * Uses a LinearLayoutManager for vertical scrolling.
     */
    private void setupRecyclerView() {
        logAdapter = new EmotionLogAdapter(new ArrayList<>());
        recyclerViewLogs.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewLogs.setAdapter(logAdapter);
    }

    /**
     * Sets up click listeners for all emoticon buttons and the summary button.
     * Each emoticon button logs the corresponding emotion when clicked.
     */
    private void setupButtonListeners() {
        // Happy button
        btnHappy.setOnClickListener(v -> logEmotion("😊 Happy"));

        // Sad button
        btnSad.setOnClickListener(v -> logEmotion("😢 Sad"));

        // Grateful button
        btnGrateful.setOnClickListener(v -> logEmotion("🙏 Grateful"));

        // Angry button
        btnAngry.setOnClickListener(v -> logEmotion("😠 Angry"));

        // Excited button
        btnExcited.setOnClickListener(v -> logEmotion("🎉 Excited"));

        // Tired button
        btnTired.setOnClickListener(v -> logEmotion("😴 Tired"));

        // View Summary button
        btnViewSummary.setOnClickListener(v -> displaySummary());
    }

    /**
     * Logs an emotion with the current timestamp.
     * Updates the RecyclerView and provides user feedback via Toast.
     *
     * @param emotion The emotion string to log (includes emoticon and name)
     */
    private void logEmotion(String emotion) {
        // Add the emotion to the log manager
        logManager.addLog(emotion);

        // Update the RecyclerView with the latest logs
        List<EmotionLog> logs = logManager.getAllLogs();
        logAdapter.updateLogs(logs);

        // Scroll to the top to show the newest entry
        recyclerViewLogs.smoothScrollToPosition(0);

        // Show feedback to the user
        Toast.makeText(this, emotion + " logged!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Displays a summary of all logged emotions with frequency counts.
     * Updates the summary TextView with formatted statistics.
     */
    private void displaySummary() {
        String summary = logManager.getSummary();

        if (summary.isEmpty()) {
            tvSummary.setText("No logs yet. Start logging your emotions!");
        } else {
            tvSummary.setText(summary);
        }

        // Make summary visible if it was hidden
        tvSummary.setVisibility(View.VISIBLE);
    }
}