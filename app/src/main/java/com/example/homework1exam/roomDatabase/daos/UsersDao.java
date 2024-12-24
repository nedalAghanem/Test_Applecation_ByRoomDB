package com.example.homework1exam.roomDatabase.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.homework1exam.roomDatabase.entitys.Users;

import java.util.List;

@Dao
public interface UsersDao {

    // لإضافة مستخدم جديد
    @Insert
    void insertUser(Users user);

    @Delete
    void deleteUsers(Users user);

    @Query("select * from Users order by userName asc")
    LiveData<List<Users>> getAllUsers(); // هنا استخدمت اللايف داتا المطلوبة في الفيديو

    // للتحقق من تسجيل الدخول باستخدام البريد الإلكتروني وكلمة المرور
    @Query("SELECT * FROM Users WHERE email = :email AND password = :password")
    Users loginUser(String email, String password);

    // جلب عدد الايميلات المتوفرة للفحص
    @Query("SELECT COUNT(*) FROM Users WHERE email = :email")
    int isEmailExists(String email); // عشان يرجلعي عدد البريدات وانا افحص بالشاشة

    // حذف يوزر من ايميلو
    @Query("delete from Users where email =:email")
    void deleteUsersByEmail(String email);

    @Query("SELECT Users.user_id FROM Users WHERE Users.email = :email ")
    int returnUserId(String email);

    @Query("SELECT * FROM Users WHERE Users.email = :email ")
    Users returnUserByEmail(String email);


}









