package com.minozoy.magasid.meetme;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.ByteArrayOutputStream;


public class profiles extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;//for sort
    SharedPreferences mSharedPreferences;// for saving sort items
    FirebaseDatabase mFireBase;
    DatabaseReference reference;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    TextView textView;

    private static final int Request_Call =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);


            final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);


            Toolbar toolbar = findViewById(R.id.app_bar);
            drawerLayout = findViewById(R.id.drawer1);
            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
            toggle.syncState();
            drawerLayout.addDrawerListener(toggle);

            navigationView = findViewById(R.id.navigation);


            mSharedPreferences = getSharedPreferences("SortSettings", MODE_PRIVATE);

            // now by default new arrivals will be seen first
            String start = mSharedPreferences.getString("SortLadies", "newest");

            if (start.equals("newest")) {
                mLayoutManager = new LinearLayoutManager(this);
                //this means data will load from the bottom ie from the newest to the oldest
                mLayoutManager.setReverseLayout(true);
                mLayoutManager.setStackFromEnd(true);
            } else if (start.equals("oldest")) {
                mLayoutManager = new LinearLayoutManager(this);
                //this means data will load from the top ie from the oldest to the newest
                mLayoutManager.setReverseLayout(false);
                mLayoutManager.setStackFromEnd(false);
            }

            recyclerView = findViewById(R.id.recycle_view);
            recyclerView.setHasFixedSize(true);

            mFireBase = FirebaseDatabase.getInstance();
            reference = mFireBase.getReference("Data");

            //set layout as LinearLayout
            recyclerView.setLayoutManager(mLayoutManager);

            textView = findViewById(R.id.email);

    }

    private   void fire(String searchOne){
        Query searchquary = reference.orderByChild("Title").startAt(searchOne).endAt(searchOne + "\uf8ff");

        FirebaseRecyclerAdapter<model, viewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<model, viewHolder>(
                        model.class,
                        R.layout.row,
                        viewHolder.class,
                        searchquary

                ) {
                    @Override
                    protected void populateViewHolder(viewHolder viewHolder, model model, int position) {
                        viewHolder.Setdetails(getApplicationContext(),model.getTitle(),model.getPrice(),model.getImage());
                    }

                    @Override
                    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        viewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new viewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                //getData from FireBase Position
                                String mTitle = getItem(position).getTitle();
                                String mDesc = getItem(position).getPrice();
                                String Image = getItem(position).getImage();

                                //pass this data to new activity

                                Intent lintent = new Intent(view.getContext(),LadieDetails.class);
                                lintent.putExtra("Title",mTitle);// puts title
                                lintent.putExtra("Description",mDesc);//puts Description
                                lintent.putExtra("Image", Image);//puts Image Url

                                startActivity(lintent);

                            }

                            @Override
                            public void onLongCickListener(View view, int position) {

                                // this one is for saving Image to the Phone gallery

                            }
                        });

                        return viewHolder;
                    }


                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    //Load data into RecycleView on start

    @Override
    public void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<model, viewHolder> firebaseAdapter =
                new FirebaseRecyclerAdapter<model, viewHolder>(
                        model.class,
                        R.layout.row,
                        viewHolder.class,
                        reference
                ) {
                    @Override
                    protected void populateViewHolder(viewHolder viewHolder, model model, int position) {
                        viewHolder.Setdetails(getApplicationContext(),model.getTitle(),model.getPrice(),model.getImage());
                    }


                    @Override
                    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        viewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new viewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                //getData from FireBase Position
                                String mTitle = getItem(position).getTitle();
                                String mDesc = getItem(position).getPrice();
                                String Image = getItem(position).getImage();

                                //pass this data to new activity

                                Intent lintent = new Intent(view.getContext(),LadieDetails.class);
                                lintent.putExtra("Title",mTitle);// puts title
                                lintent.putExtra("Description",mDesc);//puts Description
                                lintent.putExtra("Image", Image);//puts Image Url

                                startActivity(lintent);

                            }

                            @Override
                            public void onLongCickListener(View view, int position) {

                                // this one is for saving Image to Phone gallery

                            }
                        });

                        return viewHolder;
                    }

                };

        recyclerView.setAdapter(firebaseAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate the menu, this adds items to action bar if it is present
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                fire(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String sew) {
                // filter as you type
                fire(sew);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    //menu item on the tool bar  method
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //handle other items on clicks

        if(id == R.id.sort){
            //display an alert Dialogue to choose from
            sortDialogue();
            return true;
        }else if(id == R.id.help){
           callPerson();
        }
        else if(id == R.id.rate){
            Intent intent = new Intent(this,RatingActivity.class);
            startActivity(intent);
            finish();
        }
        else if(id == R.id.share){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String ShareSubject = ("This is the begining");
            intent.putExtra(Intent.EXTRA_SUBJECT,ShareSubject);
            startActivity(Intent.createChooser(intent,"Share using"));
        }
        else if(id == R.id.setting){
            Intent intent = new Intent(this,Settings.class);
            startActivity(intent);
            finish();

        }
        else if(id == R.id.log_out){
            Toast.makeText(this,"You have clicked Log Out",Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.feedback){
            //startActivity(new Intent(profiles.this, Feedback.class));
            //finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void sortDialogue() {

        //options top select from in the dialogue
        String [] list = {"New Arrivals", "First Ladies"};

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Sort by;")// set Title
                .setIcon(R.drawable.ic_action_sort)
                .setItems(list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //the which item contains the position of the selected item
                        //0 means new arrivals 1 means first ladies

                        if (which == 0) {
                            //show new ladies/arrivals
                            //Edit our shared preferences

                            SharedPreferences.Editor editor = mSharedPreferences.edit();
                            editor.putString("Sort","newest");//where sort is the key and newest is the value
                            editor.apply();//apply means save the value in our shared preferences
                            recreate();//restart activity to take effect

                        }else if(which==1){
                            //show old ones
                            //Edit our shared preferences

                            SharedPreferences.Editor editor = mSharedPreferences.edit();
                            editor.putString("Sort","oldest");//where sort is the key and oldest is the value
                            editor.apply();//apply means save the value in our shared preferences
                            recreate();//restart activity to take effect
                        }
                    }
                });
        dialog.show();

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    private boolean connectivity(){

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();

    }


    private void callPerson() {
        String  nummber = "0703394151";

        if(ContextCompat.checkSelfPermission(profiles.this,
                Manifest.permission.CALL_PHONE )!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(profiles.this,new String[] {Manifest.permission.CALL_PHONE},Request_Call);

        }else {
            String dial = "tel:" +nummber;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == Request_Call){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                callPerson();
            }else {
                Toast.makeText(this,"PERMISSION DENIED",Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    protected void onRestart() {
        super.onRestart();


        if(!connectivity()){
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_stat_network)
                    .setTitle("Network Connection Alert")
                    .setMessage("Please Check your Internet Connection!!!")
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

        }


        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);


        Toolbar toolbar = findViewById(R.id.app_bar);
        drawerLayout = findViewById(R.id.drawer1);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);

        navigationView = findViewById(R.id.navigation);


        mSharedPreferences = getSharedPreferences("SortSettings", MODE_PRIVATE);

        // now by default new arrivals will be seen first
        String start = mSharedPreferences.getString("SortLadies", "newest");

        if (start.equals("newest")) {
            mLayoutManager = new LinearLayoutManager(this);
            //this means data will load from the bottom ie from the newest to the oldest
            mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);
        } else if (start.equals("oldest")) {
            mLayoutManager = new LinearLayoutManager(this);
            //this means data will load from the top ie from the oldest to the newest
            mLayoutManager.setReverseLayout(false);
            mLayoutManager.setStackFromEnd(false);
        }

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);

        mFireBase = FirebaseDatabase.getInstance();
        reference = mFireBase.getReference("Data");

        //set layout as LinearLayout
        recyclerView.setLayoutManager(mLayoutManager);



    }


    @Override
    protected void onResume() {
        super.onResume();


        if(!connectivity()){
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_stat_network)
                    .setTitle("Network Connection Alert")
                    .setMessage("Please Check your Internet Connection!!!")
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

        }


        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);


        Toolbar toolbar = findViewById(R.id.app_bar);
        drawerLayout = findViewById(R.id.drawer1);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);

        navigationView = findViewById(R.id.navigation);


        mSharedPreferences = getSharedPreferences("SortSettings", MODE_PRIVATE);

        // now by default new arrivals will be seen first
        String start = mSharedPreferences.getString("SortLadies", "newest");

        if (start.equals("newest")) {
            mLayoutManager = new LinearLayoutManager(this);
            //this means data will load from the bottom ie from the newest to the oldest
            mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);
        } else if (start.equals("oldest")) {
            mLayoutManager = new LinearLayoutManager(this);
            //this means data will load from the top ie from the oldest to the newest
            mLayoutManager.setReverseLayout(false);
            mLayoutManager.setStackFromEnd(false);
        }

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);

        mFireBase = FirebaseDatabase.getInstance();
        reference = mFireBase.getReference("Data");

        //set layout as LinearLayout
        recyclerView.setLayoutManager(mLayoutManager);



    }

    @Override
    protected void onPause() {
        super.onPause();


    }
}
