package com.example.waleed.firebasestartup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;
    private TextView textView2;
    private EditText editTextConfirmPassword;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;



    private ProgressDialog ProgressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();




//        *** De-Comment below line to enable toggle button ***
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        firebaseAuth = FirebaseAuth.getInstance();
        ProgressDialog = new ProgressDialog(this);

    buttonRegister = (Button) findViewById(R.id.buttonRegister);
    editTextEmail = (EditText) findViewById(R.id.editTextEmail);
    editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);


//        font changing method %start---------------------->
    textViewSignin = (TextView) findViewById(R.id.textViewSignin);
    Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/avenir_next_regular.ttf");
    textViewSignin.setTypeface(custom_font);


        textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setTypeface(custom_font);
        textView2.setTypeface(null, Typeface.BOLD);


        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextEmail.setTypeface(custom_font);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextEmail.setTypeface(custom_font);

        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPassword.setTypeface(custom_font);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonRegister.setTypeface(custom_font);
        buttonRegister.setTypeface(null, Typeface.BOLD);


//      Font changing method %End--------------------->
        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);

    }

 private void registerUser() {

    String email = editTextEmail.getText().toString().trim();
    String password = editTextPassword.getText().toString().trim();
    String cnfrmPassword = editTextConfirmPassword.getText().toString().trim();

     if (TextUtils.isEmpty(email)){

//         email is empty
         Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();

//         stop the function from executing further
         return;
     }

     if (TextUtils.isEmpty(password)){

//         password field is empty
         Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
         return;


     }
     if (TextUtils.isEmpty(cnfrmPassword)){

         Toast.makeText(this, "password didn't match", Toast.LENGTH_SHORT).show();
         return;
     }

     if (TextUtils.equals(password,cnfrmPassword)){

//         Toast.makeText(this, "password match perfectly", Toast.LENGTH_SHORT).show();
//         return;


         ProgressDialog.setMessage("Registering User");
         ProgressDialog.show();
         firebaseAuth.createUserWithEmailAndPassword(email, password)
                 .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()) {

//                        user is successfully registered and logged in
//                        we will start the profile activity here
//                        right now lets display a toast only

                             finish();
                             startActivity(new Intent(getApplicationContext(), profileActivity.class));

                         } else

                         {
                             Toast.makeText(MainActivity.this, "Failed to Register, Please Try Again", Toast.LENGTH_SHORT).show();

                         }
                         ProgressDialog.dismiss();
                     }
                 });

     }
    else{

         Toast.makeText(this, "Password didn't match perfectly",Toast.LENGTH_SHORT).show();
         return;

     }
 }




 @Override

 public boolean onOptionsItemSelected(MenuItem item){

     if(mToggle.onOptionsItemSelected(item)){
         return true;


     }
    return super.onOptionsItemSelected(item);

 }
 public void onClick(View view){
    if(view == buttonRegister)
    {
        registerUser();


    }
    if (view == textViewSignin){


//        will open the login activity here

    startActivity(new Intent(this, LoginActivity.class));

    }
 }

}


