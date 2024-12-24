package com.example.homework1exam;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.homework1exam.databinding.ActivityMainBinding;
import com.example.homework1exam.databinding.ActivityRegisterBinding;
import com.example.homework1exam.roomDatabase.Repository;
import com.example.homework1exam.roomDatabase.entitys.Users;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    Repository repo;
    public static final int PERMISSION_REQ_CODE = 301;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            // هذا الكود اذا لم ياخذ الصلاحية يرجع يطلبها
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQ_CODE);
//        }

        repo = new Repository(getApplication());

        binding.usernameInput.setVisibility(View.GONE);
        binding.emailInput.setVisibility(View.VISIBLE);
        binding.passwordInput.setVisibility(View.VISIBLE);

        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userEmail = binding.emailInput.getText().toString().trim();
                String userPassword = binding.passwordInput.getText().toString().trim();
                Users loggedInUser = repo.loginUser(userEmail, userPassword);

                if (userEmail != null && userPassword != null) {
                    if (loggedInUser != null) {
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        int id = repo.returnUserId(userEmail);
                        Toast.makeText(RegisterActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        binding.usernameInput.setText("");
                        binding.emailInput.setText("");
                        binding.passwordInput.setText("");
                        intent.putExtra("userId", id);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Incorrect email or password!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }


            }

        });

        binding.tvHaveAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.usernameInput.setVisibility(View.GONE);
                binding.emailInput.setVisibility(View.VISIBLE);
                binding.passwordInput.setVisibility(View.VISIBLE);
                binding.emailInput.setText("");
                binding.passwordInput.setText("");
                binding.registerButton.setText("Login");
                binding.tvHaveAcount.setVisibility(View.GONE);
                binding.tvCreateAccount.setVisibility(View.VISIBLE);

                binding.registerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userEmail = binding.emailInput.getText().toString().trim();
                        String userPassword = binding.passwordInput.getText().toString().trim();
                        Users loggedInUser = repo.loginUser(userEmail, userPassword);

                        if (userEmail != null && userPassword != null) {
                            if (loggedInUser != null) {
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                int id = repo.returnUserId(userEmail);
                                Toast.makeText(RegisterActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                binding.usernameInput.setText("");
                                binding.emailInput.setText("");
                                binding.passwordInput.setText("");
                                intent.putExtra("userId", id);
                                startActivity(intent);
                                finish();

                            } else {
                                Toast.makeText(RegisterActivity.this, "Incorrect email or password!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        binding.tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.usernameInput.setVisibility(View.VISIBLE);
                binding.emailInput.setVisibility(View.VISIBLE);
                binding.passwordInput.setVisibility(View.VISIBLE);
                binding.registerButton.setText("Register");
                binding.tvHaveAcount.setVisibility(View.VISIBLE);
                binding.tvCreateAccount.setVisibility(View.GONE);

                binding.registerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String userName = binding.usernameInput.getText().toString().trim();
                        String userEmail = binding.emailInput.getText().toString().trim();
                        String userPassword = binding.passwordInput.getText().toString().trim();
                        if (!userEmail.contains("@")) {
                            binding.emailInput.setError("Email must contain '@'");
                            userEmail = null;
                        }
                        if (userName != null && userEmail != null && userPassword != null) {

                            Users loggedInUserBuEmail = repo.returnUserByEmail(userEmail);
                            if (loggedInUserBuEmail != null) {
                                Toast.makeText(RegisterActivity.this, "You Already have account", Toast.LENGTH_SHORT).show();
                            } else {
                                repo.insertUser(new Users(userName, userEmail, userPassword));
                                Toast.makeText(RegisterActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                binding.usernameInput.setText("");
                                binding.emailInput.setText("");
                                binding.passwordInput.setText("");
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                int id = repo.returnUserId(userEmail);
                                intent.putExtra("userId", id);
                                startActivity(intent);
                                finish();
                            }

                        } else {
                            Toast.makeText(RegisterActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });


    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PERMISSION_REQ_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // تم الحصول على الصلاحية
//
//            } else {
//                // تم رفض الصلاحية قم بارسال رسالة للمستخدم
//            }
//        }
//    }
}