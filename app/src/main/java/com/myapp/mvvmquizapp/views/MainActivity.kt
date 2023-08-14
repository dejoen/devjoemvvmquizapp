package com.myapp.mvvmquizapp.views

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.myapp.mvvmquizapp.databinding.ActivityMainBinding
import com.myapp.mvvmquizapp.roomdb.QuizEntity
import com.myapp.mvvmquizapp.viewmodel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private var index = 0
    private lateinit var activityMainBinding: ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()
    private lateinit var questions: MutableList<QuizEntity>
    private var score = 0
    private var isAnswered=false
    private var totalScore= mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        quizViewModel.getAllQuizData().observe(this) {
            questions = it
            activityMainBinding.questionIndex.text =
                "question ${index + 1} out of ${questions.size}"
            activityMainBinding.questionText.text = questions[index].question
            activityMainBinding.optionA.text = questions[index].optionOne
            activityMainBinding.optionB.text = questions[index].optionTwo
            activityMainBinding.optionC.text = questions[index].optionThree
            Log.d(TAG, it.toString())
        }

    }

    override fun onStart() {
        super.onStart()
        initViews()
    }

    private fun initViews() {
        activityMainBinding.nextBtn.setOnClickListener {
            
            isAnswered=false
            activityMainBinding.optionA.setBackgroundColor(Color.BLUE)
            activityMainBinding.optionB.setBackgroundColor(Color.BLUE)
            activityMainBinding.optionC.setBackgroundColor(Color.BLUE)
            Log.d(TAG,"ibdex ")
            if (index < questions.size - 1) {
                index++
                totalScore.add(score)
                activityMainBinding.questionIndex.text =
                    "question ${index + 1} out of ${questions.size}"
                activityMainBinding.questionText.text = questions[index].question
                activityMainBinding.optionA.text = questions[index].optionOne
                activityMainBinding.optionB.text = questions[index].optionTwo
                activityMainBinding.optionC.text = questions[index].optionThree
                        score=0
            } else {
           score=0
                val dialog = AlertDialog.Builder(this@MainActivity)
                dialog.setTitle("Total Score")
                totalScore.forEach { 
                    score+=it
                }
                
                dialog.setMessage("you scored $score out of ${questions.size}")
                dialog.setPositiveButton("ok") { dialogI, _ ->
                    index = 0
                    activityMainBinding.questionIndex.text =
                        "question ${index + 1} out of ${questions.size}"
                    activityMainBinding.questionText.text = questions[index].question
                    activityMainBinding.optionA.text = questions[index].optionOne
                    activityMainBinding.optionB.text = questions[index].optionTwo
                    activityMainBinding.optionC.text = questions[index].optionThree
                    score = 0
                    totalScore.clear()
                    dialogI.dismiss()
                }
                dialog.create()
                dialog.show()
            }
        }

        activityMainBinding.optionA.setOnClickListener {
            activityMainBinding.optionA.setBackgroundColor(Color.GREEN)
            activityMainBinding.optionB.setBackgroundColor(Color.BLUE)
            activityMainBinding.optionC.setBackgroundColor(Color.BLUE)
            if (activityMainBinding.optionA.text.equals(questions[index].correctAnswer)) {
                if (!isAnswered) {
                    Log.d(TAG,"clicked")
                    score++
                    isAnswered = true
                }
            }else if(!activityMainBinding.optionA.text.equals(questions[index].correctAnswer)){
                if(score>0) score--
                isAnswered=false
            }
        }

        activityMainBinding.optionB.setOnClickListener {
            activityMainBinding.optionB.setBackgroundColor(Color.GREEN)
            activityMainBinding.optionA.setBackgroundColor(Color.BLUE)
            activityMainBinding.optionC.setBackgroundColor(Color.BLUE)
            if (activityMainBinding.optionB.text.equals(questions[index].correctAnswer)) {
                if (!isAnswered) {
                    Log.d(TAG,"clicked")
                    score++
                    isAnswered = true
                }
            }else if(!activityMainBinding.optionB.text.equals(questions[index].correctAnswer)){
                if(score>0) score--
                isAnswered = false
            }
        }

        activityMainBinding.optionC.setOnClickListener {
            activityMainBinding.optionC.setBackgroundColor(Color.GREEN)
            activityMainBinding.optionB.setBackgroundColor(Color.BLUE)
            activityMainBinding.optionA.setBackgroundColor(Color.BLUE)
            if (activityMainBinding.optionC.text.equals(questions[index].correctAnswer)) {
                if (!isAnswered) {
                    Log.d(TAG,"clicked")
                    score++
                    isAnswered = true

                }
            }else if(!activityMainBinding.optionC.text.equals(questions[index].correctAnswer)){
                if(score>0) score--
                isAnswered = false
            }
        }
    }

}