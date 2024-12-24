package com.example.homework1exam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.homework1exam.roomDatabase.Repository;
import com.example.homework1exam.roomDatabase.UserScore;
import com.example.homework1exam.databinding.ActivityMainBinding;
import com.example.homework1exam.roomDatabase.entitys.Questions;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private QuestionsAdapter adapter;
    private Repository repo;
    int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.mainToolbar);

        Intent intent = getIntent();
        userId = intent.getIntExtra("userId", -1);


        repo = new Repository(getApplication());
        repo.getQuestionsRandomly().observe(this,
                new Observer<List<Questions>>() { // هان استخدمنا اللايف داتا
                    @Override
                    public void onChanged(List<Questions> questions) {
                        // عند تحديث البيانات يقوم بتحديث الكود
                        populateQuestionIntoRV(questions);
                    }
                });


        binding.mainFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(binding.mainToolbar, "Float Action Button Clicked", BaseTransientBottomBar.LENGTH_LONG).show();


                Intent intent = new Intent(getBaseContext(), DetailsActivity.class);
                UserScore userScore = repo.getUserScoreById(userId);
                Toast.makeText(MainActivity.this, userScore.getCorrectAnswers()+"", Toast.LENGTH_SHORT).show();
                if (userScore != null) {
                    // استخدم المعلومات حسب الحاجة
                    String userName = userScore.getUserName();
                    String email = userScore.getUserEmail();
                    int correctAnswers = userScore.getCorrectAnswers();
                    Toast.makeText(MainActivity.this, "name:" + userName + "/email:" + email + "/score:" + correctAnswers, Toast.LENGTH_SHORT).show();
                    intent.putExtra("user_name", userName);
                    intent.putExtra("email", email);
                    intent.putExtra("score", correctAnswers);
//                    finish();
                    Log.d("MainActivity", "FAB clicked");
            }
                startActivity(intent);

            }
    });

    }

    // To Add A list to Adapter and refresh them
    void populateQuestionIntoRV(List<Questions> questions) {
        adapter = new QuestionsAdapter(questions, this, userId);
        binding.mainRv.setAdapter(adapter);
        binding.mainRv.setLayoutManager(new LinearLayoutManager(this));
        binding.mainRv.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
    }

    //        registerForContextMenu(tv);
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_exit) {
            Toast.makeText(getApplicationContext(), "You are log out", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return false;
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        getMenuInflater().inflate(R.menu.menu_copytext,menu);
//    }

// to copy aQuastion from a card
//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.menu_copy){
//            Toast.makeText(getApplicationContext(), "Text Copyed", Toast.LENGTH_SHORT).show();
//            String text = tv.getText().toString();
//            return true ;
//        }
//        return false ;
//    }

}