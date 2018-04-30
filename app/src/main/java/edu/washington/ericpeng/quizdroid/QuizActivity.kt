package edu.washington.ericpeng.quizdroid

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class QuizActivity : AppCompatActivity(), TopicFragment.OnFragmentInteractionListener, QuestionFragment.OnFragmentInteractionListener,
AnswerFragment.OnFragmentInteractionListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val topic : String = intent.getStringExtra("topic")

        val fm = fragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.container, TopicFragment.newInstance(topic))
        ft.commit()

    }

    override fun onFragmentInteraction(uri: Uri) {

    }
}
