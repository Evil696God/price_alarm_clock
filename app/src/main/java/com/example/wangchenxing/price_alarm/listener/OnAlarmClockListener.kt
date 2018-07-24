package com.example.wangchenxing.price_alarm.listener

import com.example.wangchenxing.price_alarm.bean.PriceAlarmClockTable

/**
 * @data 07/23/2018 16/22
 * @author wcx
 * @description 价格闹钟返回事件接口
 */
interface OnAlarmClockListener {
  fun onWatchSuccess(dataArrayList: ArrayList<PriceAlarmClockTable>)

  fun onWatchError()

  fun onAddSuccess(dataArrayList: ArrayList<PriceAlarmClockTable>)

  fun onAddError()

  fun onModifySuccess(dataArrayList: ArrayList<PriceAlarmClockTable>)

  fun onModifyError()

  fun onDeleteSuccess(dataArrayList: ArrayList<PriceAlarmClockTable>)

  fun onDeleteError()
}