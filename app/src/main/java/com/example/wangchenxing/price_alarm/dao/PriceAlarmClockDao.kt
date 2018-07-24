package com.example.wangchenxing.price_alarm.dao

import android.arch.persistence.room.*
import com.example.wangchenxing.price_alarm.bean.PriceAlarmClockBean
import java.util.ArrayList

/**
 * @data 07/23/2018 10/41
 * @author wcx
 * @description 价格闹钟提醒数据库操作接口
 */
@Dao
interface PriceAlarmClockDao {

  /**
   * @data 07/23/2018 10/41
   * @author wcx
   * @description 插入一个数据
   */
  @Insert
  fun insertPriceAlarmClock(user: PriceAlarmClockBean)

  /**
   * @data 07/23/2018 10/41
   * @author wcx
   * @description  插入多个数据
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertPriceAlarmClocks(arrayList: ArrayList<PriceAlarmClockBean>)

  /**
   * @data 07/23/2018 10/41
   * @author wcx
   * @description 删除数据
   */
  @Delete
  fun deletePriceAlarmClock(priceAlarmClockBean: PriceAlarmClockBean)

  /**
   * @data 07/23/2018 10/41
   * @author wcx
   * @description 删除全部数据
   */
  @Query("DELETE FROM price_alarm_clock")
  fun deleteAllPriceAlarmClock()

  /**
   * @data 07/23/2018 10/41
   * @author wcx
   * @description 查询全部数据
   */
  @Query("select * from price_alarm_clock")
  fun selectPriceAlarmClocks(): List<PriceAlarmClockBean>

  /**
   * @data 07/23/2018 10/41
   * @author wcx
   * @description 修改数据
   */
  @Update
  fun updatePriceAlarmClock(priceAlarmClockBean: PriceAlarmClockBean)

  /**
   * @data 07/23/2018 10/41
   * @author wcx
   * @description 修改多个数据
   */
  @Update
  fun updatePriceAlarmClocks(arrayList: ArrayList<PriceAlarmClockBean>)
}