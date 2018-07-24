package com.example.wangchenxing.price_alarm.model

import android.arch.persistence.room.*
import java.util.ArrayList

/**
 * @data 07/23/2018 10/21
 * @author wcx
 * @description 价格闹钟提醒bean类
 */
@Entity(tableName = "price_alarm_clock")
data class PriceAlarmClockTable(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "_id")
        var id: Int = 0,
        @ColumnInfo(name = "market_name")
        var marketName: String?,
        @ColumnInfo(name = "currency_name")
        var currencyName: String?,
        @ColumnInfo(name = "price")
        var price: String?,
        @ColumnInfo(name = "status")
        var status: String?,
        @ColumnInfo(name = "uploadData")
        var uploadData: String?,
        @ColumnInfo(name = "onceFlag")
        var onceFlag: String?) {
  @Ignore
  constructor() : this(
          0,
          null,
          null,
          null,
          null,
          null,
          null)
}

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

/**
 * @data 07/23/2018 10/41
 * @author wcx
 * @description 价格闹钟提醒数据库操作类
 */
@Database(entities = arrayOf(PriceAlarmClockTable::class), version = 1)
abstract class PriceAlarmClockDatabase : RoomDatabase() {

  abstract fun getPriceAlarmClockDao(): PriceAlarmClockDao

}

