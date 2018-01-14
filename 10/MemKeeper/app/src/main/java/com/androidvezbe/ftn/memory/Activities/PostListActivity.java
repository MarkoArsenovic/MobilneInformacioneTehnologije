package com.androidvezbe.ftn.memory.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.androidvezbe.ftn.memory.Data.MemoriesRecyclerAdapter;
import com.androidvezbe.ftn.memory.Model.Memory;
import com.androidvezbe.ftn.memory.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostListActivity extends AppCompatActivity {
    private DatabaseReference mDatabaseReference;
    private RecyclerView recyclerView;
    private MemoriesRecyclerAdapter memoriesRecyclerAdapter;
    private List<Memory> memoryList;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("MMemory");
        mDatabaseReference.keepSynced(true);


        memoryList = new ArrayList<>();


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:
                if (mUser != null && mAuth != null) {

                    startActivity(new Intent(PostListActivity.this, AddPostActivity.class));
                    finish();

                }

                break;
            case R.id.action_signout:

                if (mUser != null && mAuth != null) {
                    mAuth.signOut();

                    startActivity(new Intent(PostListActivity.this, MainActivity.class));
                    finish();

                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Memory memory = dataSnapshot.getValue(Memory.class);

                memoryList.add(memory);

                Collections.reverse(memoryList);

                memoriesRecyclerAdapter = new MemoriesRecyclerAdapter(PostListActivity.this, memoryList);
                recyclerView.setAdapter(memoriesRecyclerAdapter);
                memoriesRecyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
