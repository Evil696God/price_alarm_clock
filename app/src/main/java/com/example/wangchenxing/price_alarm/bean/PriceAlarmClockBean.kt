package com.example.wangchenxing.price_alarm.bean

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

/**
 * @data 07/23/2018 10/21
 * @author wcx
 * @description 价格闹钟提醒bean类
 */
@Entity(tableName = "price_alarm_clock")
data class PriceAlarmClockBean constructor(
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
        var uploadData: String?) {
  @Ignore
  constructor() : this(0, null, null, null, null, null)

}