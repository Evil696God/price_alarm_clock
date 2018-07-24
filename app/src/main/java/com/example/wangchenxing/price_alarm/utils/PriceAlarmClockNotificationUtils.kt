package com.example.wangchenxing.price_alarm.utils

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.support.v4.app.NotificationCompat
import com.example.wangchenxing.price_alarm.R
import android.app.NotificationChannel
import android.content.Context.NOTIFICATION_SERVICE
import android.graphics.Color


/**
 * @data 07/23/2018 20/26
 * @author wcx
 * @description 唤醒闹钟通知工具类
 */
object PriceAlarmClockNotificationUtils {

  @SuppressLint("WrongConstant")
  fun sendPriceAlarmClockNotification(
          context: Context,
          title: String,
          content: String,
          id: String?) {
    val manager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    val sendID: String? = id ?: "price_alarm_clock_01"
    val channel: NotificationChannel? // 创建Notification Channel对象
    // 如果版本号为8.0以上,定义Notification Channel
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
      channel = NotificationChannel(
              sendID,
              "price_alarm_clock",
              NotificationManager.IMPORTANCE_MAX) // 设置唯一的渠道通知Id
      channel.apply {
        enableLights(true)
        lightColor = Color.RED
        enableVibration(true) // 开启震动
        vibrationPattern = longArrayOf(1000, 1000, 1000, 1000) // 8.0以下版本的效果一样,都是震动
      }
      manager.createNotificationChannel(channel) // 在NotificationManager中注册渠道通知对象
    }
    // 定义通知,都可适配
    val notification = NotificationCompat.Builder(context, "1")
    notification.apply {
      mContentTitle = "" + title
      mContentText = "" + content
      setWhen(System.currentTimeMillis())
      setSmallIcon(R.drawable.ic_launcher_foreground)
      setStyle(NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(context.resources,
              R.drawable.ic_launcher_background)))
      setVibrate(longArrayOf(1000, 1000, 1000, 1000))
      setLights(Color.RED, 1000, 1000) // 震动和灯光一样都需要Notification Channel开启灯光和震动,才能有效果
      priority = NotificationCompat.PRIORITY_MAX // 悬浮通知
      setChannelId(sendID!!) // 设置通知Id
      setAutoCancel(true)
      setDefaults(Notification.DEFAULT_ALL)
    }
    manager.notify(1, notification.build())
  }
}