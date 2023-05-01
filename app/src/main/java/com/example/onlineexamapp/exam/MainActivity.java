package com.example.onlineexamapp.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.onlineexamapp.R;
import com.example.onlineexamapp.authentication.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LinearLayout maths, science, android , java;
    private ArrayList<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        maths = findViewById(R.id.maths_quiz);
        science = findViewById(R.id.science_quiz);
        android = findViewById(R.id.android_quiz);
        java = findViewById(R.id.java_quiz);
        questionList = new ArrayList<Question>();


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quizIntent = new Intent(getApplicationContext(), QuizActivity.class);
                DatabaseReference myRef = database.getReference("maths");
                ValueEventListener postListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                String question = snapshot.child("question").getValue(String.class);
                                String a = snapshot.child("a").getValue(String.class);
                                String b = snapshot.child("b").getValue(String.class);
                                String c = snapshot.child("c").getValue(String.class);
                                String d = snapshot.child("d").getValue(String.class);
                                String answer = snapshot.child("answer").getValue(String.class);

                                Question mQuestion = new Question(question, a, b, c, d, answer);
                                questionList.add(mQuestion);
                            }
                            quizIntent.putExtra("selectedSubject", "maths");
                            quizIntent.putParcelableArrayListExtra("questions", (ArrayList<? extends Parcelable>) questionList);
                            startActivity(quizIntent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                    }
                };
                myRef.addValueEventListener(postListener);
            }
        });
        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quizIntent = new Intent(getApplicationContext(), QuizActivity.class);
                DatabaseReference myRef = database.getReference("science");
                ValueEventListener postListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                String question = snapshot.child("question").getValue(String.class);
                                String a = snapshot.child("a").getValue(String.class);
                                String b = snapshot.child("b").getValue(String.class);
                                String c = snapshot.child("c").getValue(String.class);
                                String d = snapshot.child("d").getValue(String.class);
                                String answer = snapshot.child("answer").getValue(String.class);

                                Question mQuestion = new Question(question, a, b, c, d, answer);
                                questionList.add(mQuestion);
                            }
                            quizIntent.putExtra("selectedSubject", "science");
                            quizIntent.putParcelableArrayListExtra("questions", (ArrayList<? extends Parcelable>) questionList);
                            startActivity(quizIntent);
                        }
                        Toast.makeText(MainActivity.this, "dataSnapshot.getValue(String.class)", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                    }
                };
                myRef.addValueEventListener(postListener);
            }
        });
        android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quizIntent = new Intent(getApplicationContext(), QuizActivity.class);
                DatabaseReference myRef = database.getReference("android");
                ValueEventListener postListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                String question = snapshot.child("question").getValue(String.class);
                                String a = snapshot.child("a").getValue(String.class);
                                String b = snapshot.child("b").getValue(String.class);
                                String c = snapshot.child("c").getValue(String.class);
                                String d = snapshot.child("d").getValue(String.class);
                                String answer = snapshot.child("answer").getValue(String.class);

                                Question mQuestion = new Question(question, a, b, c, d, answer);
                                questionList.add(mQuestion);
                            }
                            quizIntent.putExtra("selectedSubject", "android");
                            quizIntent.putParcelableArrayListExtra("questions", (ArrayList<? extends Parcelable>) questionList);
                            startActivity(quizIntent);
                        }
                        Toast.makeText(MainActivity.this, "dataSnapshot.getValue(String.class)", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                    }
                };
                myRef.addValueEventListener(postListener);
            }
        });
        java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quizIntent = new Intent(getApplicationContext(), QuizActivity.class);
                DatabaseReference myRef = database.getReference("java");
                ValueEventListener postListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                String question = snapshot.child("question").getValue(String.class);
                                String a = snapshot.child("a").getValue(String.class);
                                String b = snapshot.child("b").getValue(String.class);
                                String c = snapshot.child("c").getValue(String.class);
                                String d = snapshot.child("d").getValue(String.class);
                                String answer = snapshot.child("answer").getValue(String.class);

                                Question mQuestion = new Question(question, a, b, c, d, answer);
                                questionList.add(mQuestion);
                            }
                            quizIntent.putExtra("selectedSubject", "java");
                            quizIntent.putParcelableArrayListExtra("questions", (ArrayList<? extends Parcelable>) questionList);
                            startActivity(quizIntent);
                        }
                        Toast.makeText(MainActivity.this, "dataSnapshot.getValue(String.class)", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                    }
                };
                myRef.addValueEventListener(postListener);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                logout();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    @Override
    protected void onResume() {
        questionList = new ArrayList<Question>();
        super.onResume();
    }

    String TAG = "Firebase_Test";
}