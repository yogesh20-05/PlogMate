package com.example.megamart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.ProtectionDomain;

import cz.msebera.android.httpclient.Header;

public class RegistrationActivity extends AppCompatActivity {


    EditText etRegName,etRegPhno,etRegEmail,etRegusername,etRegPass;
    CheckBox cbRegShowHidePAss;
    Button btnRegRegister;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("PlogMate");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);


        etRegName=findViewById(R.id.etRegisterName);
        etRegPhno=findViewById(R.id.etRegisterphno);
        etRegEmail=findViewById(R.id.etRegisterEmailid);
        etRegusername=findViewById(R.id.etRegisterrUsername);
        etRegPass=findViewById(R.id.etRegisterPassword);
        cbRegShowHidePAss=findViewById(R.id.cbRegisterShowpassword);
        btnRegRegister=findViewById(R.id.btnRegisterregister);




        cbRegShowHidePAss.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    etRegPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    etRegPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });



        btnRegRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etRegName.getText().toString().isEmpty()) {
                    etRegName.setError("Enter your name");
                } else if (etRegPhno.getText().toString().isEmpty()) {
                    etRegPhno.setError("Enter the phone number");
                } else if (etRegPhno.getText().toString().length() != 10) {
                    etRegPhno.setError("Enter a valid phone number");
                } else if (etRegEmail.getText().toString().isEmpty()) {
                    etRegEmail.setError("Enter the Email ID");
                } else if (!etRegEmail.getText().toString().contains("@")) {
                    etRegEmail.setError("Enter the Valid Email ID");
                } else if (!etRegEmail.getText().toString().contains("gmail.com")) {
                    etRegEmail.setError("Enter the Valid Email ID");
                } else if (etRegusername.getText().toString().isEmpty()) {
                    etRegusername.setError("Please enter your Username");
                } else if (etRegusername.getText().toString().length() < 8) {
                    etRegusername.setError("Username must be greater that 8 characters");
                } else if (!etRegusername.getText().toString().matches(".*[A-Z].*")) {
                    etRegusername.setError("Username must be contain a Uppercase letter");
                } else if (!etRegusername.getText().toString().matches(".*[a-z].*")) {
                    etRegusername.setError("Username must be contain a Lowercase letter ");
                } else if (!etRegusername.getText().toString().matches(".*[0-9].*")) {
                    etRegusername.setError("User must be contain 1 number ");
                } else if (!(etRegusername.getText().toString().matches(".*[@,#,$,%,&,_].*"))) {
                    etRegusername.setError("User must be contain a Special symbol");

                } else if (etRegPass.getText().toString().isEmpty()) {
                    etRegPass.setError("Password can not be empty");

                } else if (etRegPass.getText().toString().length() < 8) {
                    etRegPass.setError("Password must be greater than 8 characters");

                } else {
                    AsyncHttpClient client = new AsyncHttpClient();
                    RequestParams params = new RequestParams();
                    params.put("name", etRegName.getText().toString());
                    params.put("phno", etRegPhno.getText().toString());
                    params.put("email", etRegEmail.getText().toString());
                    params.put("username", etRegusername.getText().toString());
                    params.put("password", etRegusername.getText().toString());

                    client.post("http://192.168.70.127:80/bookinfo/registerdata.php", params, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);

                            Toast.makeText(RegistrationActivity.this, "Registration successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });

                    Toast.makeText(RegistrationActivity.this, "Registration successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }





        });


    }
}