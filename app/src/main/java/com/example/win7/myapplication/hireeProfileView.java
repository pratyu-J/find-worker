package com.example.win7.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class hireeProfileView extends AppCompatActivity {
    private TextView name,city,email,phone,age,sex,dob,job;
    private Button update;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    String userName, userCity, userEmail, userPhone, userSex,userAge, userDob, userJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiree_profile_view);
        firebaseAuth = FirebaseAuth.getInstance();
        name = (TextView)findViewById(R.id.tvNAME);
        city = (TextView)findViewById(R.id.tvCITY);
        email = (TextView)findViewById(R.id.tvEMAIL);
        phone = (TextView)findViewById(R.id.tvPHONE);
        age = (TextView)findViewById(R.id.tvAGE);
        sex = (TextView)findViewById(R.id.tvSEX);
        dob = (TextView)findViewById(R.id.tvDOB);
        job = (TextView)findViewById(R.id.tvJOB);
        update = (Button)findViewById(R.id.updateButton);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userProfile user_profile = dataSnapshot.getValue(userProfile.class);
                userName = user_profile.getName();
                userCity = user_profile.getCity();
                userEmail =user_profile.getEmail();
                userPhone = user_profile.getPhone();
                userSex = user_profile.getSex();
                userAge = user_profile.getAge();
                userDob = user_profile.getDob();
                userJob = user_profile.getJob();


                name.setText(userName);
                city.setText("City : " + userCity);
                email.setText("Email : " + userEmail);
                phone.setText("Phone :" + userPhone);
                sex.setText("Sex : " + userSex);
                age.setText("Age :" + userAge);
                dob.setText("DOB : " + userDob);
                job.setText("Job/specialization : " + userJob);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(hireeProfileView.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(hireeProfileView.this,hireeReg .class));
            }
        });
    }

    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(hireeProfileView.this, MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutMenu: {
                Logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
