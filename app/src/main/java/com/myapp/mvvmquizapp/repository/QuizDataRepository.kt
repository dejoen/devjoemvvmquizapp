package com.myapp.mvvmquizapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.myapp.mvvmquizapp.roomdb.DatabaseBuilder
import com.myapp.mvvmquizapp.roomdb.QuizEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuizDataRepository @Inject constructor(@ApplicationContext val context: Context) {

    init {
        MainScope().launch(Dispatchers.IO) {
            DatabaseBuilder.getDataBaseInstance(context)
        }


    }

    fun getAllQuestions(): LiveData<MutableList<QuizEntity>> {
        return DatabaseBuilder.getDataBaseInstance(context)!!.quizDao().getAllQuestions()
    }
}