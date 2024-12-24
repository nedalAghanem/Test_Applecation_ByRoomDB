package com.example.homework1exam.roomDatabase.entitys;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "Users")
public class Users {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    private int id ;
    @NonNull
    private String userName ;
    @NonNull
    private String email ;
    private String password ;

    public Users() {
    }

    public Users(int id, @NonNull String userName,@NonNull String email,String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password ;
    }

    public Users(@NonNull String userName, @NonNull String email,String password) {
        this.userName = userName;
        this.email = email;
        this.password =password;
    }

    public Users(@NonNull String userName, @NonNull String email) {
        this.userName = userName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
