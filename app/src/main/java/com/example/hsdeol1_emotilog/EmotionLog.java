package com.example.hsdeol1_emotilog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * EmotionLog - Data model class representing a single emotion log entry.
 *
 * Purpose:
 * Encapsulates the data for each emotion log entry, including the emotion
 * type and the timestamp when it was logged.
 *
 * Design Rationale:
 * - Simple POJO (Plain Old Java Object) design for easy data management
 * - Immutable after creation (no setters) to maintain data integrity
 * - Provides formatted timestamp string for display purposes
 * - Timestamp stored as long (milliseconds) for easy comparison and sorting
 *
 * Outstanding Issues:
 * - None identified
 */
public class EmotionLog {

    private String emotion;
    private long timestamp;

    /**
     * Constructor for creating a new EmotionLog entry.
     *
     * @param emotion The emotion string (e.g., "😊 Happy")
     * @param timestamp The timestamp in milliseconds since epoch
     */
    public EmotionLog(String emotion, long timestamp) {
        this.emotion = emotion;
        this.timestamp = timestamp;
    }

    /**
     * Gets the emotion string.
     *
     * @return The emotion (includes emoticon and name)
     */
    public String getEmotion() {
        return emotion;
    }

    /**
     * Gets the raw timestamp value.
     *
     * @return Timestamp in milliseconds since epoch
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Gets a formatted string representation of the timestamp.
     * Format: "MMM dd, yyyy HH:mm:ss" (e.g., "Jan 15, 2025 14:30:45")
     *
     * @return Formatted timestamp string for display
     */
    public String getFormattedTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    /**
     * Gets a short formatted date string (without time).
     * Format: "MMM dd, yyyy" (e.g., "Jan 15, 2025")
     * Useful for grouping logs by date.
     *
     * @return Formatted date string
     */
    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    /**
     * Gets only the time portion of the timestamp.
     * Format: "HH:mm:ss" (e.g., "14:30:45")
     *
     * @return Formatted time string
     */
    public String getFormattedTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
}