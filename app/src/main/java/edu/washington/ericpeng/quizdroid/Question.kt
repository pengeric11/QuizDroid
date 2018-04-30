package edu.washington.ericpeng.quizdroid

import java.io.Serializable
import java.lang.reflect.Array

class Question(val question: String, val ans1: String, val ans2: String, val ans3: String, val ans4: String, val correct: Int) : Serializable {
    fun getAnswers() : ArrayList<String> {
        val answers : ArrayList<String> = ArrayList()

        answers.add(ans1)
        answers.add(ans2)
        answers.add(ans3)
        answers.add(ans4)

        return answers
    }
}

