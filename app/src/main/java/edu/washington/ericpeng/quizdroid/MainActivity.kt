package edu.washington.ericpeng.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private val options = arrayOf("Mathematics", "Physics", "Marvel Super Heroes")
    private lateinit var lv: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lv = findViewById(R.id.listview)

        val topics = QuizApp.create().getInstance().topics

        val list = ArrayList<String>(topics.keys)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        lv.adapter = adapter

        lv.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("topic", list.get(position))
            startActivity(intent)
        }

    }
}
