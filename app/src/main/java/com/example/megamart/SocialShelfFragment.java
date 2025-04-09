package com.example.megamart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Arrays;
import java.util.List;

public class SocialShelfFragment extends Fragment {

    public SocialShelfFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_social_shelf, container, false);

        // Set up ViewPager2 for the image slider
        ViewPager2 photoSlider = view.findViewById(R.id.photoSlider);
        List<Integer> imageResIds = Arrays.asList(
                R.drawable.drive,
                R.drawable.ss,
                R.drawable.drive
        );
        PhotoSliderAdapter adapter = new PhotoSliderAdapter(requireContext(), imageResIds);
        photoSlider.setAdapter(adapter);

        // Set up RecyclerView for books (Horizontal scroll)
        RecyclerView booksRecyclerView = view.findViewById(R.id.booksRecyclerView);
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Sample book data (with author, title)
        List<Book> books = Arrays.asList(
                new Book("J.K. Rowling", "Harry Potter"),
                new Book("J.R.R. Tolkien", "The Lord of the Rings"),
                new Book("George Orwell", "1984"),
                new Book("Leo Tolstoy", "War and Peace"),
                new Book("Jane Austen", "Pride and Prejudice")
        );

        // Set up the adapter for the RecyclerView
        BookAdapter bookAdapter = new BookAdapter(books);
        booksRecyclerView.setAdapter(bookAdapter);

        return view;
    }
}