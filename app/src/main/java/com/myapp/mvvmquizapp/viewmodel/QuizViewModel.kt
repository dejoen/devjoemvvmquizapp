package com.myapp.mvvmquizapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.mvvmquizapp.repository.QuizDataRepository
import com.myapp.mvvmquizapp.roomdb.QuizEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val quizDataRepository: QuizDataRepository) :
    ViewModel() {


    fun getAllQuizData(): LiveData<MutableList<QuizEntity>> {
        var allQuestions: LiveData<MutableList<QuizEntity>>? = null
        viewModelScope.launch {
            allQuestions = quizDataRepository.getAllQuestions()
        }
        return allQuestions!!
    }

}