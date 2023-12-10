package com.example.moneystatic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button HomePageButton,signInButton;
    EditText emailEditText, passwordEditText;
    CheckBox checkBox;
    GoogleApiClient googleApiClient;
    String SiteKey="6LdGBxQpAAAAAEZrzEkPznA7xtZL1TLYMg3X9Y_Q";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//
        checkBox=findViewById(R.id.check_box);
        HomePageButton = findViewById(R.id.btnLogin);
        signInButton =findViewById(R.id.btn_sign_in_page);
        emailEditText = findViewById(R.id.emailEditT);
        passwordEditText = findViewById(R.id.passwordlEditT);
//Captch
//        checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (checkBox.isChecked()) {
//                    SafetyNet.getClient(MainActivity.this)
//                            .verifyWithRecaptcha(SiteKey)
//                            .addOnSuccessListener(MainActivity.this, result -> {
//                                // Process the success result
//                                Toast.makeText(MainActivity.this, "Captcha Successfully verified!!!!!", Toast.LENGTH_SHORT).show();
//                            })
//                            .addOnFailureListener(MainActivity.this, e -> {
//                                // Process the failure result
//                                String errorMessage = "Captcha verification failed!";
//                                if (e.getMessage() != null) {
//                                    errorMessage += " Error: " + e.getMessage();
//                                }
//                                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
//
//                                // Print or log the error for further debugging
//                                e.printStackTrace();
//                            });
//                }
//            }
//        });

        // Assume checkBox is the reference to your checkbox
//        checkBox.setEnabled(false); // Disable the checkbox initially



        checkBox.setOnClickListener(v -> {
            if (checkBox.isChecked()==true) {
                checkBox.setChecked(false);
                SafetyNet.getClient(MainActivity.this)
                        .verifyWithRecaptcha(SiteKey)
                        .addOnSuccessListener(result -> {
                            // Process the success result
                            Toast.makeText(MainActivity.this, "Captcha Successfully verified!!!!!", Toast.LENGTH_SHORT).show();

                            // Enable the checkbox after successful verification
                            checkBox.setChecked(true);
                            checkBox.setEnabled(true);
                        })
                        .addOnFailureListener(e -> {
                            // Process the failure result
                            String errorMessage = "Captcha verification failed!";
                         
                            if (e.getMessage() != null) {
                                errorMessage += " Error: " + e.getMessage();
                            }
                            Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                            // Print or log the error for further debugging
                            e.printStackTrace();

                            // Uncheck the checkbox if verification fails

                            checkBox.setChecked(false);
                        });

            }
    
        });




        ////////
        HomePageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the entered email and password
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                // Verify credentials against the database
                DBHelperUsers dbHelper = new DBHelperUsers(MainActivity.this);
                boolean isValidCredentials = dbHelper.checkCredentials(email, password);
                // Replace this with your actual login logic
                if (email.isEmpty() || password.isEmpty()) {
                    showToast("Invalid email or password");
                }
                else if(!isValidCredentials){
                    showToast("Not ValidCredentials !!!");
                }else  if(!checkBox.isChecked())
                {
                    // Inside your activity or fragment
                    showToast(" Captcha not verified!!!");
                }
                else {
                    // Assuming you are in LoginActivity and want to navigate to HomePageActivity
//                    Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                    Intent intent = new Intent(MainActivity.this, Drawer_nav_Activity.class);
//                    Intent intent = new Intent(MainActivity.this, CaptchaActivity.class);
                    String valueToPassEmail = email; // Replace this with the actual data you want to pass

                    intent.putExtra("UserID", valueToPassEmail);
                    startActivity(intent);
                    // Close the current activity
                    finish();
                }
            }
        });
        //
        // Adding OnClickListener for the "Forgot Password" button
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click on the "Forgot Password" button
                // For example, you might navigate to a ForgotPasswordActivity
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    ///
    public void Users( ) {
//        Cursor res = DB.getData();
//        if (res.getCount() == 0) {
//            Toast.makeText(MainActivity.this, "No Entry Exist", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Create ArrayList to store data
//        ArrayList<String> dataList = new ArrayList<>();
//        while (res.moveToNext()) {
//            String entry = "Id: " + res.getString(0) + "\n" +
//                    "Name: " + res.getString(1) + "\n" +
//                    "Email: " + res.getString(2);
//            dataList.add(entry);
//        }


    }


}