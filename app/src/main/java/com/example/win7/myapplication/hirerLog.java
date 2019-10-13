package com.example.win7.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class hirerLog extends AppCompatActivity {
    private EditText email, pass, name;
    private Button logbutton;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirer_log);
        getVar();
        firebaseAuth  = FirebaseAuth.getInstance();
        logbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String EMAIL = email.getText().toString().trim();
                    String PASSWORD = pass.getText().toString().trim();
                    final String NAME =name.getText().toString().trim();

                    firebaseAuth.signInWithEmailAndPassword(EMAIL, PASSWORD).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(hirerLog.this,"Log in successfull",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(hirerLog.this,hirerQuerryPage.class);
                                i.putExtra("NAME",NAME);
                                startActivity(i);
                                //startActivity(new Intent(hirerLog.this,hirerQuerryPage.class ));
                            }
                            else{
                                Toast.makeText(hirerLog.this,"Log in failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }

    public void getVar(){
        email=(EditText)findViewById(R.id.email3);
        pass=(EditText)findViewById(R.id.pass3);
        name=(EditText)findViewById(R.id.name3);
        logbutton=(Button)findViewById(R.id.logButton_hirer);
    }

    public Boolean validate(){
        Boolean result = false;
        String user_email = email.getText().toString();
        String user_password = pass.getText().toString();
        String user_name = name.getText().toString();

        if(user_email.isEmpty() || user_password.isEmpty() || user_name.isEmpty()){
            Toast.makeText(hirerLog.this,"Enter all details",Toast.LENGTH_SHORT).show();
        }
        else{
            result=true;
        }
        return result;
    }

}

