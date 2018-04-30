package edu.washington.ericpeng.quizdroid

import java.io.Serializable
import java.util.ArrayList

class Topic(val questions: ArrayList<Question>, val title: String, val shortDescription: String, val longDescription: String) : Serializable
