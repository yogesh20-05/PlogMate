package com.example.megamart;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class PloggingFragment extends Fragment {

    public PloggingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plogging, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager2 photoSlider = view.findViewById(R.id.photoImage);

        // Set up the image slider
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.drive); // Replace with your drawable images
        images.add(R.drawable.plogging);
        images.add(R.drawable.ss);

        ImageSliderAdapter sliderAdapter = new ImageSliderAdapter(images);
        photoSlider.setAdapter(sliderAdapter);
    }

    // Simple Adapter for Image Slider
    private static class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.SliderViewHolder> {
        private final List<Integer> imageList;

        public ImageSliderAdapter(List<Integer> imageList) {
            this.imageList = imageList;
        }

        @NonNull
        @Override
        public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_slider, parent, false);
            return new SliderViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
            holder.imageView.setImageResource(imageList.get(position));
        }

        @Override
        public int getItemCount() {
            return imageList.size();
        }

        static class SliderViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            SliderViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.photoImage);
            }
        }
    }
}