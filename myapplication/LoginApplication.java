package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginApplication extends AppCompatActivity {
    private SignInButton signInButton;
    private GoogleSignInClient mGoogleSignInClient;
    private String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    //private Button btnSignOut;
    private int RC_SIGN_IN = 1;
    //private Button
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_application);
        signInButton = findViewById(R.id.signin);
        signInButton.setSize(SignInButton.SIZE_ICON_ONLY);
        Button bane = (Button) findViewById(R.id.button2);
        final Button loginbtn = (Button) findViewById(R.id.button);
        final EditText email = findViewById(R.id.textView);
        final EditText psw = findViewById(R.id.editTextTextPassword);
        mAuth = FirebaseAuth.getInstance();
        final TextView pswForgot = (TextView) findViewById(R.id.forgotPassw);



        loginbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String memail=email.getText().toString().trim();
                String mpsw= psw.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(memail,mpsw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginApplication.this,"success",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), home.class));
                            finish();
                        } else {
                            Toast.makeText(LoginApplication.this,"failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        pswForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText resetm = new EditText(view.getContext());
                final AlertDialog.Builder resetpdialog = new AlertDialog.Builder(view.getContext());
                resetpdialog.setTitle("RESET PASSWORD?");
                resetpdialog.setMessage("enter your email");
                resetpdialog.setView(resetm);

                resetpdialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String takeemail = resetm.getText().toString();
                        mAuth.sendPasswordResetEmail(takeemail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(LoginApplication.this, "reset link has been sent", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginApplication.this, "not sent" + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                resetpdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                    }
                });

                resetpdialog.create().show();

            }
        });

        bane.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegistration();
            }
        });



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();

            }
        });

    }

    private void openRegistration() {
        Intent i = new Intent(this,RegistrationMain.class);
        startActivity(i);

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        Intent a=new Intent(this,home.class);
        startActivity(a);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {

            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
            Toast.makeText(LoginApplication.this, "Signed In Successfully", Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);
        } catch (ApiException e) {
            Toast.makeText(LoginApplication.this, "Sign In Failed", Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);
        }
    }


    private void FirebaseGoogleAuth(GoogleSignInAccount acct) {
        //check if the account is null
        if (acct != null) {
            AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
            mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginApplication.this, "Successful", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Toast.makeText(LoginApplication.this, "Failed", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                }
            });
        } else {
            Toast.makeText(LoginApplication.this, "acc failed", Toast.LENGTH_SHORT).show();
        }
    }


    private void updateUI(FirebaseUser fUser) {
        //btnSignOut.setVisibility(View.VISIBLE);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account != null) {
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();
            Intent i=new Intent(this,home.class);
            i.putExtra("name", personName);
            Toast.makeText(LoginApplication.this, personName + personEmail+personGivenName+personFamilyName+personId+personPhoto, Toast.LENGTH_SHORT).show();
        }

    }

}





