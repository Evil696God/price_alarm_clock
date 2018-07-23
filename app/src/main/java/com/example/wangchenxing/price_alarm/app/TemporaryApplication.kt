package com.example.wangchenxing.price_alarm.app

import android.app.Application
import com.example.wangchenxing.price_alarm.network.TemporaryThreadPoolExecutor
import java.util.concurrent.ThreadPoolExecutor

class TemporaryApplication : Application() {

  override fun onCreate() {
    super.onCreate()
  }

}