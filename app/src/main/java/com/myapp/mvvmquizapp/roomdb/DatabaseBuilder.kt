package com.myapp.mvvmquizapp.roomdb

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.myapp.mvvmquizapp.utils.QuestionsUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


object DatabaseBuilder {
    private const val TAG = "DatabaseBuilder"
    private var INSTANCE: QuizDatabase? = null
    fun getDataBaseInstance(context: Context): QuizDatabase? {
        if (INSTANCE == null) {
            synchronized(QuizDatabase::class) {
                INSTANCE = initRoomDb(context)
            }

        }
        return INSTANCE
    }

    private fun initRoomDb(context: Context) =
        Room.databaseBuilder(context, QuizDatabase::class.java, "QuizDatabase")
            .fallbackToDestructiveMigration()
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Log.d(TAG, "databaseCreated")
                    insertAllQuestions()
                }
            }).build()

    private fun insertAllQuestions() {
        val quizDao = INSTANCE?.quizDao()!!
        QuestionsUtil.getAllRawQuestions().mapIndexed { index, quizModel ->
            MainScope().launch(Dispatchers.IO) {
                quizDao.insertQuestion(
                    QuizEntity(
                        index + 1,
                        quizModel.question,
                        quizModel.optionOne,
                        quizModel.optionTwo,
                        quizModel.optionThree,
                        quizModel.correctAnswer
                    )
                )
            }

        }
    }
}