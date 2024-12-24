package com.example.homework1exam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.homework1exam.databinding.ActivityDetailsBinding;
import com.example.homework1exam.roomDatabase.Repository;
import com.example.homework1exam.roomDatabase.UserScore;
import com.example.homework1exam.roomDatabase.entitys.Questions;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    ActivityDetailsBinding binding;
    Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Intent intent = getIntent();
        String userName = intent.getStringExtra("user_name");
        String email = intent.getStringExtra("email");
        int score = intent.getIntExtra("score", 00);



        binding.detailsTvUsername.setText(userName);
        binding.detailsTvUsermark.setText(score + "");

        repo = new Repository(getApplication());

        repo.getUserScores().observe(this,
                new Observer<List<UserScore>>() { // هان استخدمنا اللايف داتا
                    @Override
                    public void onChanged(List<UserScore> questions) {
                        // عند تحديث البيانات يقوم بتحديث الكود
                        populateUserScoreIntoRV(questions);
                    }
                });
    }

    void populateUserScoreIntoRV(List<UserScore> score) {
        UsersAdapter adapter = new UsersAdapter(score, this);
        binding.detailsRv.setAdapter(adapter);
        binding.detailsRv.setLayoutManager(new LinearLayoutManager(this));
        binding.detailsRv.setHasFixedSize(true);
        adapter.notifyDataSetChanged();

    }


}