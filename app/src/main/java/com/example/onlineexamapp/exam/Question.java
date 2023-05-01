package com.example.onlineexamapp.exam;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Question implements Parcelable {
    private String question , answer_a, answer_b, answer_c, answer_d, correctAnswer;

    public Question(String question, String answer_a, String answer_b, String answer_c, String answer_d, String correctAnswer) {
        this.question = question;
        this.answer_a = answer_a;
        this.answer_b = answer_b;
        this.answer_c = answer_c;
        this.answer_d = answer_d;
        this.correctAnswer = correctAnswer;
    }

    protected Question(Parcel in) {
        question = in.readString();
        answer_a = in.readString();
        answer_b = in.readString();
        answer_c = in.readString();
        answer_d = in.readString();
        correctAnswer = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public String getAnswer_a() {
        return answer_a;
    }

    public String getAnswer_b() {
        return answer_b;
    }

    public String getAnswer_c() {
        return answer_c;
    }

    public String getAnswer_d() {
        return answer_d;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer_a(String answer_a) {
        this.answer_a = answer_a;
    }

    public void setAnswer_b(String answer_b) {
        this.answer_b = answer_b;
    }

    public void setAnswer_c(String answer_c) {
        this.answer_c = answer_c;
    }

    public void setAnswer_d(String answer_d) {
        this.answer_d = answer_d;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(question);
        parcel.writeString(answer_a);
        parcel.writeString(answer_b);
        parcel.writeString(answer_c);
        parcel.writeString(answer_d);
        parcel.writeString(correctAnswer);
    }
}
