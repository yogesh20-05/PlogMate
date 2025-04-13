package com.example.megamart;



import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.megamart.databinding.ActivityCreatePostBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Calendar;
import java.util.UUID;

public class CreatePostActivity extends AppCompatActivity {
ActivityCreatePostBinding binding;
private Uri pickedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        binding=ActivityCreatePostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // Set as ActionBar


        binding.tvAddIamge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });


    }


    private void openGallery() {
        Intent i=new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(i,100);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.create_post_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.post) {
            ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.setTitle("Posting..");
            progressDialog.show();



            String id= UUID.randomUUID().toString();

            StorageReference storageReference=FirebaseStorage.getInstance().getReference("Posts/"+id+"/image.png");

                       if (pickedImageUri!=null){
                storageReference.putFile(pickedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                progressDialog.cancel();
                                finish();
                                Toast.makeText(CreatePostActivity.this,"Posted Successfully..",Toast.LENGTH_SHORT).show();

                                PostModel postModel=new PostModel(id,FirebaseAuth.getInstance()
                                        .getUid(),binding.postTextCaption.getText().toString(),
                                        uri.toString(),"0","0", Calendar.getInstance().getTimeInMillis());

                                FirebaseFirestore.getInstance().collection("Posts")
                                        .document(id).set(postModel);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@androidx.annotation.NonNull Exception e) {
                                progressDialog.cancel();
                                Toast.makeText(CreatePostActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@androidx.annotation.NonNull Exception e) {
                        progressDialog.cancel();
                        Toast.makeText(CreatePostActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }

            else{
                PostModel postModel=new PostModel(id,FirebaseAuth.getInstance()
                        .getUid(),binding.postTextCaption.getText().toString(),
                        null,"0","0", Calendar.getInstance().getTimeInMillis());

                FirebaseFirestore.getInstance().collection("Posts")
                        .document(id).set(postModel);
                progressDialog.cancel();

            }
        }


        return false;
    }









    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100){
            if(data!=null){
                pickedImageUri=data.getData();
                binding.ivPickedImage.setImageURI(pickedImageUri);
                Glide.with(CreatePostActivity.this)
                        .load(pickedImageUri).into(binding.ivPickedImage);
            }

        }
    }


}




































































































