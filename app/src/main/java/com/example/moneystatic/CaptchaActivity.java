package com.example.moneystatic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

public class CaptchaActivity extends AppCompatActivity {
//initialize var
    CheckBox checkBox;
    GoogleApiClient googleApiClient;
    //Put sitekey
    String SiteKey="6LdzfxMpAAAAALAcgXLqBv3q_ec62o7iV-DTj3HR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captcha);

        //Asssign vari
        checkBox=findViewById(R.id.check_box);
        googleApiClient=new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) CaptchaActivity.this)
                .build();
        googleApiClient.connect();
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked())
                {
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient,SiteKey)
                            .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                                @Override
                                public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                    Status status=recaptchaTokenResult.getStatus();
                                    if(status!=null&&status.isSuccess())
                                    {
                                        Toast.makeText(CaptchaActivity.this, "Captcha Sucessfully varified!!!!!", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(CaptchaActivity.this, "Not!! Suc!!!!!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    SafetyNet.getClient(CaptchaActivity.this)
                            .verifyWithRecaptcha(SiteKey)
                            .addOnSuccessListener(CaptchaActivity.this, result -> {
                                // Process the success result
                                Toast.makeText(CaptchaActivity.this, "Captcha Successfully verified!!!!!", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(CaptchaActivity.this, e -> {
                                // Process the failure result
                                String errorMessage = "Captcha verification failed!";
                                if (e.getMessage() != null) {
                                    errorMessage += " Error: " + e.getMessage();
                                }
                                Toast.makeText(CaptchaActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                                // Print or log the error for further debugging
                                e.printStackTrace();
                            });
                }
            }
        });

    }
}