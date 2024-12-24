package com.example.homework1exam.roomDatabase.entitys;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Questions {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "question_id")
    private int questionId;
    @NonNull
    private String questionText;
    @NonNull
    private String optionA;
    @NonNull
    private String optionB;
    @NonNull
    private String optionC;
    @NonNull
    private String optionD;
    @NonNull
    private String correctOption;

    public Questions() {
    }

    public Questions(int questionId, @NonNull String questionText, @NonNull String optionA, @NonNull String optionB, @NonNull String optionC, @NonNull String optionD, @NonNull String correctOption) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
    }

    public Questions(@NonNull String questionText, @NonNull String optionA, @NonNull String optionB, @NonNull String optionC, @NonNull String optionD, @NonNull String correctOption) {
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @NonNull
    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(@NonNull String questionText) {
        this.questionText = questionText;
    }

    @NonNull
    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(@NonNull String optionA) {
        this.optionA = optionA;
    }

    @NonNull
    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(@NonNull String optionB) {
        this.optionB = optionB;
    }

    @NonNull
    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(@NonNull String optionC) {
        this.optionC = optionC;
    }

    @NonNull
    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(@NonNull String optionD) {
        this.optionD = optionD;
    }

    @NonNull
    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(@NonNull String correctOption) {
        this.correctOption = correctOption;
    }
}
