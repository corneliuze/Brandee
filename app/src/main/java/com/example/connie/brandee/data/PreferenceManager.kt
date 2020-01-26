package com.example.connie.brandee.data

import android.content.Context
import android.content.SharedPreferences

import com.example.connie.brandee.models.QuestionsModel
import com.google.gson.Gson

class PreferenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, 0)
    }


    //this method gets all the answered questions
    val answeredQuestions: String?
        get() = this.sharedPreferences.getString(ANSWERED_QUESTIONS, "")

    //this method is to get the current question
    val currentQuestion: QuestionsModel?
        get() {
            val questionJsonToString = this.sharedPreferences.getString(CURRENT_QUESTIONS, "{}")
            return if (questionJsonToString == "{}") null else Gson().fromJson<QuestionsModel>(questionJsonToString, QuestionsModel::class.java)
        }



    //this is the method to add answered questions to a string
    //they were added by the question id
    fun addToAnsweredQuestion(id: String) {
        var allQsts = answeredQuestions
        allQsts += ",$id"  // this adds every question answered to a string.
        this.sharedPreferences.edit().putString(ANSWERED_QUESTIONS, allQsts).apply()

    }

    //this method sets the current question
    fun setCurrentQuestions(questionsModel: QuestionsModel?) {
        this.sharedPreferences.edit().putString(CURRENT_QUESTIONS, Gson().toJson(questionsModel)).apply()
        //converted the question is json form to strings using gson converter factory
    }

    //this methods is to check if a question is answered by checking if the id we used to add in the getAnsweredQuestion is in the list of string
    fun isQuestionAnswered(id: String): Boolean {
        return answeredQuestions!!.contains(id)
    }

    companion object {
        private const val ANSWERED_QUESTIONS = "ANSWERED_QUESTIONS"
        private const val CURRENT_QUESTIONS = "CURRENT_QUESTIONS"
        private const val FILE_NAME = "com.example.connie.brandee"
    }
}
