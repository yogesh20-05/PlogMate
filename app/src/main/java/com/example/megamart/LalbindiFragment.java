package com.example.megamart;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Arrays;
import java.util.List;

public class LalbindiFragment extends Fragment {

    public LalbindiFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lalbindi, container, false);

        ViewPager2 photoSlider = view.findViewById(R.id.photoSlider);
        RecyclerView eventsRecyclerView = view.findViewById(R.id.eventsRecyclerView);

        // Local drawable image IDs
        List<Integer> imageResIds = Arrays.asList(
                R.drawable.drive,
                R.drawable.ss,
                R.drawable.drive
        );

        // Set adapter for image slider
        PhotoSliderAdapter adapter = new PhotoSliderAdapter(requireContext(), imageResIds);
        photoSlider.setAdapter(adapter);

        // Listen for image slider position change
        photoSlider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.d("ImageSlider", "Current position: " + position);
            }
        });

        // Extended event list
        List<String> eventList = Arrays.asList(
                "Blood Donation - April 20",
                "Health Checkup - April 25",
                "Free Eye Camp - April 30",
                "Nutrition Awareness - May 5",
                "Yoga Workshop - May 10",
                "Women’s Health Talk - May 15",
                "Vaccination Drive - May 20",
                "Mental Health Seminar - May 25",
                "Child Care Camp - May 30",
                "Skin Checkup Camp - June 5"
        );

        // Set event list adapter with click listener
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        eventsRecyclerView.setAdapter(new EventAdapter(eventList, new EventAdapter.OnEventClickListener() {
            @Override
            public void onEventClick(String event) {
                // Show event details in a dialog with bold event name
                String formattedMessage = "<b>" + event.split(" - ")[0] + "</b> - " + event.split(" - ")[1];
                new androidx.appcompat.app.AlertDialog.Builder(requireContext())
                        .setTitle("Event Details")
                        .setMessage("Details about the event:\n" + formattedMessage + "\n\nMore details can go here...")
                        .setPositiveButton("OK", null)
                        .setCancelable(true)
                        .show();
            }
        }));

        return view;
    }
}