package com.example.wangchenxing.price_alarm.utils

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Bundle
import com.example.wangchenxing.price_alarm.receiver.PriceAlarmClockReceiver

/**
 * @data 07/23/2018 20/26
 * @author wcx
 * @description 唤醒闹钟工具类
 */
object PriceAlarmClockUtils {

  private var alarmManager: AlarmManager? = null
  private var pendingIntent: PendingIntent? = null
  private var intent: Intent? = null

  /**
   * @data 07/24/2018 10/02
   * @author wcx
   * @description 发送闹钟震动广播
   */
  fun sendAlarmReceiver(
          time: Long,
          context: Context,
          alarmId: Int) {
    alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
    intent = Intent(
            context,
            PriceAlarmClockReceiver::class.java)
    val bundle = Bundle()
    bundle.putBoolean(
            "msg",
            true)
    intent!!.putExtras(bundle)
    intent!!.action = "Alarm_clock"
    pendingIntent = PendingIntent.getBroadcast(
            context,
            alarmId,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT)
    alarmManager!!.set(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent)
  }

  /**
   * @data 07/24/2018 10/02
   * @author wcx
   * @description 停止闹钟震动广播
   */
  fun stopAlarmReceiver(
          context: Context,
          alarmId: Int) {
    alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
    intent = Intent(
            context,
            PriceAlarmClockReceiver::class.java)
    val bundle = Bundle()
    bundle.putBoolean(
            "msg",
            false)
    intent!!.putExtras(bundle)
    intent!!.action = "Alarm_clock"
    pendingIntent = PendingIntent.getBroadcast(
            context,
            alarmId,
            intent,
            0)
    if (pendingIntent != null && alarmManager != null) {
      alarmManager!!.cancel(pendingIntent)
      pendingIntent!!.cancel()
    }
  }
}