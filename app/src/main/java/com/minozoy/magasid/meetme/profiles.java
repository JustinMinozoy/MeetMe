package com.minozoy.magasid.meetme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.ByteArrayOutputStream;

public class profiles extends AppCompatActivity {

    RecyclerView recyclerView;

    FirebaseDatabase mFireBase;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Available Ladies Lists...");

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFireBase = FirebaseDatabase.getInstance();
        reference = mFireBase.getReference("Data");

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

                                // this one is for saving Image to the Phone gallery

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //handle other items on clicks
        return super.onOptionsItemSelected(item);
    }
}
