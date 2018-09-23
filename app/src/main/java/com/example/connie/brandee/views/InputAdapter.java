package com.example.connie.brandee.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class InputAdapter extends RecyclerView.Adapter<InputAdapter.InputViewHolder> {
    @NonNull
    @Override
    public InputAdapter.InputViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull InputAdapter.InputViewHolder inputViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class InputViewHolder extends RecyclerView.ViewHolder {
        public InputViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
