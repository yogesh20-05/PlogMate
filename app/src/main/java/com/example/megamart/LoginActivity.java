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
import androidx.appcompat.app.AppCompatActivity;

import com.example.megamart.common.NetworkChangeListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class LoginActivity extends AppCompatActivity {
    ImageView ivLoginlogo;
    TextView etUsername,etPassword, tvREgister;
    Button btnLogin;
    CheckBox cbShowPass;
    BottomNavigationView bottomNavigationView;
    NetworkChangeListener networkChangeListener=new NetworkChangeListener();

    ProgressDialog progressDialog;

    @Override
    protected void onStart(){
        super.onStart();
        setTitle("PlogMate");
        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener,intentFilter);
    }

    @Override
    protected void onStop(){
        super.onStop();
        unregisterReceiver(networkChangeListener);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setTitle("HomeActivity");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);






        etPassword=findViewById(R.id.etLoginPassword);
        etUsername=findViewById(R.id.etLoginUsername);
        btnLogin=findViewById(R.id.btnLoginlogin);
        cbShowPass=findViewById(R.id.cbLoginShowpassword);
        tvREgister=findViewById(R.id.tvRegistration);





        tvREgister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });




        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etUsername.getText().toString().isEmpty())
                {
                    etUsername.setError("Please enter username ");
                }
                else if (etPassword.getText().toString().isEmpty()) {
                    etPassword.setError("Please enter password ");
                } else if (etUsername.getText().toString().length()<8) {
                    etUsername.setError("Username must be greater than 8 characters");

                }
                else {



                    AsyncHttpClient client=new AsyncHttpClient();
                    RequestParams params=new RequestParams();
                    params.put("username",etUsername.getText().toString());
                    params.put("password",etPassword.getText().toString());
                    Intent i=new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);

                    client.post("http://192.168.70.127:80/bookinfo/userlogin.php",params,new JsonHttpResponseHandler()
                            {

                                /*@Override
                                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                    super.onSuccess(statusCode, headers, response);
                                    try {
                                        String status=response.getString("success");
                                        if (status.equals("1"))
                                        {
                                            Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
                                            startActivity(intent);
                                        }
                                        else
                                        {
                                            Toast.makeText(LoginActivity.this,"Username  and Password are invallid ",Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        Toast.makeText(LoginActivity.this,"Server Error ",Toast.LENGTH_SHORT).show();
                                        throw new RuntimeException(e);
                                    }


                                }

                                @Override
                                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                    super.onFailure(statusCode, headers, throwable, errorResponse);
                                    Toast.makeText(LoginActivity.this,"Server Error ",Toast.LENGTH_SHORT).show();

                                }*/

                            }
                    );
                }

                }




    });

}}