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

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder> {

    Context context;
    String answer;
    ArrayList<Character> inputs = new ArrayList<>();

    AnswerClickListener answerClickListener;
    AnswerFilledListener answerFilledListener;
    public  AnswerAdapter (Context context, String answer,  AnswerClickListener answerClickListener,
                           AnswerFilledListener answerFilledListener){
        this.context = context;
        this.answer = answer;
        this.inputs = inputs;
        this.answerClickListener = answerClickListener;
        this.answerFilledListener = answerFilledListener;
        for (int i = 0; i < answer.length(); i++){
            inputs.add('#');
        }
    }
    @NonNull
    @Override
    public AnswerAdapter.AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.each_answer_layout , viewGroup, false);
        return  new AnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerAdapter.AnswerViewHolder answerViewHolder, int i) {
        char text = inputs.get(i);
        if (text == '#'){
            answerViewHolder.answerRelLayout.setBackground(context.getResources().getDrawable(R.drawable.black_bg));
            answerViewHolder.answerTextView.setText("");
        }else {
            answerViewHolder.answerRelLayout.setBackground(context.getResources().getDrawable(R.drawable.green_bg));
            answerViewHolder.answerTextView.setText(String.valueOf(text));
            answerViewHolder.itemView.setOnClickListener(view -> {
                inputs.remove(i);
                inputs.add(i, '#');
                notifyDataSetChanged();
                answerClickListener.onAnswerClicked(text);
            });
            if (inputs.indexOf('#') == -1){
                String ans = "";
                for (char cr : inputs){
                    ans += cr;
                }
                boolean isCorrect = answer.equals(ans);
                answerFilledListener.onAnswerFilled(isCorrect);
            }
        }
    }

    @Override
    public int getItemCount() {
        return answer.length();
    }

    public class AnswerViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout answerRelLayout;
        TextView answerTextView;
        public AnswerViewHolder(@NonNull View itemView) {
            super(itemView);
            answerRelLayout = itemView.findViewById(R.id.answer_rel_view);
            answerTextView = itemView.findViewById(R.id.answer_text_view);
        }
    }
    public void addNewCharacter(int position, char cr){
        int index = inputs.indexOf('#');
        inputs.remove(index);
        inputs.add(index, cr);
        notifyDataSetChanged();

    }
    public interface AnswerClickListener{
        void onAnswerClicked(char cr);
    }
    public interface  AnswerFilledListener{
        void onAnswerFilled(boolean isCorrect);
    }
}
