package com.example.homework1exam.roomDatabase.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.homework1exam.roomDatabase.entitys.Results;
import com.example.homework1exam.roomDatabase.UserScore;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ResultsDao {

    @Insert
    void insertResult(Results result);

    @Update
    int updateAnswer(Results result);

    @Delete
    void deleteResults(Results result);

    // Are you Answerd
    @Query("SELECT COUNT(*) > 0 FROM Results WHERE userId = :userId AND questionId = :questionId")
    boolean isAnswerExists(int userId, int questionId);

    // جلب بيانات المستخدمين مع نتائجهم
    @Query("SELECT Users.userName, Users.email AS userEmail, COUNT(*) AS correctAnswers " +
            "FROM Results " +
            "INNER JOIN Users ON Results.userId = Users.user_id " +
            "WHERE Results.isCorrect = 'Correct' " +
            "GROUP BY Users.user_id")
    LiveData<List<UserScore>> getUserScores();

//     جلب اليوزر مع نتيجته من خلال الاي دي
    @Query("SELECT Users.userName, Users.email, COUNT(Results.resultId) AS correctAnswers " +
            "FROM Results " +
            "INNER JOIN Users ON Users.user_id = Results.userId " +
            "WHERE Results.isCorrect = 'Correct' AND Users.user_id = :userId " +
            "GROUP BY Users.user_id")
    UserScore getUserScoreById(int userId);






}

