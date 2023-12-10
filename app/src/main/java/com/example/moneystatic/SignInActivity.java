package com.example.moneystatic;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SignInActivity extends AppCompatActivity {
    Button GoTOLogInPageButton,signInButton;
    EditText nameTXT,emailEditText, passwordEditText,confirmPasswordEditText;
    DBHelperUsers DB;
    int randomNumber,randomNumber2;
    String Captch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        GoTOLogInPageButton = findViewById(R.id.btnCreateAccount);
        signInButton = findViewById(R.id.btn_Log_in_page);
//        GoToTestPage= findViewById(R.id.refreshBtn);
//        TextView textViewCaptchDisplya= findViewById(R.id.CaptchDisplay);
//         CaptchDisplya = findViewById(R.id.CaptchEdit);

        nameTXT = findViewById(R.id.fullName);
        emailEditText = findViewById(R.id.emailEditT_signin);
        passwordEditText = findViewById(R.id.passwordlEditT_signin);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordlEditT_signin);
        DB = new DBHelperUsers(this);  // Initialize your DBHelp
        // Adding OnClickListener for the "Forgot Password" button



        //
        // Create an instance of the Random class


        // Generate a random 6-digit number

        ///
        ////
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the entered email and password
                String name = nameTXT.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                /////////////////////////////////////////////////////////////////////////////
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);
                // Close the current activity
                finish();
                ////////////////////////////
                // Check if any of the EditText fields is empty
                if (name.isEmpty()||  email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
//                    showToast("Please fill in all fields!!");
                    showToast("Pressed!!");
                } else {
                    // Check if password and confirm password match
                    if (!password.equals(confirmPassword)) {
                        showToast("Password and Confirm Password do not match!");
                    } else {
                        // Your logic for creating an account
                        // ...

                        // Close the current activity
                        finish();
                        showToast("Account Created Successfully!!");
                    }
                }

            }
        });

        //insert user

        GoTOLogInPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameTXT.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();
                // Get the text from the EditText


// Get the text from the TextView
//                String displayedCaptcha = textViewCaptchDisplya.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    showToast("Invalid email or password ");
                } else {
                    // Check if password and confirm password match
                    if (!password.equals(confirmPassword)) {
                        showToast("Password and Confirm Password do not match!");
                    }
//                    else
//                        if (!userEnteredCaptcha.equals(Integer.toString(randomNumber)))
//                    {
//                        showToast("Captch incorrect!!");
//                    }
                        else {
                        // Your logic for creating an account
                        // ...
                        Boolean checkinsertdata=DB.insertuserdata(name,email,password);

                        if(checkinsertdata==true)
                        {
                            showToast("Account Created Successfully!!");
                        }

                        // Close the current activity
                        finish();

                    }



                }
            }
        });


    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed() {
        // Add your custom logic here
        // For example, show a toast message
        Toast.makeText(this, "Back button pressed!", Toast.LENGTH_SHORT).show();
// Start the second activity
        //        Intent intent = new Intent(AddExpensesActivity.this, HomePageActivity.class);
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);

        startActivity(intent);
        startActivityForResult(intent, 1);
        // If you want to keep the default behavior (going back in the navigation stack), call super.onBackPressed()
        super.onBackPressed();
    }
}