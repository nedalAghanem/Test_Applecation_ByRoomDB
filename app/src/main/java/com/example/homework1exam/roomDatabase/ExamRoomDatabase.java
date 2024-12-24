package com.example.homework1exam.roomDatabase;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.homework1exam.roomDatabase.daos.QuestionsDao;
import com.example.homework1exam.roomDatabase.daos.ResultsDao;
import com.example.homework1exam.roomDatabase.daos.UsersDao;
import com.example.homework1exam.roomDatabase.entitys.Questions;
import com.example.homework1exam.roomDatabase.entitys.Results;
import com.example.homework1exam.roomDatabase.entitys.Users;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Users.class, Results.class, Questions.class}, version = 1, exportSchema = false)
public abstract class ExamRoomDatabase extends RoomDatabase {

    public abstract UsersDao usersDao();
    public abstract QuestionsDao questionsDao();
    public abstract ResultsDao resultsDao();

    private static volatile ExamRoomDatabase INSTANCE; // هذا مؤشر على الداتابيز حتى تصل لاخر قيمة للمتغير للتعديل عليها
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ExamRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ExamRoomDatabase.class) { // هادي للفحص مرة اخرى لتحسب ان الاوبجكت منشا مسبقا
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ExamRoomDatabase.class, "exam_database")
                            .addCallback(sRoomDatabaseCallback)
                            .allowMainThreadQueries() // حتى تنفذ جمل الاستعلام في المين ثريد
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                // استخدام INSTANCE للوصول إلى DAO
                ExamRoomDatabase dbInstance = INSTANCE; // تأكد من أن INSTANCE تم إنشاؤها في getDatabase

                if (dbInstance != null) {
                    QuestionsDao questionsDao = dbInstance.questionsDao(); // الحصول على QuestionsDao
                    questionsDao.insertQuestions(QuestionData.getQuestions()); // إدخال الأسئلة
                }
            });
        }
    };
}
