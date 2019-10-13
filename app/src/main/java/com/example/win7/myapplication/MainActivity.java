package com.example.win7.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
private Button hirer;
private Button hiree;
private TextView tv1;
private Button loghirer;
private Button loghiree;
private TextView tv2;
//private FirebaseAuth firebaseAuth;
//private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hirer=(Button)findViewById(R.id.btnhirer);
        hiree=(Button)findViewById(R.id.btnhiree);
        tv1=(TextView)findViewById(R.id.tv1);
        loghirer=(Button)findViewById(R.id.hirerlg);
        loghiree=(Button)findViewById(R.id.hireelg);
        tv2=(TextView)findViewById(R.id.tv2);



        hirer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, hirerReg.class);
                startActivity(intent);
            }
        });

        hiree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, hireeReg.class);
                startActivity(intent);
            }
        });

        loghirer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, hirerLog.class);
                startActivity(intent);
            }
        });

        loghiree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, hireeLog.class);
                startActivity(intent);
            }
        });


    }
}
