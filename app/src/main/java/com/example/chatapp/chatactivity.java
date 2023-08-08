package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.net.vcn.VcnUnderlyingNetworkTemplate;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chatapp.adapter.chatadapter;
import com.example.chatapp.models.chatmodel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class chatactivity extends AppCompatActivity {

    Button backbutton,send;
    CircleImageView profileview;
TextView profilename,profilelastseen;
RecyclerView recyclerView;
EditText etmessage;
FirebaseAuth mauth;
String senderid,reciverid,name;


chatadapter adapter;
FirebaseDatabase database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);









// getSupportActionBar().hide();
//        andrid = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
//        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        setContentView(R.layout.activity_chatactivity);
        send=findViewById(R.id.send);
        profilename=findViewById(R.id.profilename);
        recyclerView = findViewById(R.id.recyclerView);
        etmessage=findViewById(R.id.etmessage);


        //create a instance
        database=FirebaseDatabase.getInstance();
mauth=FirebaseAuth.getInstance();
//get id sender or reciver
        senderid = mauth.getUid();
         reciverid=getIntent().getStringExtra("userid");
         name=getIntent().getStringExtra("name");
        profilename.setText(name);

         final ArrayList<chatmodel> model1 = new ArrayList<>();
         chatadapter chatadapter=new chatadapter(model1, this,reciverid);
        recyclerView.setAdapter(chatadapter);

LinearLayoutManager manager=new LinearLayoutManager(this);
recyclerView.setLayoutManager(manager);

final String senderroom=senderid + reciverid;
final String reciverroom=reciverid + senderid;

        database.getReference()
                .child("chats")
                .child(senderroom)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        model1.clear();
                        for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                            chatmodel model = dataSnapshot1.getValue(chatmodel.class);
                            model.setMessageid(dataSnapshot1.getKey());
                            model1.add(model);
                        }
                        chatadapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
send.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String  message =etmessage.getText().toString();
        final chatmodel model=new chatmodel(message,senderid);
        model.setTimestamp(new Date().getTime());

        etmessage.setText("");
        database.getReference().child("chats")
                .child(senderroom)
                .push()
                .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        database.getReference().child("chats")
                                .child(reciverroom)
                                .push()
                                .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                    }
                                });
                    }
                });


    }
});

    }
}
