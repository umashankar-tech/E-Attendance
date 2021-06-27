package com.example.collegeattendance.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.collegeattendance.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewMessage extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<MsgModel> datalist;
    MsgAdapter msgAdapter;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_message);
        recyclerView = findViewById(R.id.messageRecycler);
        datalist = new ArrayList<>();
        msgAdapter = new MsgAdapter(  this,datalist);

        recyclerView.setAdapter(msgAdapter);

        firebaseFirestore  = FirebaseFirestore.getInstance();
        Query query= firebaseFirestore.collection("MESSAGES");
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot d:list)
                {
                   MsgModel model = d.toObject(MsgModel.class);
                    datalist.add(model);
                }
                msgAdapter.notifyDataSetChanged();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
       // recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(linearLayoutManager);
    }
}