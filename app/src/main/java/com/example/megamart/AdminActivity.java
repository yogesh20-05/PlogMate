package com.example.megamart;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.example.megamart.R;

public class AdminActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button uploadButton, selectImageButton;
    private Uri imageUri;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Initialize Firebase references
        storageReference = FirebaseStorage.getInstance().getReference("ploggingDriveImages");
        databaseReference = FirebaseDatabase.getInstance().getReference("ploggingDriveImage");

        // Initialize views
        imageView = findViewById(R.id.imageView);
        selectImageButton = findViewById(R.id.selectImageButton);
        uploadButton = findViewById(R.id.uploadButton);

        // Handle image selection
        selectImageButton.setOnClickListener(v -> openImagePicker());

        // Handle image upload
        uploadButton.setOnClickListener(v -> uploadImageToFirebase());
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*"); // Allow only images
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri); // Display the selected image
        }
    }

    private void uploadImageToFirebase() {
        if (imageUri != null) {
            // Generate a unique file name for the image
            StorageReference fileReference = storageReference.child(System.currentTimeMillis() + ".jpg");

            // Upload the image to Firebase Storage
            fileReference.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        // Get the download URL of the uploaded image
                        fileReference.getDownloadUrl().addOnSuccessListener(uri -> {
                            String imageUrl = uri.toString();

                            // Save the URL to Firebase Database
                            databaseReference.setValue(imageUrl).addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(AdminActivity.this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(AdminActivity.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                                }
                            });
                        });
                    })
                    .addOnFailureListener(e -> Toast.makeText(AdminActivity.this, "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        } else {
            Toast.makeText(AdminActivity.this, "No image selected", Toast.LENGTH_SHORT).show();
        }
    }
}