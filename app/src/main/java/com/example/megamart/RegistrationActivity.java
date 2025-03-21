
package com.example.megamart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.megamart.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistrationActivity extends AppCompatActivity {
    ActivityRegistrationBinding binding;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    binding.regToLog.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
            
        }
    });

    binding.btnRegisterregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = binding.etRegisterName.getText().toString();
                String Username = binding.etRegisterrUsername.getText().toString();
                String phoneNum = binding.etRegisterphno.getText().toString();
                String Email = binding.etRegisterEmailid.getText().toString();
                String Password = binding.etRegisterPassword.getText().toString();

                progressDialog = new ProgressDialog(RegistrationActivity.this);

                progressDialog.setTitle("Creating");
                progressDialog.setMessage("Account");
                progressDialog.show();


                FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(Email.trim(), Password.trim())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                UserProfileChangeRequest userProfileChangeRequest=new UserProfileChangeRequest.Builder().setDisplayName(Name).build();
                                FirebaseAuth.getInstance().getCurrentUser().updateProfile(userProfileChangeRequest);
                                new MySharedPreferences(RegistrationActivity.this).setMyDeta(phoneNum);
                                UserModel userModel=new UserModel();
                                userModel.setuserName(Name);
                                userModel.setusrName(Username);
                                userModel.setusrEmail(Email);
                                userModel.setusrPhNumber(phoneNum);
                                userModel.setusrPassword(Password);
                                FirebaseFirestore
                                        .getInstance()
                                        .collection("Users")
                                        .document(FirebaseAuth.getInstance().getUid()).set(userModel);

                                reset();


                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {


                                Toast.makeText(RegistrationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }

    private void reset() {
        progressDialog.cancel();
        Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show();
        FirebaseAuth.getInstance().signOut();

    }
}