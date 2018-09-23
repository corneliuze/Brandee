package com.example.connie.brandee.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.connie.brandee.R;

import java.util.ArrayList;
import java.util.Collections;

public class InputAdapter extends RecyclerView.Adapter<InputAdapter.InputViewHolder> {
    Context context;
    ArrayList<Character> input;
    InputClickListener inputClickListener;
    int lengthOfAnswer;

    public InputAdapter(Context context, ArrayList<Character> input, InputClickListener inputClickListener, int lengthOfAnswer){
        this.context = context;
        this.input = input;
        this.inputClickListener = inputClickListener;
        this.lengthOfAnswer = lengthOfAnswer;
    }
    @NonNull
    @Override
    public InputAdapter.InputViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.each_input_layout, viewGroup, false);

        return new InputViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InputAdapter.InputViewHolder inputViewHolder, int i) {
        char text = input.get(i);
        inputViewHolder.inputTextView.setText(String.valueOf(text));
        if (text == '#'){
            inputViewHolder.itemView.setVisibility(View.INVISIBLE);
            }else {
            inputViewHolder.itemView.setVisibility(View.VISIBLE);
             inputViewHolder.itemView.setOnClickListener(view -> {
                 if (Collections.frequency(input, '#') != lengthOfAnswer){
                     inputViewHolder.itemView.setVisibility(View.INVISIBLE);
                     input.remove(i);
                     input.add(i, '#');
                     inputClickListener.onInputClick(i, text);
                     notifyDataSetChanged();

                 }
             });
        }

    }

    @Override
    public int getItemCount() {
        return input.size();
    }
    public void onReturnCharacter(char cr){
        int index = input.indexOf('#');
        input.remove(index);
        input.add(index, cr);
        notifyDataSetChanged();
    }

    public class InputViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout inputRelLayout;
        TextView inputTextView;

        public InputViewHolder(@NonNull View itemView) {
            super(itemView);
            inputRelLayout = itemView.findViewById(R.id.input_rel_view);
            inputTextView = itemView.findViewById(R.id.input_text_view);

        }
    }

    public interface InputClickListener  {
        void onInputClick(int position, char ch);

    }
}
