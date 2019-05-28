package com.example.connie.brandee.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.connie.brandee.models.QuestionsModel;
import com.google.gson.Gson;

public class PreferenceManager {
    private Context context;
    private SharedPreferences sharedPreferences;
    private static final String ANSWERED_QUESTIONS = "ANSWERED_QUESTIONS";
    private static final String CURRENT_QUESTIONS = "CURRENT_QUESTIONS";
    private static final String FILE_NAME = "com.example.connie.brandee";

    public PreferenceManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
    }

    //this is the method to add answered questions to a string
    //they were added by the question id
    public void addToAnsweredQuestion(String id) {
        String allQsts = getAnsweredQuestions();
        allQsts += ("," + id);  // this adds every question answered to a string.
        this.sharedPreferences.edit().putString(ANSWERED_QUESTIONS, allQsts).apply();

    }


    //this method gets all the answered questions
    public String getAnsweredQuestions() {
        return this.sharedPreferences.getString(ANSWERED_QUESTIONS, "");
    }

    //this method is to get the current question
    public QuestionsModel getCurrentQuestion() {
        String questionJsonToString = this.sharedPreferences.getString(CURRENT_QUESTIONS, "{}");
        if (questionJsonToString.equals("{}"))
            return null;
        return new Gson().fromJson(questionJsonToString, QuestionsModel.class);
    }

    //this method sets the current question
    public void setCurrentQuestions(QuestionsModel questionsModel) {
        this.sharedPreferences.edit().putString(CURRENT_QUESTIONS, new Gson().toJson(questionsModel)).apply();
        //converted the question is json form to strings using gson converter factory
    }

    //this methods is to check if a question is answered by checking if the id we used to add in the getAnsweredQuestion is in the list of string
    public boolean isQuestionAnswered(String id) {
        return getAnsweredQuestions().contains(id);
    }
}
