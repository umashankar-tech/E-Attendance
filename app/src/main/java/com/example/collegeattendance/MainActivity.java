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

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText gmail ;
    EditText password1;

    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        gmail=(EditText) findViewById(R.id.email1);
        password1 = (EditText)findViewById(R.id.password2);
        signUp= (Button)findViewById(R.id.signUpBtn);
    }

    public void goToAdminManagement(View view) {
        String mail =   gmail.getText().toString();
        String pasword = password1.getText().toString();
        if(mail.isEmpty()){
            gmail.setError("Enter your mailID");
            gmail.requestFocus();
        }
        else if (pasword.isEmpty()){
            password1.setError("Enter Your Password");
            password1.requestFocus();
        }


         else if(!(mail.isEmpty()&&pasword.isEmpty())){
              firebaseAuth.createUserWithEmailAndPassword(mail, pasword)
                      .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                              if (task.isSuccessful()) {
                                  Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();


                                  Intent intent = new Intent(MainActivity.this, AdminMangement.class);
                                  startActivity(intent);
                              }
                              else {
                                  Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();

                              }
                          }
                      });

        }
        else{
            Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show();
        }

    }

    public void goToSignInPage(View view) {
    }
}