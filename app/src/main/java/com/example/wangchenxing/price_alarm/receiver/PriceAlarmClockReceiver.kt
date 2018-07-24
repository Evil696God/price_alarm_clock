package com.example.wangchenxing.price_alarm.receiver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Vibrator
import java.io.IOException

/**
 * @data 07/23/2018 20/26
 * @author wcx
 * @description 价格闹钟广播
 */
class PriceAlarmClockReceiver : BroadcastReceiver() {
  object PriceAlarmClockProperty {
    var mediaPlayer: MediaPlayer? = null
    var vibrator: Vibrator? = null
  }

  /**
   * @data 07/24/2018 10/05
   * @author wcx
   * @description 立即停止闹钟和震动
   */
  companion object {
    fun stopAlarmClock() {
      if (PriceAlarmClockProperty.mediaPlayer != null && PriceAlarmClockProperty.mediaPlayer!!.isPlaying) {
        PriceAlarmClockProperty.mediaPlayer!!.stop()
        PriceAlarmClockProperty.mediaPlayer!!.release()
        PriceAlarmClockProperty.mediaPlayer = null
      }

      if (PriceAlarmClockProperty.vibrator != null) {
        PriceAlarmClockProperty.vibrator!!.cancel()
      }
    }
  }

  override fun onReceive(
          context: Context?,
          intent: Intent?) {
    val bundle = intent!!.extras
    val flag = bundle.getBoolean("msg")
    if (flag) {
      startMediaPlayer(context!!)
      startVibrator(context)
    } else {
      stopMediaPlayer()
      stopVibrator()
      resumeMediaPlayer()
    }
  }

  /**
   * @data 07/24/2018 10/05
   * @author wcx
   * @description 初始化和启动MediaPlay
   */
  private fun startMediaPlayer(context: Context) {
    val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
    if (PriceAlarmClockProperty.mediaPlayer == null) {
      PriceAlarmClockProperty.mediaPlayer = MediaPlayer()
    }
    try {
      if (PriceAlarmClockProperty.mediaPlayer!!.isPlaying) {
        PriceAlarmClockProperty.mediaPlayer!!.stop()
        PriceAlarmClockProperty.mediaPlayer!!.release()
        PriceAlarmClockProperty.mediaPlayer = MediaPlayer()
      }

      PriceAlarmClockProperty.mediaPlayer!!.setDataSource(
              context,
              uri)
      PriceAlarmClockProperty.mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_RING)
      PriceAlarmClockProperty.mediaPlayer!!.isLooping = true
      PriceAlarmClockProperty.mediaPlayer!!.prepare()
      PriceAlarmClockProperty.mediaPlayer!!.start()
    } catch (e: IOException) {
      e.printStackTrace()
    }

  }

  /**
   * 停止MediaPlay
   */
  private fun stopMediaPlayer() {
    if (PriceAlarmClockProperty.mediaPlayer != null && PriceAlarmClockProperty.mediaPlayer!!.isPlaying) {
      PriceAlarmClockProperty.mediaPlayer!!.stop()
    }
  }

  /**
   * @data 07/24/2018 10/05
   * @author wcx
   * @description 释放mediaPlayer
   */
  private fun resumeMediaPlayer() {
    if (PriceAlarmClockProperty.mediaPlayer != null) {
      PriceAlarmClockProperty.mediaPlayer!!.release()
    }
  }

  /**
   * @data 07/24/2018 10/05
   * @author wcx
   * @description 开始震动
   */
  @SuppressLint("MissingPermission")
  private fun startVibrator(context: Context) {
    if (PriceAlarmClockProperty.vibrator == null) {
      PriceAlarmClockProperty.vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }
    val pattern = longArrayOf(
            800,
            1500,
            500,
            1300)
    if (PriceAlarmClockProperty.vibrator != null) {
      PriceAlarmClockProperty.vibrator!!.vibrate(
              pattern,
              2)
    }
  }

  /**
   * @data 07/24/2018 10/05
   * @author wcx
   * @description 停止震动
   */
  @SuppressLint("MissingPermission")
  private fun stopVibrator() {
    if (PriceAlarmClockProperty.vibrator != null) {
      PriceAlarmClockProperty.vibrator!!.cancel()
    }
  }
}