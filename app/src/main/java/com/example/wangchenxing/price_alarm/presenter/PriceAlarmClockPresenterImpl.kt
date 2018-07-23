package com.example.wangchenxing.price_alarm.presenter

import android.arch.persistence.room.Room
import com.example.wangchenxing.price_alarm.bean.PriceAlarmClockBean
import com.example.wangchenxing.price_alarm.common.PriceAlarmClockPresenter
import com.example.wangchenxing.price_alarm.common.PriceAlarmClockView
import com.example.wangchenxing.price_alarm.db.PriceAlarmClockDatabase
import com.example.wangchenxing.price_alarm.listener.OnAlarmClockListener
import com.example.wangchenxing.price_alarm.model.PriceAlarmClockModuleImpl

/**
 * @data 07/23/2018 16/32
 * @author wcx
 * @description 价格闹钟Presenter实现类
 */
class PriceAlarmClockPresenterImpl(alarmClockView: PriceAlarmClockView) :
        PriceAlarmClockPresenter,
        OnAlarmClockListener {


  private val alarmClockView = alarmClockView
  val priceAlarmClockDbHelper = Room.databaseBuilder(
          alarmClockView.getDbContext(),
          PriceAlarmClockDatabase::class.java,
          "price_alarm_clock")
          .build()

  private val alarmClockModule: PriceAlarmClockModuleImpl = PriceAlarmClockModuleImpl(
          this,
          priceAlarmClockDbHelper)

  override fun addAlarmClock(priceAlarmClockBean: PriceAlarmClockBean) {
    alarmClockModule.addAlarmClock(priceAlarmClockBean)
  }

  override fun watchAlarmClock() {
    alarmClockModule.watchAlarmClock()
  }

  override fun modifyAlarmClock(position: Int, priceAlarmClockBean: PriceAlarmClockBean) {
    alarmClockModule.modifyAlarmClock(position, priceAlarmClockBean)
  }

  override fun deleteAlarmClock(priceAlarmClockBean: PriceAlarmClockBean) {
    alarmClockModule.deleteAlarmClock(priceAlarmClockBean)
  }

  override fun onWatchSuccess(dataArrayList: ArrayList<PriceAlarmClockBean>) {
    alarmClockView.updateUI(dataArrayList)
  }

  override fun onWatchError() {
  }

  override fun onAddSuccess(dataArrayList: ArrayList<PriceAlarmClockBean>) {
    alarmClockView.updateUI(dataArrayList)
  }

  override fun onAddError() {

  }

  override fun onModifySuccess(dataArrayList: ArrayList<PriceAlarmClockBean>) {
    alarmClockView.updateUI(dataArrayList)
  }

  override fun onModifyError() {
  }

  override fun onDeleteSuccess(dataArrayList: ArrayList<PriceAlarmClockBean>) {
    alarmClockView.updateUI(dataArrayList)
  }

  override fun onDeleteError() {
  }
}