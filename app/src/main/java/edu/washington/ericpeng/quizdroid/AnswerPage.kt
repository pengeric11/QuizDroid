package edu.washington.ericpeng.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class AnswerPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.answer_page)

        val answer : String = intent.getStringExtra("answer")
        val numCorrect : Int = intent.getIntExtra("numCorrect", 0)
        val num : Int = intent.getIntExtra("num", 1)
        val user : String = intent.getStringExtra("user")
        val questions = intent.getSerializableExtra("questions") as ArrayList<Question>

        val tv1 : TextView = findViewById(R.id.answer)
        val tv2 : TextView = findViewById(R.id.total)
        val tv3 : TextView = findViewById(R.id.correct)

        tv1.text = "Your answer was " + user
        tv3.text = "The correct answer is " + answer
        tv2.text = "You've gotten " + numCorrect + " correct out of " + questions.size  + " questions"

        val btn : Button = findViewById(R.id.btn)

        if (num < questions.size - 1)
            btn.text = "Next"
        else btn.text = "Finish"

        btn.setOnClickListener({view ->
            if (num == questions.size - 1){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                val intent = Intent(this, QuestionPage::class.java)
                intent.putExtra("numCorrect", numCorrect)
                intent.putExtra("questions", questions)
                intent.putExtra("num", num + 1)
                startActivity(intent)
                finish()
            }
        })

    }
}
