package com.example.homework1exam;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework1exam.databinding.CustomQustionItemBinding;
import com.example.homework1exam.roomDatabase.Repository;
import com.example.homework1exam.roomDatabase.UserScore;
import com.example.homework1exam.roomDatabase.entitys.Questions;
import com.example.homework1exam.roomDatabase.entitys.Results;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder> {
    List<Questions> questions;
    Context context;
    private int numberOfQuestions = 0;
    int userID;

    private final int[] colors = {
            // from Internet
            Color.parseColor("#FFCDD2"), // وردي
            Color.parseColor("#C8E6C9"), // اخضر
            Color.parseColor("#BBDEFB"), // ازرق
            Color.parseColor("#FFF9C4"), // اصفر
            Color.parseColor("#D1C4E9")  // بنفسجي
    };


    public QuestionsAdapter(List<Questions> questions, Context context, int userId) {
        this.questions = questions;
        this.context = context;
        this.userID = userId;
    }

    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuestionsViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.custom_qustion_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {
        Questions question = questions.get(position);

        holder.bind(question);

        holder.binding.customIvStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.binding.customIvStar.getDrawable().getConstantState() ==
                        context.getResources().getDrawable(R.drawable.star).getConstantState()) {
                    holder.binding.customIvStar.setImageResource(R.drawable.star_selected);
                } else {
                    holder.binding.customIvStar.setImageResource(R.drawable.star);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class QuestionsViewHolder extends RecyclerView.ViewHolder {
        CustomQustionItemBinding binding;
        Questions question;
        Repository repo;


        public QuestionsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomQustionItemBinding.bind(itemView);
        }

        // this method to clean Code
        public void bind(Questions question) {
            this.question = question;
            binding.customQustionText.setText(question.getQuestionText());
            binding.customChoise1.setText(question.getOptionA());
            binding.customChoise2.setText(question.getOptionB());
            binding.customChoise3.setText(question.getOptionC());
            binding.customChoise4.setText(question.getOptionD());
            numberOfQuestions += 1;
            binding.customTvNumberQuestion.setText(String.valueOf(numberOfQuestions));

            // تعيين لون عشوائي للكارد فيو
            int randomColor = colors[new Random().nextInt(colors.length)];
            binding.cardView.setCardBackgroundColor(randomColor);

            // add a new answer
            // to add a new answer
            repo = new Repository(((Activity) context).getApplication());  // getApplication()

            binding.customRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    Intent intent = new Intent(context.getApplicationContext(), SoundService.class);
                    if (checkedId == R.id.custom_choise1) {
                        // choose A
                        addAnswerToDatabase(userID, question.getQuestionId(), "A", question.getCorrectOption());
                        context.stopService(intent);
                        context.startService(intent);
                    } else if (checkedId == R.id.custom_choise2) {
                        // choose B
                        addAnswerToDatabase(userID, question.getQuestionId(), "B", question.getCorrectOption());
                        context.stopService(intent);
                        context.startService(intent);
                    } else if (checkedId == R.id.custom_choise3) {
                        // choose C
                        addAnswerToDatabase(userID, question.getQuestionId(), "C", question.getCorrectOption());
                        context.stopService(intent);
                        context.startService(intent);
                    } else if (checkedId == R.id.custom_choise4) {
                        // choose D
                        addAnswerToDatabase(userID, question.getQuestionId(), "D", question.getCorrectOption());
                        context.stopService(intent);
                        context.startService(intent);
                    }

                }
            });

        }

        // Add A data to Answer Table
        private void addAnswerToDatabase(int userId, int questionId, String userAnswer, String correctOption) {
            String isCorrect = userAnswer.equals(correctOption) ? "Correct" : "Wrong";
            if (repo.isAnswerExists(userId, questionId)) {
                // Update The Answer
                int re = repo.updateAnswer(new Results(userId, questionId, userAnswer, isCorrect));
                Toast.makeText(context.getApplicationContext(), "Updated a answer " + "userId:" + userID, Toast.LENGTH_SHORT).show();
            } else {
                // Add a new Answer
                repo.insertResult(new Results(userId, questionId, userAnswer, isCorrect));
                Toast.makeText(context.getApplicationContext(), "Add answer Successfully ! " + "userId:" + userID, Toast.LENGTH_SHORT).show();
            }

        }


    }


}

