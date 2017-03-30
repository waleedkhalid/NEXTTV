package com.example.waleed.firebasestartup;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class profileActivity extends AppCompatActivity implements TextView.OnClickListener {


    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        public TextView roomName;
        public TextView roomAddress;
        public TextView roomUrl;
        public ImageView ThumbUrl;


        public RoomViewHolder(View v) {
            super(v);
            roomName = (TextView) itemView.findViewById(R.id.name);
            roomAddress = (TextView) itemView.findViewById(R.id.address);
            roomUrl = (TextView) itemView.findViewById(R.id.Movieurl);
            ThumbUrl = (ImageView) itemView.findViewById(R.id.ThumbUrl);

        }
    }
    public static final String ROOMS = "Movies";
    private RecyclerView mRoomRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonLogOut;
    public Context context=this;
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<EscapeRoom, RoomViewHolder> mFirebaseAdapter;
    private ProgressDialog progressDialog;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mRoomRecyclerView = (RecyclerView)findViewById(R.id.roomRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        mLinearLayoutManager.setStackFromEnd(true);


        progressDialog= ProgressDialog.show(this, "Loading...","Please Wait",true);
        //Database Initialization
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<EscapeRoom, RoomViewHolder>(
                EscapeRoom.class,
                R.layout.escape_room,
                RoomViewHolder.class,
                mFirebaseDatabaseReference.child(ROOMS)) {
            @Override

            protected void populateViewHolder(RoomViewHolder viewHolder, EscapeRoom model, int position) {

//                viewHolder.roomName.setText(model.getTitle());
//                viewHolder.roomAddress.setText(model.getDirector());
//                viewHolder.roomUrl.setText(model.getMovieUrl());
                Picasso.with(context).load(model.getThumbUrl()).into(viewHolder.ThumbUrl);

            progressDialog.dismiss();
            }

        };
        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver(){
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount){
                super.onItemRangeInserted(positionStart, itemCount);
                int roomCount = mFirebaseAdapter.getItemCount();
                int lastVisiblePosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastVisiblePosition == -1 || (positionStart >= (roomCount -1) && lastVisiblePosition == (positionStart -1))){
                    mRoomRecyclerView.scrollToPosition(positionStart);
                }
            }
        });
        mRoomRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRoomRecyclerView.setAdapter(mFirebaseAdapter);



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


    }

//  Read from Database

    FirebaseDatabase database = FirebaseDatabase.getInstance();


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