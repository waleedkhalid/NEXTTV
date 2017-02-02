package com.example.waleed.firebasestartup;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class profileActivity extends AppCompatActivity implements TextView.OnClickListener {

    private static final String URL_Data = "https://www.internetfaqs.net/superheroes.php";

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonLogOut;
    private ListView mlistview;

    private DatabaseReference databaseReference;
//    private EditText  editTextName, editTextAddress;
//    private Button buttonSave;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-startup-4cd98.firebaseio.com/Movies");

        listItems = new ArrayList<>();

        adapter = new MyAdapter(listItems, this);

        recyclerView.setAdapter(adapter);

        LoadRecyclerViewData();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);

        textViewUserEmail.setText("Welcome " + user.getEmail());
        buttonLogOut = (Button) findViewById(R.id.buttonLogOut);

        buttonLogOut.setOnClickListener(this);
//        buttonSave.setOnClickListener(this);

//        font changing method
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/avenir_next_regular.ttf");
        textViewUserEmail.setTypeface(custom_font);
//        editTextName.setTypeface(custom_font);
//        editTextAddress.setTypeface(custom_font);

    }

    private void LoadRecyclerViewData() {



        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null)

        {

            finish();
            startActivity(new Intent(this, LoginActivity.class));

        }




        database = FirebaseDatabase.getInstance();

//        getReference();

//        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
//        editTextName = (EditText) findViewById(R.id.editTextName);
//        buttonSave = (Button) findViewById(R.id.buttonSave);





    }
//    private void saveUserInformation()
//    {
//
////    String name = editTextName.getText().toString().trim();
//    String add = editTextAddress.getText().toString().trim();
//
//        UserInformation userInformation = new UserInformation(name, add);
//
//        FirebaseUser User = firebaseAuth.getCurrentUser();
//         databaseReference.child(User.getUid()).setValue(userInformation);
//
//        Toast.makeText(this, "Information saved", Toast.LENGTH_LONG).show();
//
//
//    }


//  Read from Database

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Movies");

//    myRef.addValueEventListner(new ValueEvnetListner);


    @Override
    public void onClick(View view) {

        if (view == buttonLogOut) {

            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));


        }

//            if(view == buttonSave){
//
//                saveUserInformation();
//
//            }
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("profile Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

}