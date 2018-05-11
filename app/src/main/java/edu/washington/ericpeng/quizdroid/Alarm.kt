package edu.washington.ericpeng.quizdroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import org.json.JSONArray
import java.io.*
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class Alarm : BroadcastReceiver() {

    private lateinit var url : String

    override fun onReceive(context: Context, intent: Intent) {
        url = intent.getStringExtra("url")
        HttpRequest(context).execute(url).get()
        Toast.makeText(context, "Downloading From: " + url, Toast.LENGTH_SHORT).show()
    }

    class HttpRequest(context: Context) : AsyncTask<String, String, String>() {

        private val context = context

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String {
            val connection = URL(params[0]).openConnection() as HttpURLConnection

            var text : String = ""

            try {
                connection.connect()
                text = connection.inputStream.use { it.reader().use { reader -> reader.readText() } }
            }
            catch (e : IOException){
                Toast.makeText(context, "Download Failed...", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
            catch (e : ConnectException){
                Toast.makeText(context, "Download Failed...", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
            catch (e : MalformedURLException){
                Toast.makeText(context, "Download Failed...", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
            catch (e : InterruptedIOException){
                Toast.makeText(context, "Download Failed...", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
            catch (e : Exception){
                Toast.makeText(context, "Download Failed...", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
            finally {
                connection.disconnect()
            }

            val json = JSONArray(text)

            Log.i("JSON", json.toString())

            val root : File = android.os.Environment.getExternalStorageDirectory()
            val file : File = File(root.absolutePath)
            file.mkdirs()

            val jsonFile : File = File(file, "questions.json")

            try {
                val fos : FileOutputStream = FileOutputStream(jsonFile)
                val pw : PrintWriter = PrintWriter(fos)

                pw.print(json)

                pw.flush()
                pw.close()
                fos.close()
            }
            catch (e : FileNotFoundException){
                Toast.makeText(context, "Download Failed...", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
            catch (e : IOException){
                Toast.makeText(context, "Download Failed...", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
            catch (e : Exception){
                Toast.makeText(context, "Download Failed...", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }

            Log.i("File", "File Written")

            return text
        }

        override fun onPostExecute(result: String?) {
            Toast.makeText(context, "Finished Downloading", Toast.LENGTH_SHORT).show()
            super.onPostExecute(result)
        }
    }
}