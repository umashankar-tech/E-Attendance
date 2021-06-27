package com.example.collegeattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    EditText gmail ;
    EditText password1;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        firebaseAuth=FirebaseAuth.getInstance();
        gmail=(EditText) findViewById(R.id.emailId);
        password1 = (EditText)findViewById(R.id.loginpassword);
        signUp= (Button)findViewById(R.id.loginBtn);
    }

    public void goToHomePage(View view) {
        String mail = gmail.getText().toString();
        String password = password1.getText().toString();

        if(mail.isEmpty()){
            gmail.setError("Enter your mailID");
            gmail.requestFocus();
        }
        else if (password.isEmpty()){
            password1.setError("Enter Your Password");
            password1.requestFocus();
        }
        else if(mail.isEmpty()&&password.isEmpty()){
            gmail.setError("Enter your mailID");
            gmail.requestFocus();
            password1.setError("Enter Your Password");
            password1.requestFocus();
        }
        else if(!(mail.isEmpty()&&password.isEmpty())){
            firebaseAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(Login.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent = new Intent(Login.this,AdminMangement.class);
                        startActivity(intent);
                    }

                }
            });
        }

        else{

        }
    }

    public void goToRegisterPage(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}