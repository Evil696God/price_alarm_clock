package com.example.wangchenxing.price_alarm.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.wangchenxing.price_alarm.bean.PriceAlarmClockBean
import com.example.wangchenxing.price_alarm.dao.PriceAlarmClockDao

/**
 * @data 07/23/2018 10/41
 * @author wcx
 * @description 价格闹钟提醒数据库操作类
 */
@Database(entities = arrayOf(PriceAlarmClockBean::class), version = 1)
abstract class PriceAlarmClockDatabase : RoomDatabase() {

  abstract fun getPriceAlarmClockDao(): PriceAlarmClockDao

}