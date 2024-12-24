package com.example.homework1exam.roomDatabase.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.homework1exam.roomDatabase.entitys.Questions;

import java.util.ArrayList;
import java.util.List;


@Dao
public interface QuestionsDao {

    @Insert
    void insertQuestions(ArrayList<Questions> questions);

    @Insert
    void insertAllQuestions(ArrayList<Questions> questions);

    // 30 سؤال عشوائي
    @Query("SELECT * FROM Questions ORDER BY RANDOM() LIMIT 30")
    LiveData<List<Questions>> getQuestionsRandomly();
}
