package com.example.wangchenxing.price_alarm.common

import android.content.Context
import com.example.wangchenxing.price_alarm.bean.PriceAlarmClockTable

/**
 * @data 07/23/2018 16/20
 * @author wcx
 * @description 价格闹钟View层接口
 */
interface PriceAlarmClockView {
  //更新UI
  fun updateUI(dataArrayList: ArrayList<PriceAlarmClockTable>)

  //获取context
  fun getDbContext(): Context
}