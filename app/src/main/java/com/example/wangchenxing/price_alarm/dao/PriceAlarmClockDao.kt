package com.example.wangchenxing.price_alarm.dao

import android.arch.persistence.room.*
import com.example.wangchenxing.price_alarm.bean.PriceAlarmClockTable
import java.util.ArrayList

/**
 * @data 07/23/2018 10/41
 * @author wcx
 * @description 价格闹钟提醒数据库操作接口
 */
@Dao
interface PriceAlarmClockDao {

  @Insert
  fun insertPriceAlarmClock(user: PriceAlarmClockTable)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertPriceAlarmClocks(arrayList: ArrayList<PriceAlarmClockTable>)

  @Delete
  fun deletePriceAlarmClock(PriceAlarmClockModel: PriceAlarmClockTable)

  @Query("DELETE FROM price_alarm_clock")
  fun deleteAllPriceAlarmClock()

  @Query("select * from price_alarm_clock")
  fun selectPriceAlarmClocks(): List<PriceAlarmClockTable>

  @Update
  fun updatePriceAlarmClock(PriceAlarmClockModel: PriceAlarmClockTable)

  @Update
  fun updatePriceAlarmClocks(arrayList: ArrayList<PriceAlarmClockTable>)
}