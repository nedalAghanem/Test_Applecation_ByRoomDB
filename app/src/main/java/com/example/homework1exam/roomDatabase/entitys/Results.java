package com.example.homework1exam.roomDatabase.entitys;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Users.class,parentColumns = {"user_id"}
        ,childColumns = {"userId"},onUpdate = ForeignKey.CASCADE,onDelete = ForeignKey.CASCADE),
                      @ForeignKey(entity = Questions.class,parentColumns = {"question_id"}
       ,childColumns = {"questionId"},onUpdate = ForeignKey.CASCADE,onDelete = ForeignKey.CASCADE)}
        ,tableName = "Results")
public class Results {

    @PrimaryKey(autoGenerate = true)
    private int resultId;
    private int userId;
    private int questionId;
    private String userAnswer;
    private String isCorrect;

    public Results() {
    }

    public Results(int resultId, int userId, int questionId, String userAnswer, String isCorrect) {
        this.resultId = resultId;
        this.userId = userId;
        this.questionId = questionId;
        this.userAnswer = userAnswer;
        this.isCorrect = isCorrect;
    }


    public Results(int userId, int questionId, String userAnswer, String isCorrect) {
        this.userId = userId;
        this.questionId = questionId;
        this.userAnswer = userAnswer;
        this.isCorrect = isCorrect;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer( String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect( String isCorrect) {
        this.isCorrect = isCorrect;
    }
}
