package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegistrationMain extends AppCompatActivity {

    private static final String TAG = "blah";

    //private FirebaseAuth.AuthStateListener fireAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_main);
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final EditText name=(EditText) findViewById(R.id.textView3);
        final EditText email=(EditText) findViewById(R.id.textView5);
        final EditText psw=(EditText) findViewById(R.id.textView4);
        final EditText phone=(EditText) findViewById(R.id.textView6);
        final Button btnback=(Button) findViewById(R.id.button2);
        
        name.setText(name.getText().toString());

        email.setText(name.getText().toString());

        psw.setText(name.getText().toString());

        phone.setText(name.getText().toString());

                //need firebase authentication instance

         btnback.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View view) {
                 if(firebaseAuth.getCurrentUser()!=null){
                     startActivity(new Intent(getApplicationContext(), LoginApplication.class));
                     Toast.makeText(RegistrationMain.this,"user already exist",Toast.LENGTH_LONG).show();
                     finish();
                 }

                 String memail=email.getText().toString().trim();
                 String mpsw= psw.getText().toString().trim();
                 firebaseAuth.createUserWithEmailAndPassword(memail, mpsw)
                         .addOnCompleteListener(RegistrationMain.this, new OnCompleteListener<AuthResult>() {
                             @Override
                             public void onComplete(@NonNull Task<AuthResult> task){

                                 if (!task.isSuccessful()) {
                                     Toast.makeText(RegistrationMain.this,"not created"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                 } else {

                                     Toast.makeText(RegistrationMain.this,"success",Toast.LENGTH_LONG).show();
                                     startActivity(new Intent(getApplicationContext(), LoginApplication.class));
                                     finish();
                                 }
                             }
                         });

                 //backtologin();

             }

         });

    }



//    private void backtologin() {
//        Intent i2=new Intent(this,LoginApplication.class);
//        startActivity(i2);
//    }
}