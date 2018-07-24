package com.example.wangchenxing.price_alarm.presenter

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import com.example.wangchenxing.price_alarm.model.PriceAlarmClockDatabase
import com.example.wangchenxing.price_alarm.model.PriceAlarmClockTable
import com.example.wangchenxing.price_alarm.model.PriceAlarmClockModule
import com.example.wangchenxing.price_alarm.ui.PriceAlarmClockFragment

/**
 * @data 07/23/2018 16/32
 * @author wcx
 * @description 价格闹钟Presenter实现类
 */
class PriceAlarmClockPresenter(private val alarmClockView: PriceAlarmClockFragment) {

  // 更新数据库版本
  val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
      database.execSQL("ALTER TABLE price_alarm_clock "
              + " ADD COLUMN onceFlag TEXT");
    }

  }
  private val priceAlarmClockDbHelper = Room.databaseBuilder(
          alarmClockView.getDbContext(),
          PriceAlarmClockDatabase::class.java,
          "price_alarm_clock")
          .build()

  private val alarmClockModule: PriceAlarmClockModule = PriceAlarmClockModule(
          this,
          priceAlarmClockDbHelper)

  fun addAlarmClock(priceAlarmClockBean: PriceAlarmClockTable) {
    alarmClockModule.addAlarmClock(priceAlarmClockBean)
  }

  fun watchAlarmClock() {
    alarmClockModule.watchAlarmClock()
  }

  fun modifyAlarmClock(position: Int, priceAlarmClockBean: PriceAlarmClockTable) {
    alarmClockModule.modifyAlarmClock(position, priceAlarmClockBean)
  }

  fun deleteAlarmClock(priceAlarmClockBean: PriceAlarmClockTable) {
    alarmClockModule.deleteAlarmClock(priceAlarmClockBean)
  }

  fun onWatchSuccess(dataArrayList: ArrayList<PriceAlarmClockTable>) {
    alarmClockView.updateUI(dataArrayList)
  }

  fun onWatchError() {
  }

  fun onAddSuccess(dataArrayList: ArrayList<PriceAlarmClockTable>) {
    alarmClockView.updateUI(dataArrayList)
  }

  fun onAddError() {

  }

  fun onModifySuccess(dataArrayList: ArrayList<PriceAlarmClockTable>) {
    alarmClockView.updateUI(dataArrayList)
  }

  fun onModifyError() {
  }

  fun onDeleteSuccess(dataArrayList: ArrayList<PriceAlarmClockTable>) {
    alarmClockView.updateUI(dataArrayList)
  }

  fun onDeleteError() {
  }
}