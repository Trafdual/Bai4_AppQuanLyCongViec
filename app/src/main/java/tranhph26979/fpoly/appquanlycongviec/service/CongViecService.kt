package tranhph26979.fpoly.appquanlycongviec.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import tranhph26979.fpoly.appquanlycongviec.ContentProvider.ContentProviderCV
import tranhph26979.fpoly.appquanlycongviec.R
import tranhph26979.fpoly.appquanlycongviec.model.CongViec

class CongViecService : Service() {
    private lateinit var contentProviderCV: ContentProviderCV
    private lateinit var mangcongviec: ArrayList<CongViec>
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        contentProviderCV = ContentProviderCV()
        mangcongviec = contentProviderCV.getTasksFromContentProvider(this)
            if (mangcongviec.isEmpty()) {
                showNotification("Không có công việc")
            } else {
                val congViec: CongViec = mangcongviec.get(0)
                showNotification(congViec.nameCV)
            }

        return super.onStartCommand(intent, flags, startId)
    }

    @SuppressLint("MissingPermission")
    private fun showNotification(content: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "channel_id",
                "Công việc",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notification = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notification.createNotificationChannel(channel)

            val builder = NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.ic_baseline_task_24)
                .setContentTitle("Công việc hôm nay")
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            val notificationManager = NotificationManagerCompat.from(this)
            notificationManager.notify(1, builder.build())
        }
    }
}