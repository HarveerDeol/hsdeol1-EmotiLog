package com.example.hsdeol1_emotilog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EmotionLogManager - Manages the collection of emotion log entries.
 *
 * Purpose:
 * Provides centralized management for all emotion log data including
 * adding new logs, retrieving logs, and generating statistical summaries.
 *
 * Design Rationale:
 * - Uses ArrayList for efficient sequential access and insertion
 * - Maintains logs in reverse chronological order (newest first)
 * - Provides summary statistics using HashMap for frequency counting
 * - All data stored in memory only (no persistence as per requirements)
 * - Thread-safe operations not implemented (single-threaded UI access assumed)
 *
 * Outstanding Issues:
 * - No date range filtering capability (future enhancement)
 * - Could add methods for daily/weekly/monthly breakdowns
 * - No limit on number of logs (could cause memory issues with extensive use)
 */
public class EmotionLogManager {

    private List<EmotionLog> emotionLogs;

    /**
     * Constructor initializes the emotion log list.
     */
    public EmotionLogManager() {
        emotionLogs = new ArrayList<>();
    }

    /**
     * Adds a new emotion log entry with the current timestamp.
     * The new log is added at the beginning of the list (newest first).
     *
     * @param emotion The emotion string to log
     */
    public void addLog(String emotion) {
        long currentTime = System.currentTimeMillis();
        EmotionLog log = new EmotionLog(emotion, currentTime);
        // Add at the beginning for reverse chronological order
        emotionLogs.add(0, log);
    }

    /**
     * Retrieves all emotion logs.
     * Returns a copy of the list to prevent external modification.
     *
     * @return List of all EmotionLog entries (newest first)
     */
    public List<EmotionLog> getAllLogs() {
        return new ArrayList<>(emotionLogs);
    }

    /**
     * Gets the total number of logged emotions.
     *
     * @return Total count of emotion logs
     */
    public int getTotalLogCount() {
        return emotionLogs.size();
    }

    /**
     * Generates a summary of all logged emotions with frequency counts.
     *
     * The summary includes:
     * - Total number of logs
     * - Count for each emotion type
     * - Formatted as a readable string
     *
     * @return Formatted summary string with statistics
     */
    public String getSummary() {
        if (emotionLogs.isEmpty()) {
            return "";
        }

        // Count frequency of each emotion
        Map<String, Integer> emotionCounts = new HashMap<>();
        for (EmotionLog log : emotionLogs) {
            String emotion = log.getEmotion();
            emotionCounts.put(emotion, emotionCounts.getOrDefault(emotion, 0) + 1);
        }

        // Build summary string
        StringBuilder summary = new StringBuilder();
        summary.append("📊 Emotion Log Summary\n");
        summary.append("━━━━━━━━━━━━━━━━━━━━\n\n");
        summary.append("Total Logs: ").append(emotionLogs.size()).append("\n\n");

        // Sort emotions by count (descending)
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(emotionCounts.entrySet());
        Collections.sort(sortedEntries, (a, b) -> b.getValue().compareTo(a.getValue()));

        // Add each emotion count
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            summary.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append(" times\n");
        }

        return summary.toString();
    }

    /**
     * Clears all emotion logs.
     * Useful for testing or if user wants to start fresh.
     */
    public void clearAllLogs() {
        emotionLogs.clear();
    }

    /**
     * Gets logs for a specific date (future enhancement placeholder).
     * Currently returns all logs.
     *
     * @param date The date string to filter by
     * @return List of logs for that date
     */
    public List<EmotionLog> getLogsByDate(String date) {
        List<EmotionLog> filteredLogs = new ArrayList<>();
        for (EmotionLog log : emotionLogs) {
            if (log.getFormattedDate().equals(date)) {
                filteredLogs.add(log);
            }
        }
        return filteredLogs;
    }
}