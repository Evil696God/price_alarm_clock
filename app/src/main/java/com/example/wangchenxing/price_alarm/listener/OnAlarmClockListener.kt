package com.example.wangchenxing.price_alarm.listener

import com.example.wangchenxing.price_alarm.bean.PriceAlarmClockBean
/**
 * @data 07/23/2018 16/22
 * @author wcx
 * @description 价格闹钟返回事件接口
 */
interface OnAlarmClockListener {
  fun onWatchSuccess(dataArrayList: ArrayList<PriceAlarmClockBean>)

  fun onWatchError()

  fun onAddSuccess(dataArrayList: ArrayList<PriceAlarmClockBean>)

  fun onAddError()

  fun onModifySuccess(dataArrayList: ArrayList<PriceAlarmClockBean>)

  fun onModifyError()

  fun onDeleteSuccess(dataArrayList: ArrayList<PriceAlarmClockBean>)

  fun onDeleteError()
}