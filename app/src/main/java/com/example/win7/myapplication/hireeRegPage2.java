package com.example.win7.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class hireeRegPage2 extends AppCompatActivity {
    private EditText JOB,AGE,EOE;
    private Button registerbtn;
    private TextView HR;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressdialog;
    String UID, job, age, eoe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiree_reg_page2);
        firebaseAuth = FirebaseAuth.getInstance();
        progressdialog = new ProgressDialog(this);


        JOB=(EditText)findViewById(R.id.etjob);
        AGE=(EditText)findViewById(R.id.etage);
        EOE=(EditText)findViewById(R.id.eteoe);
        registerbtn=(Button)findViewById(R.id.registerhiree);
      //  tv5=(TextView)findViewById(R.id.textView5);

       /* Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String city = bundle.getString("City");
        String email = bundle.getString("Email");
        String phone = bundle.getString("Phone");
        String sex = bundle.getString("sex");*/
        FirebaseUser user = firebaseAuth.getCurrentUser();
        UID = user.getUid();

      //  tv5.setText(name);


        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String job = JOB.getText().toString().trim();
                    String age = AGE.getText().toString().trim();
                    String dob = EOE.getText().toString().trim();
                    //sendUserData();
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


                    final DatabaseReference myref = firebaseDatabase.getInstance().getReferenceFromUrl("https://jobconnect-3324b.firebaseio.com");
                    DatabaseReference user1 = myref.child(UID);
                    user1.child("job").setValue(job);
                    user1.child("age").setValue(age);
                    user1.child("dob").setValue(dob);

                    Toast.makeText(hireeRegPage2.this,"data uploaded",Toast.LENGTH_SHORT).show();
                    //progressdialog.setMessage("Loading");
                    //progressdialog.show();
                    startActivity(new Intent(hireeRegPage2.this,hireeProfileView.class));
                }

                else{
                    Toast.makeText(hireeRegPage2.this,"upload failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

   /* private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference myref = firebaseDatabase.getInstance().getReference(firebaseAuth.getUid());
        userProfile user_profile = new userProfile(name,city,email,phone,sex,job, age, eoe, desc);
        myref.setValue(user_profile);
    }*/

    private Boolean validate(){
        Boolean result = false;
        job = JOB.getText().toString();
        age = AGE.getText().toString();
        eoe = EOE.getText().toString();

        if(job.isEmpty() || age.isEmpty() || eoe.isEmpty())
        {
            Toast.makeText(hireeRegPage2.this,"please enter all the details",Toast.LENGTH_SHORT).show();
        }
        else{
            result = true;
        }
        return result;
    }
}
