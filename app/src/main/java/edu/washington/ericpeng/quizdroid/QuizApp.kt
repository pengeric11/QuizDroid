package edu.washington.ericpeng.quizdroid

import android.util.Log
import android.app.Application

class QuizApp : Application() {

    companion object {
        fun create() : QuizApp = QuizApp()
    }

    //private var instance : TopicRepository = TopicRepository.create()

    init {
        Log.d("QuizApp", "QuizApp Constructor")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("QuizApp", "QuizApp OnCreate Method")
        //instance = TopicRepository().getInstance()
    }

    fun getInstance() : TopicRepository = TopicRepository.create()

}
