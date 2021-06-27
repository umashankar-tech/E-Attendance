package com.example.collegeattendance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListBatchToTeacher extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<BatchModel> datalist;
    ArrayList<String> streamValue;

    BactheAdapter bactheAdapter;
    String currentStream;

    FirebaseFirestore firebaseFirestore;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_batch_list);
        recyclerView = findViewById(R.id.newBatchrecyclerview);

        streamValue = new ArrayList<>();
        datalist = new ArrayList<>();
        bactheAdapter = new BactheAdapter(this,streamValue, datalist);


        Intent intent = getIntent();
        currentStream = intent.getStringExtra("stream");

        firebaseFirestore = FirebaseFirestore.getInstance();
        Query query = firebaseFirestore.collection("BatchMaster").whereEqualTo("stream", currentStream);
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d : list) {
                    BatchModel model = d.toObject(BatchModel.class);
                    datalist.add(model);
                }
                streamValue.add(currentStream);
                bactheAdapter.notifyDataSetChanged();

            }
        });
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(bactheAdapter);

    }


/*    public void goToStream(View view) {
        Toast.makeText(this, currentStream, Toast.LENGTH_SHORT).show();

        switch (currentStream){
            case "3 Year Stream":{

                Intent intent = new Intent(this, ThreeYearStream.class);
                intent.putExtra("stream", currentStream);
                startActivity(intent);
            }
            break;
            case "5 Year Stream":{

                Intent intent = new Intent(this, FiveYearStream.class);
                intent.putExtra("stream", currentStream);
                startActivity(intent);
            }
            break;
            default:{
                Toast.makeText(this, "Stream Not Selected", Toast.LENGTH_SHORT).show();
            }

        }
    }*/
}