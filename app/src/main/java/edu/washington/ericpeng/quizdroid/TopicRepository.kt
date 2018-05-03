package edu.washington.ericpeng.quizdroid

import android.os.AsyncTask
import android.util.JsonReader
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class TopicRepository : AsyncTask<String, String, String>{

    val topics = HashMap<String, Topic>()
    var url : String

    constructor() {
        this.url = "http://tednewardsandbox.site44.com/questions.json"
        this.execute(this.url).get()
        Log.i("REPO", "Constructor")
    }

    constructor(url: String) {
        this.url = url
        this.execute(this.url).get()
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: String?): String {
        Log.i("REPO", "CONNECTION")
        val connection = URL(url).openConnection() as HttpURLConnection

        var text : String = ""

        try {
            connection.connect()
            text = connection.inputStream.use { it.reader().use { reader -> reader.readText() } }
        }
        catch (e : IOException){
            e.printStackTrace()
        }
        catch (e : MalformedURLException){
            e.printStackTrace()
        }
        finally {
            connection.disconnect()
        }

        val json = JSONArray(text)
        //val it : Iterator = json.

        for (i in 0..(json.length() - 1)){
            val item = json.getJSONObject(i)

            val topic : String = item.getString("title")
            val description : String = item.getString("desc")

            val questions = item.getJSONArray("questions")

            val quiz : ArrayList<Question> = ArrayList()

            for (j in 0..(questions.length() - 1)){
                val thing = questions.getJSONObject(j)

                val q : String = thing.getString("text")
                val answer : Int = thing.getInt("answer")
                val choice = thing.getJSONArray("answers")

                val newQ = Question(q, choice.getString(0), choice.getString(1),
                        choice.getString(2), choice.getString(3), answer)

                quiz.add(newQ)
            }

            val t = Topic(quiz, topic, description, description)
            topics.put(topic, t)
        }

        return text
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        Log.i("Repo", "PostExecute")
    }
}
