package com.example.connie.brandee.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder> {
    @NonNull
    @Override
    public AnswerAdapter.AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerAdapter.AnswerViewHolder answerViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AnswerViewHolder extends RecyclerView.ViewHolder {
        public AnswerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
