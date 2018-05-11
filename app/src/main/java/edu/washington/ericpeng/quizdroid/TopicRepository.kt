package edu.washington.ericpeng.quizdroid

import android.content.Context
import android.os.AsyncTask
import android.os.Environment
import android.util.JsonReader
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.nio.charset.Charset

class TopicRepository {

    val topics = HashMap<String, Topic>()
    //lateinit var url : String
    //private lateinit var context: Context

    constructor() {
        //this.url = "http://tednewardsandbox.site44.com/questions.json"
        //this.execute(this.url).get()
        readFile()
        Log.i("REPO", "Constructor")
    }

    constructor(context: Context){
        //this.context = context
        readFile()
    }

    fun readFile(){
        val dir : File = Environment.getExternalStorageDirectory()
        //val directory = context.filesDir
        val file = File(dir, "questions.json")

        var text : String = ""

        val fis : FileInputStream = FileInputStream(file)

        try{

            val fc : FileChannel = fis.channel
            val mb : MappedByteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size())

            text = Charset.defaultCharset().decode(mb).toString()
        }
        catch (e : Exception){
            e.printStackTrace()
        }
        finally {
            fis.close()
        }

        val json = JSONArray(text)

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
        //Log.i("Repo", json.toString())
    }


    /*override fun onPreExecute() {
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
    }*/
}
