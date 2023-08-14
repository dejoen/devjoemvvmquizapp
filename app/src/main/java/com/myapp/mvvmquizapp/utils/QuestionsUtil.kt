package com.myapp.mvvmquizapp.utils

import com.myapp.mvvmquizapp.model.QuizModel

object QuestionsUtil {

    fun getAllRawQuestions():MutableList<QuizModel>{
        val questions= mutableListOf<QuizModel>()
        questions.add(QuizModel("what is the answer 0f 2 X 4","6","5","8","8"))
        questions.add(QuizModel("what is the answer 0f 2 X 9","16","15","18","18"))
        questions.add(QuizModel("what is the answer 0f 2 X 20","40","39","45","40"))
        return  questions
    }
}