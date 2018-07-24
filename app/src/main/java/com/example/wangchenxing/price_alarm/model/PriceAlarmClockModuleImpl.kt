package com.example.wangchenxing.price_alarm.model

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import com.example.wangchenxing.price_alarm.bean.PriceAlarmClockBean
import com.example.wangchenxing.price_alarm.common.PriceAlarmClockModule
import com.example.wangchenxing.price_alarm.dao.PriceAlarmClockDao
import com.example.wangchenxing.price_alarm.db.PriceAlarmClockDatabase
import com.example.wangchenxing.price_alarm.listener.OnAlarmClockListener
import com.example.wangchenxing.price_alarm.network.TemporaryThreadPoolExecutor
import java.util.concurrent.ThreadPoolExecutor

/**
 * @data 07/23/2018 16/23
 * @author wcx
 * @description 价格闹钟Module实现类
 */
class PriceAlarmClockModuleImpl(
        onAlarmClockListener: OnAlarmClockListener,
        priceAlarmClockDbHelper: PriceAlarmClockDatabase) : PriceAlarmClockModule {
  val addAlarmClock = 1
  val watchAlarmClock = 2
  val modifyAlarmClock = 3
  val deleteAlarmClock = 4
  private val threadPoolExecutor: ThreadPoolExecutor = TemporaryThreadPoolExecutor.getThreadPoolExecutor()!!
  private val priceAlarmClockDao: PriceAlarmClockDao = priceAlarmClockDbHelper.getPriceAlarmClockDao()
  private var dataArrayList = ArrayList<PriceAlarmClockBean>()
  private val handler: Handler = @SuppressLint("HandlerLeak")
  object : Handler() {
    override fun handleMessage(msg: Message?) {
      super.handleMessage(msg)
      when (msg!!.what) {
        addAlarmClock -> {
          onAlarmClockListener.onAddSuccess(dataArrayList)
        }
        watchAlarmClock -> {
          onAlarmClockListener.onWatchSuccess(dataArrayList)
        }
        modifyAlarmClock -> {
          onAlarmClockListener.onModifySuccess(dataArrayList)
        }
        deleteAlarmClock -> {
          onAlarmClockListener.onDeleteSuccess(dataArrayList)
        }
      }
    }
  }

  override fun addAlarmClock(priceAlarmClockBean: PriceAlarmClockBean) {
    threadPoolExecutor.execute({
      run {
        priceAlarmClockDao.insertPriceAlarmClock(priceAlarmClockBean)
        selectPriceAlarmClocks()
        handler.sendEmptyMessage(addAlarmClock)
      }
    })
  }

  override fun watchAlarmClock() {
    threadPoolExecutor.execute({
      run {
        selectPriceAlarmClocks()

        handler.sendEmptyMessage(watchAlarmClock)
      }
    })
  }

  override fun modifyAlarmClock(position: Int, newPriceAlarmClockBean: PriceAlarmClockBean) {
    threadPoolExecutor.execute({
      run {
        if (dataArrayList.size != 0 && position < dataArrayList.size) {
          val priceAlarmClockBean = dataArrayList[position]
          priceAlarmClockBean.apply {
            marketName = newPriceAlarmClockBean.marketName
            currencyName = newPriceAlarmClockBean.currencyName
            price = newPriceAlarmClockBean.price
            status = newPriceAlarmClockBean.status
            uploadData = newPriceAlarmClockBean.uploadData
          }
          priceAlarmClockDao.updatePriceAlarmClock(priceAlarmClockBean)

          selectPriceAlarmClocks()
        }
        handler.sendEmptyMessage(modifyAlarmClock)
      }
    })
  }

  override fun deleteAlarmClock(priceAlarmClockBean: PriceAlarmClockBean) {
    threadPoolExecutor.execute({
      run {
        if (dataArrayList.size != 0) {
          priceAlarmClockDao.deletePriceAlarmClock(priceAlarmClockBean)
          selectPriceAlarmClocks()
        }
        handler.sendEmptyMessage(deleteAlarmClock)
      }
    })
  }

  private fun selectPriceAlarmClocks() {
    dataArrayList = priceAlarmClockDao.selectPriceAlarmClocks() as ArrayList<PriceAlarmClockBean>
  }

}