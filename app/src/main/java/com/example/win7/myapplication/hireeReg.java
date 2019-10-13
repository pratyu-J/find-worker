package com.example.win7.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class hireeReg extends AppCompatActivity {

    private EditText fullName, city, email, pass, phone;
    private Button next;
    private RadioGroup radgroup;
    private RadioButton radbutton;
    private FirebaseAuth firebaseAuth;
    String name,userCity,userEmail,userPhone,password,sex,age,dob,job;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiree_reg2);

        vardef();
        firebaseAuth= FirebaseAuth.getInstance();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String full_name = fullName.getText().toString().trim();
                    String user_email = email.getText().toString().trim();
                    String user_password = pass.getText().toString().trim();
                    String user_city = city.getText().toString().trim();
                    String phone_no = phone.getText().toString().trim();


                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                sendUserData();
                                /*Intent i = new Intent(hireeReg.this,hireeRegPage2.class);
                                i.putExtra("name",name);
                                i.putExtra("City",userCity);
                                i.putExtra("Email",userEmail);
                                i.putExtra("Phone",userPhone);
                                i.putExtra("sex",sex);
                                startActivity(i);*/
                                Toast.makeText(hireeReg.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(hireeReg.this,hireeRegPage2.class));
                            }
                            else{
                                Toast.makeText(hireeReg.this,"Registration failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void vardef(){
        fullName=(EditText)findViewById(R.id.fname);
        city=(EditText)findViewById(R.id.city2);
        email=(EditText)findViewById(R.id.email2);
        pass=(EditText)findViewById(R.id.pass2);
        phone=(EditText)findViewById(R.id.phone2);
        next=(Button)findViewById(R.id.nxtbutton);
        radgroup=findViewById(R.id.rg1);

    }
    public void checkButton(View view){
        int radioid = radgroup.getCheckedRadioButtonId();
        radbutton=findViewById(radioid);
        Toast.makeText(hireeReg.this,"Selected :" + radbutton.getText(), Toast.LENGTH_SHORT).show();
    }

    private Boolean validate(){
        Boolean result = false;
        name = fullName.getText().toString();
        userCity = city.getText().toString();
        userEmail = email.getText().toString();
        password = pass.getText().toString();
        userPhone = phone.getText().toString();
        sex = ((RadioButton)findViewById(radgroup.getCheckedRadioButtonId())).getText().toString();
        age = null;
        dob = null;
        job = null;



        if(name.isEmpty() || password.isEmpty() || userCity.isEmpty() || userEmail.isEmpty())
        {
            Toast.makeText(hireeReg.this,"please enter all the details",Toast.LENGTH_SHORT).show();
        }
        else{
            result = true;
        }
        return result;
    }

   private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myref = firebaseDatabase.getReference(firebaseAuth.getUid());
        userProfile user_profile1 = new userProfile(name,userCity,userEmail,userPhone,sex,age,dob,job);
        myref.setValue(user_profile1);
    }



}
