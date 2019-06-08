package com.minozoy.magasid.meetme;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
                };

        recyclerView.setAdapter(firebaseAdapter);
    }
}
