package com.myapp.mvvmquizapp.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "QuizTable")
data class QuizEntity(
    @PrimaryKey(autoGenerate = true) val questionId:Int,
    @ColumnInfo(name = "question") val question: String,
    @ColumnInfo(name = "optionOne") val optionOne: String,
    @ColumnInfo(name = "optionTwo") val optionTwo: String,
    @ColumnInfo(name = "optionThree") val optionThree: String,
    @ColumnInfo(name = "correctAnswer") val correctAnswer: String
)