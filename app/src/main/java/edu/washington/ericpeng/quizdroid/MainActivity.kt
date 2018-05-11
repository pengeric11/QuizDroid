package edu.washington.ericpeng.quizdroid

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.os.Build
import android.provider.Settings
import android.net.NetworkInfo
import android.net.ConnectivityManager



class MainActivity : AppCompatActivity() {

    private val options = arrayOf("Mathematics", "Physics", "Marvel Super Heroes")
    private lateinit var lv: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        lv = findViewById(R.id.listview)

        TopicRepository(this)

        val topics = QuizApp.create().getInstance().topics

        val list = ArrayList<String>(topics.keys)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        lv.adapter = adapter

        lv.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("topic", list.get(position))
            startActivity(intent)
        }

        if (airplaneMode()){
            val dialog = AlertDialog.Builder(this)

            dialog.setTitle("Airplane Mode")
            dialog.setMessage("You currently have airplane mode on so some features" +
                    " might not work as expected. Do you want to head over to settings" +
                    " to turn it off?")

            dialog.setPositiveButton("Yes"){_, _ ->
                val intent = Intent(android.provider.Settings.ACTION_AIRPLANE_MODE_SETTINGS)
                startActivity(intent)

            }

            dialog.setNegativeButton("No"){_, _ ->

            }

            val d : AlertDialog = dialog.create()

            d.show()
        }
        else if (checkConncetion()){
            val dialog = AlertDialog.Builder(this)

            dialog.setTitle("No Connection")

            dialog.setMessage("You currently have no internet connection so" +
                    " no new quiz files will be fetched...")

            dialog.setNeutralButton("Confirm"){_, _ ->}

            val d : AlertDialog = dialog.create()

            d.show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.Menu -> {
                val intent = Intent(this, PreferencesActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    @Suppress("DEPRECATED_IDENTITY_EQUALS")
    fun airplaneMode(): Boolean {
        return Settings.Global.getInt(this.contentResolver,
                    Settings.Global.AIRPLANE_MODE_ON, 0) !== 0
    }

    private fun checkConncetion(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
