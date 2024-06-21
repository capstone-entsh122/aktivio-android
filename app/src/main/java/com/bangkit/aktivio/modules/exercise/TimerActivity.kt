package com.bangkit.aktivio.modules.exercise

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.androchef.happytimer.countdowntimer.HappyTimer
import com.bangkit.aktivio.R
import com.bangkit.aktivio.databinding.ActivityTimerBinding

class TimerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var state = HappyTimer.State.RUNNING

        with(binding) {
            llControl.visibility = View.GONE
            circularCountDownView.initTimer(10)
            circularCountDownView.setOnTickListener(object : HappyTimer.OnTickListener {
                override fun onTick(completedSeconds: Int, remainingSeconds: Int) {

                }

                override fun onTimeUp() {
                    playSound()
                    showNotification()
                    finish()
                    startActivity(Intent(this@TimerActivity, FinishExerciseActivity::class.java))
                }

            })
            btnPlayPause.setOnClickListener {
                if(state == HappyTimer.State.RUNNING) {
                    circularCountDownView.pauseTimer()
                    ivPlayPause.setImageResource(R.drawable.round_play_arrow_24)
                    tvPlayPause.text = "Resume"
                    state = HappyTimer.State.PAUSED
                } else {
                    circularCountDownView.resumeTimer()
                    state = HappyTimer.State.RUNNING
                    ivPlayPause.setImageResource(R.drawable.round_pause_24)
                    tvPlayPause.text = "Pause"
                }
            }
            btnStart.setOnClickListener {
                circularCountDownView.startTimer()
                btnStart.visibility = View.GONE
                llControl.visibility = View.VISIBLE
            }
            btnStop.setOnClickListener {
                circularCountDownView.stopTimer()
                finish()
            }
        }
    }

    private fun playSound() {
        try {
            val notificationUri = Settings.System.DEFAULT_NOTIFICATION_URI
            val mediaPlayer = MediaPlayer().apply {
                setDataSource(this@TimerActivity, notificationUri)
                prepare()
                start()
            }
            mediaPlayer.setOnCompletionListener {
                it.release()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showNotification() {
        createNotificationChannel()

        val builder = NotificationCompat.Builder(this, "TIMER_CHANNEL_ID")
            .setSmallIcon(R.drawable.aktivio)
            .setContentTitle("Exercise Finished")
            .setContentText("Your exercise session has finished. Good job!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    this@TimerActivity,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notify(1001, builder.build()) // 1001 is the notification ID
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Timer Notification Channel"
            val descriptionText = "Channel for timer finish notifications"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("TIMER_CHANNEL_ID", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


}