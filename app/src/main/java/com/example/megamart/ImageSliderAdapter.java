package com.example.megamart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ViewHolder> {

    private int[] images;

    public ImageSliderAdapter(int[] images) {
        this.images = images;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photoImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photoImage = itemView.findViewById(R.id.photoImage); // matches item_photo_slider.xml
        }
    }

    @NonNull
    @Override
    public ImageSliderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo_slider, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderAdapter.ViewHolder holder, int position) {
        holder.photoImage.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }
}