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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.HashMap;
import java.util.Map;

public class TeacherCreation extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore1;
    FirebaseAuth firebaseAuth;
    EditText name;
    EditText dob;
    EditText qualification;
    EditText uername;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_creation);


        firebaseFirestore1 = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.teacherName);
        dob = findViewById(R.id.dob);
        qualification = findViewById(R.id.qualification);
        password = findViewById(R.id.password23);
        uername= findViewById(R.id.userName);
    }
    public void addTeacher(View view) {
        String Name = name.getText().toString();
        String DOB = dob.getText().toString();
        String Qualification = qualification.getText().toString();
        String UserName = uername.getText().toString();
        String passWord = password.getText().toString();

        Map<String,String> batchMap = new HashMap<>();
        batchMap.put("teachername",Name);
        batchMap.put("dob",DOB);
        batchMap.put("qualification",Qualification);
        batchMap.put("userName",UserName);
        batchMap.put("password",passWord);

        firebaseFirestore1.collection("teacher").document(Name).set(batchMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                StyleableToast.makeText(TeacherCreation.this, "Teacher Added Successfully",R.style.customSuccessToast).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                StyleableToast.makeText(TeacherCreation.this, "Somthing went Wrong",R.style.customFailureToast).show();

            }
        });
        firebaseAuth.createUserWithEmailAndPassword(UserName, passWord)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();


                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();

                        }
                    }
                });
        Intent intent = new Intent(this,AdminMangement.class);
        startActivity(intent);

    }
    public void bactToManagement(View view) {
        Intent intent = new Intent(this,AdminMangement.class);
        startActivity(intent);
    }


}