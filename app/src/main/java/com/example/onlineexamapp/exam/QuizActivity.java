package com.example.onlineexamapp.exam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.onlineexamapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuizActivity extends AppCompatActivity {
    private TextView questionTxt, selectedSubject, doneText;
    private RadioButton aTxt, bTxt, cTxt, dTxt;
    private Button submitButton, nextButton;
    private ImageView image, tick;
    private RadioGroup radioGroup;
    private String checkedAnswer;
    private int score;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        List<Question> questionList = new ArrayList<Question>();
        String selectedSubjectName = getIntent().getStringExtra("selectedSubject");
        questionList = getIntent().getParcelableArrayListExtra("questions");
        questionTxt = findViewById(R.id.question);
        selectedSubject = findViewById(R.id.selectedSubject);
        aTxt = findViewById(R.id.answer_a);
        bTxt = findViewById(R.id.answer_b);
        cTxt = findViewById(R.id.answer_c);
        dTxt = findViewById(R.id.answer_d);
        image = findViewById(R.id.exam_images);
        tick = findViewById(R.id.image_tick);
        radioGroup = findViewById(R.id.radioGroup);
        submitButton = findViewById(R.id.submit_btn);
        nextButton = findViewById(R.id.next_btn);
        doneText = findViewById(R.id.done_txtview);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        Toast.makeText(QuizActivity.this, "Current user email"+ currentUser.getDisplayName(),
                Toast.LENGTH_LONG).show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");
        DatabaseReference userRef = usersRef.child(currentUser.getDisplayName());
        final int[] currentQuestion = {0};

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton checked = findViewById(i);
                checkedAnswer = checked.getText().toString();
                Toast.makeText(QuizActivity.this, "Selected : "+ checked.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        List<Question> finalQuestionList = questionList;
                    questionTxt.setText(questionList.get(0).getQuestion());
                    aTxt.setText(questionList.get(0).getAnswer_a());
                    bTxt.setText(questionList.get(0).getAnswer_b());
                    cTxt.setText(questionList.get(0).getAnswer_c());
                    dTxt.setText(questionList.get(0).getAnswer_d());
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.equals(checkedAnswer, finalQuestionList.get(currentQuestion[0]).getCorrectAnswer())){
                    score ++;
                    tick.setImageResource(R.drawable.ticktick);
                    tick.setVisibility(View.VISIBLE);
                }
                else{
                    tick.setImageResource(R.drawable.wrong);
                    tick.setVisibility(View.VISIBLE);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tick.setVisibility(View.GONE);
                currentQuestion[0] = currentQuestion[0] + 1;
                int i = currentQuestion[0];
                if (i < finalQuestionList.size()){
                    questionTxt.setText(finalQuestionList.get(i).getQuestion());
                    aTxt.setText(finalQuestionList.get(i).getAnswer_a());
                    bTxt.setText(finalQuestionList.get(i).getAnswer_b());
                    cTxt.setText(finalQuestionList.get(i).getAnswer_c());
                    dTxt.setText(finalQuestionList.get(i).getAnswer_d());
                }
                else{
                    Toast.makeText(QuizActivity.this, "Finished Exam, Score: "+ score +"\nCongratulations",
                            Toast.LENGTH_LONG).show();
                    String text = "Finished Exam \nScore: "+ score +"/" + finalQuestionList.size();
                    doneText.setText(text);
                    doneText.setVisibility(View.VISIBLE);
                    userRef.child(selectedSubjectName+"Score").setValue(score);
                }
            }
        });


        switch (selectedSubjectName){
            case "maths":{
                selectedSubject.setText("Maths Exam");
                image.setImageResource(R.drawable.maths);
                return;

            }
            case "science":{
                selectedSubject.setText("Science Exam");
                image.setImageResource(R.drawable.science);
                return;
            }
            case "android":{
                selectedSubject.setText("Android Exam");
                image.setImageResource(R.drawable.android);
                return;
            }
            case "java":{
                selectedSubject.setText("Java Exam");
                image.setImageResource(R.drawable.java);
                return;
            }
            default:{

            }
        }
    }

    String TAG = "MyQuizActivity";
}

