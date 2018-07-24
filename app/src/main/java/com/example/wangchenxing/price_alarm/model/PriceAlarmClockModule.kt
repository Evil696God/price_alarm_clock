package com.example.wangchenxing.price_alarm.model

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import com.example.wangchenxing.price_alarm.network.TemporaryThreadPoolExecutor
import com.example.wangchenxing.price_alarm.presenter.PriceAlarmClockPresenter
import java.util.concurrent.ThreadPoolExecutor

/**
 * @data 07/23/2018 16/23
 * @author wcx
 * @description 价格闹钟Module实现类
 */
class PriceAlarmClockModule(
        priceAlarmClockPresenter: PriceAlarmClockPresenter,
        priceAlarmClockDbHelper: PriceAlarmClockDatabase) {
  val addAlarmClock = 1
  val watchAlarmClock = 2
  val modifyAlarmClock = 3
  val deleteAlarmClock = 4
  private val threadPoolExecutor: ThreadPoolExecutor = TemporaryThreadPoolExecutor.getThreadPoolExecutor()!!
  private val priceAlarmClockDao: PriceAlarmClockDao = priceAlarmClockDbHelper.getPriceAlarmClockDao()
  private var dataArrayList = ArrayList<PriceAlarmClockTable>()
  private val handler: Handler = @SuppressLint("HandlerLeak")
  object : Handler() {
    override fun handleMessage(msg: Message?) {
      super.handleMessage(msg)
      when (msg!!.what) {
        addAlarmClock -> {
          priceAlarmClockPresenter.onAddSuccess(dataArrayList)
        }
        watchAlarmClock -> {
          priceAlarmClockPresenter.onWatchSuccess(dataArrayList)
        }
        modifyAlarmClock -> {
          priceAlarmClockPresenter.onModifySuccess(dataArrayList)
        }
        deleteAlarmClock -> {
          priceAlarmClockPresenter.onDeleteSuccess(dataArrayList)
        }
      }
    }
  }

  fun addAlarmClock(PriceAlarmClockModel: PriceAlarmClockTable) {
    threadPoolExecutor.execute({
      run {
        priceAlarmClockDao.insertPriceAlarmClock(PriceAlarmClockModel)
        selectPriceAlarmClocks()
        handler.sendEmptyMessage(addAlarmClock)
      }
    })
  }

  fun watchAlarmClock() {
    threadPoolExecutor.execute({
      run {
        selectPriceAlarmClocks()

        handler.sendEmptyMessage(watchAlarmClock)
      }
    })
  }

  fun modifyAlarmClock(position: Int, newPriceAlarmClockModel: PriceAlarmClockTable) {
    threadPoolExecutor.execute({
      run {
        if (dataArrayList.size != 0 && position < dataArrayList.size) {
          val PriceAlarmClockModel = dataArrayList[position]
          PriceAlarmClockModel.apply {
            marketName = newPriceAlarmClockModel.marketName
            currencyName = newPriceAlarmClockModel.currencyName
            price = newPriceAlarmClockModel.price
            status = newPriceAlarmClockModel.status
            uploadData = newPriceAlarmClockModel.uploadData
          }
          priceAlarmClockDao.updatePriceAlarmClock(PriceAlarmClockModel)

          selectPriceAlarmClocks()
        }
        handler.sendEmptyMessage(modifyAlarmClock)
      }
    })
  }

  fun deleteAlarmClock(PriceAlarmClockModel: PriceAlarmClockTable) {
    threadPoolExecutor.execute({
      run {
        if (dataArrayList.size != 0) {
          priceAlarmClockDao.deletePriceAlarmClock(PriceAlarmClockModel)
          selectPriceAlarmClocks()
        }
        handler.sendEmptyMessage(deleteAlarmClock)
      }
    })
  }

  private fun selectPriceAlarmClocks() {
    dataArrayList = priceAlarmClockDao.selectPriceAlarmClocks() as ArrayList<PriceAlarmClockTable>
  }

}