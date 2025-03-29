package com.example.megamart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.example.megamart.R;
import java.util.Calendar;

public class PloggingFragment extends Fragment {

    private ImageView ploggingDrive;

    public PloggingFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plogging, container, false);

        // Initialize ImageView
        ploggingDrive = view.findViewById(R.id.ploggingdrive);

        // Fetch and update image dynamically
        fetchLatestImage();

        return view;
    }

    private void fetchLatestImage() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == Calendar.SUNDAY) { // Load image only on Sundays
            DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("ploggingDriveImage");

            databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String imageUrl = snapshot.getValue(String.class);
                        Picasso.get().load(imageUrl).into(ploggingDrive); // Load image dynamically
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle error
                }
            });
        }
    }
}
