package com.example.homework1exam.roomDatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import com.example.homework1exam.roomDatabase.daos.QuestionsDao;
import com.example.homework1exam.roomDatabase.daos.ResultsDao;
import com.example.homework1exam.roomDatabase.daos.UsersDao;
import com.example.homework1exam.roomDatabase.entitys.Questions;
import com.example.homework1exam.roomDatabase.entitys.Results;
import com.example.homework1exam.roomDatabase.entitys.Users;

import java.util.ArrayList;
import java.util.List;


public class Repository {
    UsersDao usersDao;
    QuestionsDao questionsDao;
    ResultsDao resultsDao;

    public Repository(Application application) {
        ExamRoomDatabase db = ExamRoomDatabase.getDatabase(application);
        usersDao = db.usersDao();
        questionsDao = db.questionsDao();
        resultsDao = db.resultsDao();

    }

    // User Dao Methods

    // Add Anew User
    public void insertUser(Users user) {
        ExamRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                usersDao.insertUser(user); // لتنفيذ الكود في الوركر ثريد
            }
        });
    }
    // Delete User
    public void deleteUsers(Users user) {
        ExamRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                usersDao.deleteUsers(user);
            }
        });
    }
    // Show All Users
    public LiveData<List<Users>> getAllUsers() {
        return usersDao.getAllUsers();
    }
    // Check a user
    public Users loginUser(String email, String password) {
        return usersDao.loginUser(email, password);
    }
    // # of Emails in DB
    public int isEmailExists(String email) {
        return usersDao.isEmailExists(email);
    }
    // Delete User By Email
    public void deleteUsersByEmail(String email) {
        usersDao.deleteUsersByEmail(email);
    }
    // Return User Id By UserName
    public int returnUserId(String email){
        return usersDao.returnUserId(email);
    }
    public Users returnUserByEmail(String email){
        return usersDao.returnUserByEmail(email);
    }
    //////////////////////////////////////////////////////////
    // Question Dao Methods

    // Add All of a Question in DB
    public void insertAllQuestions(ArrayList<Questions> questions) {
        ExamRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                questionsDao.insertAllQuestions(questions); // لتنفيذ الكود في الوركر ثريد
            }
        });
    }
    // Show 30 Question as Randomly
    public LiveData<List<Questions>> getQuestionsRandomly() {
        return questionsDao.getQuestionsRandomly();
    }
    //////////////////////////////////////////////////////////
    //Result Dao Methods

    // Add a new Result
    public void insertResult(Results result) {
        ExamRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                resultsDao.insertResult(result);
            }
        });
    }
    // Update A Answer
    public int updateAnswer(Results result){
        return resultsDao.updateAnswer(result);
    }
    // Are You Answered
    public boolean isAnswerExists(int userId, int questionId){
        return resultsDao.isAnswerExists(userId,questionId);
    }
    public void deleteResults(Results result) {
        ExamRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                resultsDao.deleteResults(result);
            }
        });
    }
    // All Users With him Marks
    public LiveData<List<UserScore>> getUserScores(){
        return resultsDao.getUserScores();
    }
    // A user scoure by ID
    public UserScore getUserScoreById(int userId){
        return resultsDao.getUserScoreById(userId);
    }


}
