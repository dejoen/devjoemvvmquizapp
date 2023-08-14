package com.myapp.mvvmquizapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertQuestion(quizEntity: QuizEntity)

     @Update
 fun updateQuestions(quizEntity: QuizEntity)

    @Query("SELECT * FROM QuizTable ORDER BY questionId DESC")
   fun getAllQuestions():LiveData<MutableList<QuizEntity>>
}