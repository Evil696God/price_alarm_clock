package com.example.wangchenxing.price_alarm.common

import com.example.wangchenxing.price_alarm.bean.PriceAlarmClockTable

/**
 * @data 07/23/2018 16/19
 * @author wcx
 * @description 价格闹钟Presenter接口
 */
interface PriceAlarmClockPresenter {
  // 添加闹钟提醒
  fun addAlarmClock(priceAlarmClockBean: PriceAlarmClockTable)

  // 查看闹钟提醒
  fun watchAlarmClock()

  // 修改闹钟提醒
  fun modifyAlarmClock(i: Int, priceAlarmClockBean: PriceAlarmClockTable)

  // 删除闹钟提醒
  fun deleteAlarmClock(priceAlarmClockBean: PriceAlarmClockTable)
}