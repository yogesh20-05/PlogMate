package com.example.megamart;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.megamart.common.NetworkChangeListener;
import com.example.megamart.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    ImageView ivLoginlogo;
    TextView etLoginEmail,etPassword, tvREgister;
    Button btnLogin;
    CheckBox cbShowPass;
    BottomNavigationView bottomNavigationView;
    NetworkChangeListener networkChangeListener=new NetworkChangeListener();

    ProgressDialog progressDialog;

    @Override
    protected void onStart(){
        super.onStart();

        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener,intentFilter);
    }

    @Override
    protected void onStop(){
        super.onStop();
        unregisterReceiver(networkChangeListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        etPassword=findViewById(R.id.etLoginPassword);
        etLoginEmail=findViewById(R.id.etLoginEmail);
        binding.tvRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(i);
            }
        });

        /*binding.btnLoginlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if(etLoginEmail.getText().toString().isEmpty())
                {
                    etLoginEmail.setError("Please enter username ");
                }
                else if (etPassword.getText().toString().isEmpty()) {
                    etPassword.setError("Please enter password ");
                } else if (etLoginEmail.getText().toString().length()<8) {
                    etLoginEmail.setError("Username must be greater than 8 characters");

                }
                else {
                    String email=binding.etLoginEmail.getText().toString().trim();
                    String pass=binding.etLoginPassword.getText().toString().trim();
                    progressDialog=new ProgressDialog(LoginActivity.this);
                    progressDialog.setTitle("Loging");
                    progressDialog.setMessage("Please wait");
                    progressDialog.show();
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    progressDialog.cancel();
                                    Intent i=new Intent(LoginActivity.this,HomeActivity.class);
                                    startActivity(i);
                                    FirebaseFirestore.getInstance().collection("Users")
                                            .document(FirebaseAuth.getInstance()
                                                    .getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                @Override
                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                    UserModel userModel=documentSnapshot.toObject(UserModel.class);
                                                    new MySharedPreferences(LoginActivity.this).setMyDeta(userModel.getUsrPhNumber());
                                                }
                                            });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(LoginActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                                    progressDialog.cancel();
                                }
                            });
                }

            }
        });*/

        binding.btnLoginlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });







}}