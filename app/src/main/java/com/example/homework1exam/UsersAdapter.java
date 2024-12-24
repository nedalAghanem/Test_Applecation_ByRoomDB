package com.example.homework1exam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework1exam.databinding.CustomUsersMarkItemBinding;
import com.example.homework1exam.roomDatabase.Repository;
import com.example.homework1exam.roomDatabase.UserScore;
import com.example.homework1exam.roomDatabase.entitys.Results;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    List<UserScore> userScores;
    Context context;

    public UsersAdapter(List<UserScore> userScores, Context context) {
        this.userScores = userScores;
        this.context = context;
        addItemToList();
    }

    public void addItemToList(){
        userScores.add(new UserScore("Nedal Ali Gh","nedal@gmail.com",28));
        userScores.add(new UserScore("Eng Ody Fiad","odyfiad@gmail.com",17));
        userScores.add(new UserScore("Ramadan Ejmaana","ramadan@gmail.com",23));
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UsersViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.custom_users_mark_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        UserScore score = userScores.get(position);
        holder.bind(score);
    }

    @Override
    public int getItemCount() {
        return userScores.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        CustomUsersMarkItemBinding binding;
        UserScore userScores;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomUsersMarkItemBinding.bind(itemView);
        }

        public void bind(UserScore userScore) {
            this.userScores = userScore;
            binding.customTvUsername.setText(userScore.getUserName());
            binding.customTvEmail.setText(userScore.getUserEmail());
            binding.customTvMark.setText(userScore.getCorrectAnswers() + "%");
        }
    }
}
