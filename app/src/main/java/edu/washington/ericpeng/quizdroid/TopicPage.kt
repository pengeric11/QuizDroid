package edu.washington.ericpeng.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class TopicPage : AppCompatActivity() {

    private var questions = ArrayList<Question>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.topic_overview)

        val topic : String = intent.getStringExtra("topic")
        val tv : TextView = findViewById(R.id.tv_title)

        val tv_desc : TextView = findViewById(R.id.tv_desc)
        val tv_info : TextView = findViewById(R.id.tv_info)

        val btn : Button = findViewById(R.id.begin)

        tv.text = topic

        when(topic) {

            "Math" -> {
                tv_desc.text = "Lots of number, even more fun"
                questions.add(Question("In the complex number system, which of the following is equal to\n" +
                        "(14 − 2i)(7 + 12i)?", "74 + 154i", arrayListOf("74", "122", "74 + 154i", "122 + 154i")))
                questions.add(Question("Which of the following is a subset of  {b, c, d}",
                        "{ }", arrayListOf("{ }", "{a}", "{1 , 2 , 3}", "{a, b, c}")))
                questions.add(Question("The value of 5 in the number 357.21 is",
                        "5 tens", arrayListOf("5 tenths", "5 ones", "5 tens", "5 hundreds")))

                tv_info.text = "Total Questions: " + questions.size.toString()
            }

            "Physics" -> {
                tv_desc.text = "More than just gravity and Fig Newtons"

                questions.add(Question("Which of the following is a physical quantity that has a magnitude but no direction?"
                        , "Scalar", arrayListOf("Vector", "Frame of reference", "Resultant", "Scalar")))
                questions.add(Question("Multiplying or dividing vectors by scalars results in",
                        "Vectors", arrayListOf("Vectors if multiplied or scalars if divided",
                        "Scalars if multiplied scalars", "Scalars", "Vectors")))
                questions.add(Question("Which of the following is an example of a vector quantity?",
                        "Velocity", arrayListOf("Temperature", "Velocity", "Volume", "Mass")))
                questions.add(Question("For the winter, a duck flies 10.0 m/s due south against a " +
                        "gust of wind with a speed of 2.5 m/s. What is the resultant velocity of the duck?",
                        "7.5 m/s south", arrayListOf("-7.5 m/s south", "12.5 m/s south", "7.5 m/s south", "-12.5 m/s south")))

                tv_info.text = "Total Questions: " + questions.size.toString()
            }

            "Marvel Super Heroes" -> {
                tv_desc.text = "Probably the funnest quiz you'll ever take"

                questions.add(Question("In what place was Christmas once illegal?"
                        , "England", arrayListOf("Brazil", "Russia", "England", "France")))
                questions.add(Question("In California, it is illegal to eat oranges while doing what?",
                        "Bathing", arrayListOf("Gardening",
                        "Working on a computer", "Driving", "Bathing")))
                questions.add(Question("Coulrophobia means fear of what?",
                        "Clowns", arrayListOf("Clowns", " Old People", " cred Things\n", "Mass")))
                questions.add(Question("Which of the following is the longest running American animated TV show?",
                        "Simpsons", arrayListOf("Rugrats", " TV Funhouse", "Pokemon", "Simpsons")))
                questions.add(Question("What are the odds of being killed by space debris?",
                        "1 in 5 billion", arrayListOf("1 in 5 million", "1 in 5 billion", "1 in 10 billion", "1 in 1 trillion")))

                tv_info.text = "Total Questions: " + questions.size.toString()
            }
        }

        btn.setOnClickListener({view ->
            val intent = Intent(this, QuestionPage::class.java)
            intent.putExtra("questions", questions)
            startActivity(intent)
            finish()
        })
    }
}
