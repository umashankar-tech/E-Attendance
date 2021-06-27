package com.example.collegeattendance.studentpart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeattendance.PageToTeacherLogin;
import com.example.collegeattendance.R;
import com.example.collegeattendance.StudentModel;
import com.example.collegeattendance.TeacherLogin;
import com.example.collegeattendance.WelcomeScreen;
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

public class StudentPartLogin extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    EditText gmail ;
    EditText password1;
    TextView txtgmail,txtpassword;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_part_login);

        firebaseAuth=FirebaseAuth.getInstance();
        gmail=(EditText) findViewById(R.id.studentloginMail);
        password1 = (EditText)findViewById(R.id.studentLoginPassword);
        txtgmail = findViewById(R.id.mailtextview);
        txtpassword= findViewById(R.id.passwordtextview);
        signUp= (Button)findViewById(R.id.studentLoginButton);
    }

    public void goToStuAttSubjectList(View view) {
        String mail = gmail.getText().toString().trim();
        String password = password1.getText().toString().trim();

          if(mail.isEmpty()&&password.isEmpty()){
            gmail.setError("Enter your mailID");
            gmail.requestFocus();
            password1.setError("Enter Your Password");
            password1.requestFocus();
            Toast.makeText(this, "Fields are being Empty", Toast.LENGTH_SHORT).show();
        }
        else if(mail.isEmpty()){
            gmail.setError("Enter your mailID");
            gmail.requestFocus();
        }
        else if (password.isEmpty()){
            password1.setError("Enter Your Password");
            password1.requestFocus();
        }

        else if(!(mail.isEmpty()&&password.isEmpty())){
            if(Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                firebaseAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            new AlertDialog.Builder(StudentPartLogin.this)
                                    .setTitle("Unable To login")
                                    .setMessage("Please Check internet Connection  (OR)  \n Enter Valid UserID")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which)
                                        {
                                            Toast.makeText(StudentPartLogin.this, "Try Again", Toast.LENGTH_SHORT).show();
                                        }
                                    }).show();
                            //StyleableToast.makeText(StudentPartLogin.this, "Check Your Internet Conection \nor\n",R.style.customFailureToast).show();
                        }
                        else{

                            firebaseFirestore  = FirebaseFirestore.getInstance();
                            Query query= firebaseFirestore.collection("student").whereEqualTo("email",mail).whereEqualTo("password",password);

                            query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d:list)
                                    {
                                        String rollNo = d.getString("rollno");
                                        String stream = d.getString("stream");
                                        String year   = d.getString("year");
                                        Intent intent = new Intent(StudentPartLogin.this,StudentSubjectListToGetAttPer.class);
                                        intent.putExtra("rollno",rollNo);
                                        intent.putExtra("stream",stream);
                                        intent.putExtra("year",year);
                                        startActivity(intent);
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    new AlertDialog.Builder(StudentPartLogin.this)
                                            .setTitle("Unable To login")
                                            .setMessage("Please Check internet Connection  (OR)  \n Enter Valid UserID")
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener()
                                            {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which)
                                                {
                                                    Toast.makeText(StudentPartLogin.this, "Try Again", Toast.LENGTH_SHORT).show();
                                                }
                                            }).show();
                                }
                            });

                        }

                    }
                });
            }
            else{
                txtgmail.setTextColor(Color.RED);
                txtgmail.setText("Enter Valid Email address");
            }

        }

        else{

        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, WelcomeScreen.class);
        startActivity(intent);
    }



}