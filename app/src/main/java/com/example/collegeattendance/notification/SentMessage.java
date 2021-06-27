package com.example.collegeattendance.notification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.collegeattendance.AdminMangement;
import com.example.collegeattendance.Batches;
import com.example.collegeattendance.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SentMessage extends AppCompatActivity {
    EditText msgBriefContent;
    String MsgTitle,MsgContent;
    Map<String, String> batchMap = new HashMap<>();
    FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sent_message);
        msgBriefContent = findViewById(R.id.msgContent);
        firebaseFirestore = FirebaseFirestore.getInstance();

    }

    public void bactToManagement(View view) {
        Intent intent = new Intent(SentMessage.this, AdminMangement.class);
        startActivity(intent);
    }

    public void sentmsg(View view) {

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        MsgContent = msgBriefContent.getText().toString();
        batchMap.put("msgContent",MsgContent);
        batchMap.put("date",currentDate);


        firebaseFirestore.collection("MESSAGES").add(batchMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        StyleableToast.makeText(SentMessage.this, "Message Sent Successfully",R.style.customSuccessToast).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                StyleableToast.makeText(SentMessage.this, "Try Again",R.style.customFailureToast).show();

            }
        });


        Intent intent = new Intent(this,AdminMangement.class);
        startActivity(intent);
    }
}