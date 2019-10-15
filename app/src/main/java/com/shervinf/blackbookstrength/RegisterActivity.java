package com.shervinf.blackbookstrength;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    //Member Variables
    private AutoCompleteTextView actvEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etName;
    private EditText etDeadliftMax;
    private EditText etSquatMax;
    private EditText etBenchMax;
    private EditText etOHPMax;
    private EditText etWeightGoal;
    private EditText etCalorieGoal;

    //Firebase instance variables
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTheme(android.R.style.ThemeOverlay_Material_Dark);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.registerToolbar);

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
                finish();
                startActivity(loginIntent);
            }
        });


        actvEmail = findViewById(R.id.actvEmail);
        etPassword =findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etName  = findViewById(R.id.etUserName);
        etDeadliftMax = findViewById(R.id.etDeadliftMax);
        etSquatMax = findViewById(R.id.etSquatMax);
        etBenchMax = findViewById(R.id.etBenchMax);
        etOHPMax = findViewById(R.id.etOHPMax);
        etWeightGoal = findViewById(R.id.etWeightGoal);
        etCalorieGoal = findViewById(R.id.etCalorieGoal);


        //Keyboard Sign In action
        etConfirmPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == R.integer.register_form_finished || actionId == EditorInfo.IME_NULL) {
                    attemptRegistration();
                    return true;
                }

                return false;
            }
        });
        //get hold of an instance of FirebaseAuth
        fAuth = FirebaseAuth.getInstance();
    }
    public void register(View v)
    {
        attemptRegistration();
    }


    private void attemptRegistration()
    {
        //Reset any errors cause by the user in the form
        actvEmail.setError(null);
        etPassword.setError(null);

        String email = actvEmail.getText().toString();
        String password = etPassword.getText().toString();
        boolean cancel = false;
        View focusView = null;

        if(TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            etPassword.setError(getString(R.string.error_invalid_password));
            focusView = etPassword;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            actvEmail.setError(getString(R.string.error_field_required));
            focusView = actvEmail;
            cancel = true;
        }
        else if(!isEmailValid(email)) {
            actvEmail.setError(getString(R.string.error_invalid_email));
            focusView = actvEmail;
            cancel = true;
        }

        if(cancel) {
            //There was ana error do not attempt login and focus the first form field with an error
            focusView.requestFocus();
        }
        else {
            //Call the method that creates the firebase user
            createFirebaseUser();
        }
    }


    private boolean isEmailValid(String email) {
        // You can add more checking logic here.
        return email.contains("@");
    }


    private boolean isPasswordValid(String password) {
        //TODO: Add own logic to check for a valid password (minimum 6 characters)
        String confirmPassword = etConfirmPassword.getText().toString();
        return confirmPassword.equals(password) && password.length() > 6;
    }

    private void createFirebaseUser()
    {
        String email = actvEmail.getText().toString();
        String password = etConfirmPassword.getText().toString();

        fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("BlackBookStrength", "CreateUser on Complete: " + task.isSuccessful());

                if(!task.isSuccessful())
                {
                    Log.d("BlackBookStrength", "User creation has failed");
                    showErrorDialog("Registration attempt failed");
                }
                else {
                    createdFirebaseUser();
                    finish();
                    startActivity(new Intent(getApplicationContext(), com.shervinf.blackbookstrength.MainActivity.class));
                }
            }
        });
    }

    //Create an alert dialog to show in case registration failed
    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Oops")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void createdFirebaseUser(){
        final Double deadliftMax = Double.parseDouble(etDeadliftMax.getText().toString());
        final Double squatMax = Double.parseDouble(etSquatMax.getText().toString());
        final Double benchMax = Double.parseDouble(etBenchMax.getText().toString());
        final Double OHPMax = Double.parseDouble(etOHPMax.getText().toString());
        final Integer weightGoal = Integer.parseInt(etWeightGoal.getText().toString());
        final Integer calorieGoal = Integer.parseInt(etCalorieGoal.getText().toString());

        Log.d("BlackBookStrength","User Created");
        Toast.makeText(getApplicationContext(),R.string.success_register,Toast.LENGTH_SHORT).show();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference newUserDocumentRef = db.collection("users").document(userID);
        UserPOJO newUser = new UserPOJO();
        newUser.setDeadliftMax(deadliftMax);
        newUser.setSquatMax(squatMax);
        newUser.setBenchMax(benchMax);
        newUser.setOHPMax(OHPMax);
        newUser.setUserID(userID);
        newUser.setWeightGoal(weightGoal);
        newUser.setCalorieGoal(calorieGoal);
        newUserDocumentRef.set(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Log.d("BlackBookStrength","createdFirebaseUser Success");
                }
                else{
                    Log.d("BlackBookStrength","createdFirebaseUser Failed");
                }
            }
        });
    }
}
