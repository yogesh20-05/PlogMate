package com.example.megamart;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SocialShelfFragment extends Fragment {

    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private List<Book> bookList;
    private EditText editNotes;
    private Button btnSaveNotes;
    private ImageView ploggingdrive;
    private DatabaseReference databaseReference;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerBookList);
        editNotes = view.findViewById(R.id.editNotes);
        btnSaveNotes = view.findViewById(R.id.btnSaveNotes);
        ploggingdrive = view.findViewById(R.id.ploggingdrive);

        // Initialize book list
        bookList = new ArrayList<>();
        bookList.add(new Book("The Alchemist", false));
        bookList.add(new Book("Atomic Habits", false));
        bookList.add(new Book("Rich Dad Poor Dad", false));

        // Set up RecyclerView
        adapter = new BookAdapter(bookList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // Save Notes Button Click
        btnSaveNotes.setOnClickListener(v -> {
            String notes = editNotes.getText().toString();
            if (!notes.isEmpty()) {
                Toast.makeText(getContext(), "Notes saved: " + notes, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Please enter some notes", Toast.LENGTH_SHORT).show();
            }
        });

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("shelfFragmentImage");

        // Fetch and update the image
        fetchImageFromDatabase();
    }

    private void fetchImageFromDatabase() {
        databaseReference.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String imageUrl = snapshot.getValue(String.class);
                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        // Load the image into ImageView using Glide
                        Glide.with(requireContext())
                                .load(imageUrl)
                                .placeholder(R.drawable.drive) // Optional placeholder
                                .error(R.drawable.error_image) // Optional error image
                                .into(ploggingdrive);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors.
                Toast.makeText(getContext(), "Failed to load image.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}