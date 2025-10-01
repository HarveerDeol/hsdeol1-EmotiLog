package com.example.hsdeol1_emotilog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * EmotionLogAdapter - RecyclerView adapter for displaying emotion log entries.
 *
 * Purpose:
 * Provides the bridge between the emotion log data and the RecyclerView UI component.
 * Handles creating and binding view holders for each log entry.
 *
 * Design Rationale:
 * - Extends RecyclerView.Adapter for efficient list display
 * - Uses ViewHolder pattern for performance optimization
 * - Displays emotion and timestamp for each entry
 * - Supports dynamic updates to the log list
 * - Simple, clean design focusing on readability
 *
 * Outstanding Issues:
 * - No item click listeners (could add for viewing details or editing)
 * - No swipe-to-delete functionality (could be a nice enhancement)
 * - No animations for list updates (could improve UX)
 */
public class EmotionLogAdapter extends RecyclerView.Adapter<EmotionLogAdapter.EmotionLogViewHolder> {

    private List<EmotionLog> emotionLogs;

    /**
     * Constructor initializes the adapter with a list of emotion logs.
     *
     * @param emotionLogs Initial list of EmotionLog entries
     */
    public EmotionLogAdapter(List<EmotionLog> emotionLogs) {
        this.emotionLogs = emotionLogs;
    }

    /**
     * Creates new ViewHolder instances when needed.
     * Inflates the item layout for each log entry.
     *
     * @param parent The ViewGroup into which the new View will be added
     * @param viewType The view type of the new View (not used in this simple case)
     * @return A new EmotionLogViewHolder
     */
    @NonNull
    @Override
    public EmotionLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_emotion_log, parent, false);
        return new EmotionLogViewHolder(view);
    }

    /**
     * Binds data to an existing ViewHolder.
     * Updates the ViewHolder's views with data from the specified position.
     *
     * @param holder The ViewHolder to update
     * @param position The position of the item in the dataset
     */
    @Override
    public void onBindViewHolder(@NonNull EmotionLogViewHolder holder, int position) {
        EmotionLog log = emotionLogs.get(position);
        holder.tvEmotion.setText(log.getEmotion());
        holder.tvTimestamp.setText(log.getFormattedTimestamp());
    }

    /**
     * Returns the total number of items in the dataset.
     *
     * @return The size of the emotion logs list
     */
    @Override
    public int getItemCount() {
        return emotionLogs.size();
    }

    /**
     * Updates the adapter's dataset with a new list of logs.
     * Notifies the RecyclerView that the data has changed.
     *
     * @param newLogs The new list of EmotionLog entries
     */
    public void updateLogs(List<EmotionLog> newLogs) {
        this.emotionLogs = newLogs;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder class for emotion log items.
     * Holds references to the views for each log entry.
     */
    static class EmotionLogViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmotion;
        TextView tvTimestamp;

        /**
         * Constructor finds and stores references to child views.
         *
         * @param itemView The root view of the item layout
         */
        public EmotionLogViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmotion = itemView.findViewById(R.id.tvEmotion);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
        }
    }
}