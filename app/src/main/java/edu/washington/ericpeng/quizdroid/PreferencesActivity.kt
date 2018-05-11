package edu.washington.ericpeng.quizdroid

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class PreferencesActivity : AppCompatActivity() {

    private lateinit var url : EditText
    private lateinit var freq : EditText
    private lateinit var btn : Button

    private lateinit var int : Intent
    private lateinit var pIntent : PendingIntent

    private lateinit var uText : String
    private lateinit var fText : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        url = findViewById(R.id.editText2)
        freq = findViewById(R.id.editText)
        btn = findViewById(R.id.button)

        url.addTextChangedListener(tw)
        freq.addTextChangedListener(tw)

        btn.text = "Start"
        btn.isEnabled = false

        btn.setOnClickListener({
            int = Intent(applicationContext, Alarm::class.java)
            pIntent = PendingIntent.getBroadcast(this.applicationContext, 0, int, PendingIntent.FLAG_UPDATE_CURRENT)

            if (btn.text == "Stop"){
                btn.text = "Start"
                stopTimer()
            }

            else if (btn.text == "Start"){
                btn.text = "Stop"
                int.putExtra("url", uText)
                //int.putExtra("interval", pText)

                pIntent = PendingIntent.getBroadcast(this.applicationContext, 0, int, PendingIntent.FLAG_UPDATE_CURRENT)

                setAlarm()

            }
        })
    }

    private fun setAlarm(){
        val manager : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                SystemClock.elapsedRealtime(), 60000.times(fText.toLong()), pIntent)

    }

    private fun stopTimer() {
        val manager : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        manager.cancel(pIntent)
    }

    private var tw : TextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            uText = url.text.toString()
            fText = freq.text.toString()

            btn.isEnabled = !url.text.isNullOrEmpty() && !freq.text.isNullOrEmpty()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }
    }
}
