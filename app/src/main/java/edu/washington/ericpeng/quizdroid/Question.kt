package edu.washington.ericpeng.quizdroid

import java.io.Serializable
import java.lang.reflect.Array

class Question(val question: String, val answer: String, val choices: ArrayList<String>) : Serializable
