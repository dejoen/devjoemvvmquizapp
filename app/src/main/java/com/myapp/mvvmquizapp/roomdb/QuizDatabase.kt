package com.myapp.mvvmquizapp.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuizEntity::class], version = 1)
abstract  class QuizDatabase: RoomDatabase(){
    abstract fun quizDao():QuizDao
}