package com.example.win7.myapplication;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;

public class hireeLog extends AppCompatActivity {
    private EditText email, pass;
    private Button logbutton;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiree_log);
        getVar();
        firebaseAuth  = FirebaseAuth.getInstance();

        progressdialog = new ProgressDialog(this);

        logbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String EMAIL = email.getText().toString().trim();
                    String PASSWORD = pass.getText().toString().trim();
                    progressdialog.setMessage("Loading");
                    progressdialog.show();

                    firebaseAuth.signInWithEmailAndPassword(EMAIL, PASSWORD).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressdialog.dismiss();
                                Toast.makeText(hireeLog.this,"Log in successfull",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(hireeLog.this,hireeProfileView.class ));
                            }
                            else{
                                progressdialog.dismiss();
                                Toast.makeText(hireeLog.this,"Log in failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }

    public void getVar(){
        email=(EditText)findViewById(R.id.email4);
        pass=(EditText)findViewById(R.id.pass4);
        logbutton=(Button)findViewById(R.id.logButton_hiree);
    }

    public Boolean validate(){
        Boolean result = false;
        String user_email = email.getText().toString();
        String user_password = pass.getText().toString();

        if(user_email.isEmpty() || user_password.isEmpty()){
            Toast.makeText(hireeLog.this,"Enter all details",Toast.LENGTH_SHORT).show();
        }
        else{
            result=true;
        }
        return result;
    }

}
