package com.example.collegeattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.HashMap;
import java.util.Map;

public class Batches extends AppCompatActivity {
   FirebaseFirestore firebaseFirestore;
    EditText batch;
    EditText name;
    Button addBatch;
    Spinner spinner;
    Map<String, String> batchMap = new HashMap<>();
    String [] array = new String[]{"3 Year Stream","5 Year Stream"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batches);
        firebaseFirestore = FirebaseFirestore.getInstance();
        batch = findViewById(R.id.batches);
        name = findViewById(R.id.name);
        addBatch = findViewById(R.id.batchAddBtn);
        spinner= findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = parent.getItemAtPosition(position).toString();
                Toast.makeText(Batches.this, spinnerValue, Toast.LENGTH_SHORT).show();
                batchMap.put("stream",spinnerValue);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Batches.this, "Please select Stream", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void AddBatch(View view) {
          String Batches = batch.getText().toString();
          String Name = name.getText().toString();

            batchMap.put("batch", Batches);
            batchMap.put("name", Name);


        firebaseFirestore.collection("BatchMaster").document(Batches).set(batchMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                       StyleableToast.makeText(Batches.this, "Batch Added Successfully",R.style.customSuccessToast).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                StyleableToast.makeText(Batches.this, "Unale To Add Batch",R.style.customFailureToast).show();
            }
        });

        Intent intent = new Intent(this,AdminMangement.class);
        startActivity(intent);
    }


    public void deleteBatch(View view) {
        Toast.makeText(this, "Delete Batch Not Avalaible It will Added ASAP", Toast.LENGTH_SHORT).show();
    }

    public void bactToManagement(View view) {
        Intent intent = new Intent(this,AdminMangement.class);
        startActivity(intent);
    }


}