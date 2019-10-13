package com.example.win7.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Queue;

public class hirerQuerryPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView hirerName;
    private FirebaseAuth firebaseAuth;
    private RecyclerView hireeList;
    private ValueEventListener queryListener;
    ArrayList<cardValues> list;
    prgramAdapter adapter;
    String NAME;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirer_querry_page);
        hirerName = (TextView)findViewById(R.id.tvhirerName);
        Intent intent = getIntent();
        NAME = intent.getStringExtra("NAME");
        hirerName.setText(NAME);
        hireeList = (RecyclerView)findViewById(R.id.rvhireeList);
        hireeList.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<cardValues>();

        Spinner spinner = (Spinner)findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapt = ArrayAdapter.createFromResource(this,R.array.jobs,android.R.layout.simple_spinner_item);

        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapt);
        spinner.setOnItemSelectedListener(this);


        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://jobconnect-3324b.firebaseio.com");


              //databaseReference.addValueEventListener(new ValueEventListener()
        queryListener = new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    cardValues c = dataSnapshot1.getValue(cardValues.class);
                    list.add(c);
                }


                adapter = new prgramAdapter(hirerQuerryPage.this,list);
                hireeList.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(hirerQuerryPage.this,"Something went wrong!!",Toast.LENGTH_SHORT).show();

            }
        };
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = parent.getItemAtPosition(position).toString();
        Toast.makeText(hirerQuerryPage.this, selected, Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(hirerQuerryPage.this, filtered_result.class);
        //intent.putExtra("selected", selected);
        //startActivity(intent);

       if (!selected.equalsIgnoreCase("Select Job")) {
            Query query = FirebaseDatabase.getInstance().getReferenceFromUrl("https://jobconnect-3324b.firebaseio.com").orderByChild("job").equalTo(selected);
            query.addValueEventListener(queryListener);
        }
    }

        @Override
        public void onNothingSelected(AdapterView<?> parent){
        }
    }
