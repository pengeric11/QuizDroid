package edu.washington.ericpeng.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.*

class QuestionPage : AppCompatActivity() {

    private lateinit var questions : ArrayList<Question>
    //private var num : Int = 0
    private lateinit var answer : String
    //private lateinit var numCorrect : Double

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.question_page)

        val tv : TextView = findViewById(R.id.question)
        val rg : RadioGroup = findViewById(R.id.rg)
        val btn : Button = findViewById(R.id.submit)

        btn.isEnabled = false

        var numCorrect : Int = intent.getIntExtra("numCorrect", 0)
        val num : Int = intent.getIntExtra("num", 0)
        //val topic : String = intent.getStringExtra("topic")
        questions = intent.getSerializableExtra("questions") as ArrayList<Question>

        tv.text = questions[num].question

        for (item in questions[num].choices){
            val rb = RadioButton(this)
            rb.text = item
            rg.addView(rb)
        }

        //var selected : Int = 0
        var user : String = ""
        //val correct : Boolean = questions[rg.checkedRadioButtonId].answer == questions[num].answer
        rg.setOnCheckedChangeListener { buttonView, isChecked ->

            val rb : RadioButton = findViewById(isChecked)

            if (rb.text.toString() == questions[num].answer)
                numCorrect += 1

            //selected = isChecked
            user = rb.text.toString()
            btn.isEnabled = true
        }

        //Log.i("Main", rg.checkedRadioButtonId.toString())

        btn.setOnClickListener({view ->
            val intent = Intent(this, AnswerPage::class.java)
            intent.putExtra("user", user)
            intent.putExtra("answer", questions[num].answer)
            intent.putExtra("numCorrect", numCorrect)
            intent.putExtra("questions", questions)
            intent.putExtra("num", num)
            startActivity(intent)
        })
    }
}
