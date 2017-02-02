package com.example.waleed.firebasestartup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignUp;
    private TextView textView2;


    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null)
        {
//        profile activity will start automatically
            finish();
            startActivity(new Intent(getApplicationContext(),profileActivity.class));

        }
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignin);
        textViewSignUp = (TextView) findViewById(R.id.textViewSignUp);
        textView2 = (TextView) findViewById(R.id.textView2);

        progressDialog = new ProgressDialog(this);

        buttonSignIn.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);


        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/avenir_next_regular.ttf");
        editTextEmail.setTypeface(custom_font);
        editTextPassword.setTypeface(custom_font);
        textViewSignUp.setTypeface(custom_font);
        textView2.setTypeface(custom_font);
    }






    private void  userLogin() {

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {

//         email is empty
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();

//         stop the function from executing further
            return;
        }

        if (TextUtils.isEmpty(password)) {

//         password field is empty
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
            progressDialog.setMessage("Logging in Please Wait..");
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
//                              start the profile activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), profileActivity.class));
                            }

                        }
                    });

        }

    @Override
        public void onClick (View view) {


            if (view == buttonSignIn) {
                userLogin();

            }
            if (view == textViewSignUp) {


                finish();
                startActivity(new Intent(this, MainActivity.class));

            }
        }
    }
