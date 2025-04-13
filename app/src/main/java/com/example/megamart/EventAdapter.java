package com.example.megamart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<String> events;
    private OnEventClickListener listener;

    // Step 1: Create an interface
    public interface OnEventClickListener {
        void onEventClick(String event);
    }

    // Step 2: Update constructor to accept the listener
    public EventAdapter(List<String> events, OnEventClickListener listener) {
        this.events = events;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        String event = events.get(position);
        holder.eventText.setText(event);

        // Step 3: Set click listener
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEventClick(event);
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventText;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventText = itemView.findViewById(R.id.eventText);
        }
    }
}