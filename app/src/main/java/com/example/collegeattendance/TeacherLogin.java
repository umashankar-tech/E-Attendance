

package com.example.collegeattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.collegeattendance.studentpart.StudentPartLogin;
import com.example.collegeattendance.studentpart.StudentSubjectListToGetAttPer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.List;

public class TeacherLogin extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText gmail ;
    EditText password1;
    Button signUp;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_login);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        gmail=(EditText) findViewById(R.id.teachemailId);
        password1 = (EditText)findViewById(R.id.teachloginpassword);
        signUp= (Button)findViewById(R.id.teachloginBtn);
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
            Toast.makeText(this, "Fields are being Empty", Toast.LENGTH_SHORT).show();
        }
        else if(!(mail.isEmpty()&&password.isEmpty())){
            firebaseAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        new AlertDialog.Builder(TeacherLogin.this)
                                .setTitle("Unable To login")
                                .setMessage("Please Check internet Connection  (OR)  \n Enter Valid UserID")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        Toast.makeText(TeacherLogin.this, "Try Again", Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
                        StyleableToast.makeText(TeacherLogin.this, "Please Check LOGIN credetianls\nOR\nInternet Connection",R.style.customFailureToast).show();
                    }
                    else{
                        firebaseFirestore  = FirebaseFirestore.getInstance();
                        Query query= firebaseFirestore.collection("teacher").whereEqualTo("userName",mail).whereEqualTo("password",password);

                        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d:list)
                                {
                                    Intent intent = new Intent(TeacherLogin.this,PageToTeacherLogin.class);
                                    startActivity(intent);
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                new AlertDialog.Builder(TeacherLogin.this)
                                        .setTitle("User Does not exists")
                                        .setMessage("Enter Valid UserID \n     (or)\n Contact Admin for do Registration")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which)
                                            {
                                                Toast.makeText(TeacherLogin.this, "Try Again", Toast.LENGTH_SHORT).show();
                                            }
                                        }).show();
                            }
                        });


                    }

                }
            });
        }

        else{

        }
    }

}