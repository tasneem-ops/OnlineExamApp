package com.example.onlineexamapp.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.onlineexamapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText emailEditText, passwordEditText, nameEditText;
    Button registerButton;
    TextView loginNow;
    private FirebaseAuth mAuth;
    Intent loginIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailEditText = findViewById(R.id.email_editText);
        passwordEditText = findViewById(R.id.password_editText);
        nameEditText = findViewById(R.id.name_editText);
        registerButton = findViewById(R.id.register_btn);
        loginNow = findViewById(R.id.login_now);
        mAuth = FirebaseAuth.getInstance();

       loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email , password;
                email = String.valueOf(emailEditText.getText());
                password = String.valueOf(passwordEditText.getText());
                boolean validData = checkEmailPasswordNotEmpty(email, password);
                if (validData){
                    registerUser(email, password);
                }
                else{
                    return;
                }
            }
        });

        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(loginIntent);
                finish();
            }
        });
    }

    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            String name = nameEditText.getText().toString();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name).build();
                            user.updateProfile(profileUpdates);
                            startActivity(loginIntent);
                            finish();
                           // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                           // updateUI(null);
                        }
                    }
                });
    }

    private boolean checkEmailPasswordNotEmpty(String email , String password){
        if(TextUtils.isEmpty(email)){
            Toast.makeText(RegisterActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
            return  false;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(RegisterActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}