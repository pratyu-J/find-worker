package com.example.win7.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class hirerReg extends AppCompatActivity {
    private EditText organisationName, city, phone, pass, email;
    private Button reg;
    private TextView HR;
    private FirebaseAuth firebaseAuth;
    String name,userCity,userEmail,password;
    
            


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirer_reg2);
        setupUIViews();
        firebaseAuth  = FirebaseAuth.getInstance();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String org_name = organisationName.getText().toString().trim();
                    String user_email = email.getText().toString().trim();
                    String user_password = pass.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           Toast.makeText(hirerReg.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                           Intent i =new Intent(hirerReg.this,hirerQuerryPage.class);
                           i.putExtra("NAME",name);
                           startActivity(i);
                           //startActivity(new Intent(hirerReg.this, MainActivity.class));
                       }
                       else{
                           Toast.makeText(hirerReg.this,"Registration failed",Toast.LENGTH_SHORT).show();
                       }
                        }
                    });

                };

            }
        });
    }

    private void setupUIViews() {
        HR=(TextView)findViewById(R.id.HR);
        organisationName=(EditText)findViewById(R.id.orgname);
        city=(EditText)findViewById(R.id.city1);
        phone=(EditText)findViewById(R.id.phone1);
        pass=(EditText)findViewById(R.id.pass1);
        email=(EditText)findViewById(R.id.email1);
        reg=(Button)findViewById(R.id.hirerReg);
    }

    private Boolean validate(){
        Boolean result = false;
         name = organisationName.getText().toString();
         userCity = city.getText().toString();
         userEmail = email.getText().toString();
         password = pass.getText().toString();

         if(name.isEmpty() || password.isEmpty() || userCity.isEmpty() || userEmail.isEmpty())
         {
             Toast.makeText(hirerReg.this,"please enter all the details",Toast.LENGTH_SHORT).show();
         }
         else{
             result = true;
         }
         return result;
    }
}

