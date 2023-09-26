package tranhph26979.fpoly.appquanlycongviec

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.*
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.core.content.getSystemService
import tranhph26979.fpoly.appquanlycongviec.Adapter.ConViecAdapter
import tranhph26979.fpoly.appquanlycongviec.ContentProvider.ContentProviderCV
import tranhph26979.fpoly.appquanlycongviec.database.DatabaseCongViec
import tranhph26979.fpoly.appquanlycongviec.model.CongViec
import tranhph26979.fpoly.appquanlycongviec.service.CongViecService
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var btnthem: Button
    private lateinit var listview: ListView
    private lateinit var contentProviderCV: ContentProviderCV
    private lateinit var congviecAdapter: ConViecAdapter

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnthem = findViewById(R.id.btnthemcvman1)
        contentProviderCV = ContentProviderCV()
        btnthem.setOnClickListener() {
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(intent)
        }
        val intentFilter = IntentFilter("com.example.myapp.provider.DATA_CHANGED")
        registerReceiver(dataChangedReceiver, intentFilter)
        listview=findViewById(R.id.listviewcv)
        val alarmManager:AlarmManager= getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent=Intent(this,CongViecService::class.java)
        val requestCode = 0
        val pendingIntent:PendingIntent=PendingIntent.getService(this,requestCode,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        val calendar:Calendar=Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, 6)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        updateTaskList()
    }
    private fun updateTaskList() {
        val congviec = contentProviderCV.getTasksFromContentProvider(this@MainActivity)
        congviecAdapter = ConViecAdapter(this@MainActivity, congviec)
        listview.adapter = congviecAdapter
    }

    private val dataChangedReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            updateTaskList()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(dataChangedReceiver)
    }
}



