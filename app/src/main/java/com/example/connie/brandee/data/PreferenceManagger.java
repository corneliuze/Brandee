package com.example.connie.brandee.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.connie.brandee.models.Questions;
import com.google.gson.Gson;

public class PreferenceManagger {
    Context context;
    SharedPreferences sharedPreferences;
    private static final String ANSWERED_QUESTIONS = "ANSWERED_QUESTIONS";
    private static final String CURRENT_QUESTIONS = "CURRENT_QUESTIONS";
    private static final String FILE_NAME = "com.example.connie.brandee";

    public PreferenceManagger (Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
    }
public void addToAnsweredQuestion(String id){
        String allQsts = getAnsweredQuestions();
        allQsts += ("," + id);  // this adds every question answered to a string.
        this.sharedPreferences.edit().putString(ANSWERED_QUESTIONS, allQsts).apply();

}

    public String getAnsweredQuestions() {
        return  this.sharedPreferences.getString(ANSWERED_QUESTIONS, "");
    }
    public Questions getCurrentQuestion(){
        String questionJsonToString = this.sharedPreferences.getString(CURRENT_QUESTIONS, "{}");
        if (questionJsonToString.equals("{}")) return null;
        return new Gson().fromJson(questionJsonToString, Questions.class);
    }
    public void setCurrentQuestions(Questions questions){
        this.sharedPreferences.edit().putString(CURRENT_QUESTIONS, new Gson().toJson(questions)).apply();
    }
    public boolean isQuestionAnswered(String id){
        return getAnsweredQuestions().contains(id);
    }
}
