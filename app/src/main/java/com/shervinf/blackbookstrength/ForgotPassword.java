package com.shervinf.blackbookstrength;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    private EditText emailTv;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        emailTv = findViewById(R.id.restEmailTextView);
        mAuth = FirebaseAuth.getInstance();

        toolbarSetup();


    }





    //Setting up the toolbar for the activity to have a back button to finish current activity and send user to previous activity
    private void toolbarSetup(){
        Toolbar mToolbar = findViewById(R.id.forgotPasswordToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginActivity();
            }
        });
    }





    //This method will verify and email input by user and if valid it will call the sendPasswordResetEmail() method tp reset the users forgotten password.
    public void resetPassword(View view) {
        //Reset any errors cause by the user in the form
        emailTv.setError(null);

        String email = emailTv.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(email)) {
            emailTv.setError(getString(R.string.error_field_required));
            focusView = emailTv;
            cancel = true;
        }
        else if(!isEmailValid(email)) {
            emailTv.setError(getString(R.string.error_invalid_email));
            focusView = emailTv;
            cancel = true;
        }

        if(cancel) {
            //There was ana error do not attempt login and focus the first form field with an error
            focusView.requestFocus();
        }
        else {
            //Call the method that resets the firebase users password with the provided email
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgotPassword.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                goToLoginActivity();
                            } else {
                                Toast.makeText(ForgotPassword.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                goToLoginActivity();
                            }
                        }
                    });
        }
    }




    private void goToLoginActivity() {
        Intent loginIntent = new Intent(ForgotPassword.this,LoginActivity.class);
        finish();
        startActivity(loginIntent);
    }



    private boolean isEmailValid(String email) {
        return email.contains("@");
    }




}