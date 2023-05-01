package com.example.onlineexamapp.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineexamapp.exam.MainActivity;
import com.example.onlineexamapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private static final int SIGN_IN_RESULT_CODE = 1;
    private FirebaseAuth mAuth;
    EditText emailEditText, passwordEditText;
    Button loginButton;
    TextView registerNow;
    Intent registerIntent;
    Intent mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.login_email_editText);
        passwordEditText = findViewById(R.id.login_password_editText);
        loginButton = findViewById(R.id.login_btn);
        registerNow = findViewById(R.id.register_now);
        registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
        mainActivity = new Intent(getApplicationContext(), MainActivity.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email , password;
                email = String.valueOf(emailEditText.getText());
                password = String.valueOf(passwordEditText.getText());
                boolean validData = checkEmailPasswordNotEmpty(email, password);
                if (validData){
                    loginUser(email, password);
                }
                else{
                    return;
                }

            }
        });

        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(registerIntent);
                finish();
            }
        });

    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(mainActivity);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean checkEmailPasswordNotEmpty(String email , String password){
        if(TextUtils.isEmpty(email)){
            Toast.makeText(LoginActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
            return  false;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}