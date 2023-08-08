package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    EditText etlastname,etfirstname,etemail,etpass;
    Button register;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fstore;
    Spinner country;
    RadioGroup radio;
    RadioButton gendermale,genderfemale,any;
    CheckBox matric,inter,bach;
    String uid,anyid,first;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


//// Initialize Firebase Auth
//        mAuth = FirebaseAuth.getInstance();
        etfirstname = findViewById(R.id.etfirstname);
        etlastname = findViewById(R.id.etlastname);
        etemail = findViewById(R.id.etemail);
        etpass = findViewById(R.id.etpass);
        country = findViewById(R.id.country);
        radio = findViewById(R.id.radio);
        gendermale = findViewById(R.id.gendermale);
        genderfemale = findViewById(R.id.genderfemale);

        matric = findViewById(R.id.matric);
        inter = findViewById(R.id.inter);
        bach = findViewById(R.id.bach);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Country,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(adapter);


//        etcpass = findViewById(R.id.etcpass);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioid = radio.getCheckedRadioButtonId();
                any = findViewById(radioid);
                anyid = any.getText().toString();

                final String email = etemail.getText().toString();
                final String password = etpass.getText().toString();
//                final String confirmpassword = etcpass.getText().toString();
                first = etfirstname.getText().toString();
                final String last = etlastname.getText().toString();
                int radiold = radio.getCheckedRadioButtonId();
                any = findViewById(radiold);

                String select = ((Spinner) findViewById(R.id.country)).getSelectedItem().toString();
                firebaseAuth = FirebaseAuth.getInstance();
                fstore = FirebaseFirestore.getInstance();


                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {


                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {

                            uid = firebaseAuth.getCurrentUser().getUid();


                            DocumentReference documentReference = fstore.collection("user").document(uid);

                            Map<String, Object> user = new HashMap<>();
                            user.put("name", first);
                            user.put("Lastname", last);
                            user.put("Pass", password);
                            user.put("Country", select);
                            user.put("Gender", anyid);
                            user.put("email", email);

                            documentReference.set(user).addOnSuccessListener(Signup.this, new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Signup.this, "Successfully register", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(Signup.this, "denied", Toast.LENGTH_SHORT).show();

                        }

                    }
                });


            }
        });

    }

}