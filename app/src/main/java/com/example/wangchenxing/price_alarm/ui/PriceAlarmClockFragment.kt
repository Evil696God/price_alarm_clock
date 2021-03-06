package com.example.wangchenxing.price_alarm.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wangchenxing.price_alarm.R
import com.example.wangchenxing.price_alarm.model.PriceAlarmClockTable
import com.example.wangchenxing.price_alarm.presenter.PriceAlarmClockPresenter
import com.example.wangchenxing.price_alarm.receiver.PriceAlarmClockReceiver
import com.example.wangchenxing.price_alarm.ui.adapter.PriceAlarmClockAdapter
import com.example.wangchenxing.price_alarm.utils.DensityUtils
import com.example.wangchenxing.price_alarm.utils.PriceAlarmClockNotificationUtils
import com.example.wangchenxing.price_alarm.utils.PriceAlarmClockUtils
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.UI
import java.util.*

/**
 * @data 07/23/2018 16/13
 * @author wcx
 * @description 价格闹钟列表界面
 */
class PriceAlarmClockFragment : Fragment() {

  private lateinit var recyclerView: RecyclerView
  private lateinit var priceAlarmClockAdapter: PriceAlarmClockAdapter
  private lateinit var priceAlarmClockPresenter: PriceAlarmClockPresenter
  private var dataArrayList: ArrayList<PriceAlarmClockTable> = ArrayList()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    priceAlarmClockPresenter = PriceAlarmClockPresenter(this)
  }

  private var sum = 0
  override fun onCreateView(
          inflater: LayoutInflater,
          container: ViewGroup?,
          savedInstanceState: Bundle?): View? {
    return UI {
      relativeLayout {
        lparams(
                width = matchParent,
                height = matchParent)

        linearLayout {
          lparams(
                  width = matchParent,
                  height = wrapContent) {
            margin = DensityUtils.dp2px(
                    context,
                    10f)
          }
          id = R.id.ll_title

          textView {
            text = "市场"
          }.lparams(
                  width = 0,
                  height = wrapContent,
                  weight = 1f)
          textView {
            text = "币种"
          }.lparams(
                  width = 0,
                  height = wrapContent,
                  weight = 1f)
          textView {
            text = "价格"
          }.lparams(
                  width = 0,
                  height = wrapContent,
                  weight = 1f)
          textView {
            text = "状态"
          }.lparams(
                  width = 0,
                  height = wrapContent,
                  weight = 1f)
        }

        recyclerView = recyclerView {
          id = R.id.recyclerview
        }.lparams(
                width = matchParent,
                height = wrapContent) {
          above(R.id.ll_bottom)
          below(R.id.ll_title)
        }

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        priceAlarmClockAdapter = PriceAlarmClockAdapter(context)
        recyclerView.adapter = priceAlarmClockAdapter

        priceAlarmClockPresenter.watchAlarmClock()

        linearLayout {
          id = R.id.ll_bottom

          button {
            text = "增加"
            onClick {
              priceAlarmClockPresenter.addAlarmClock(PriceAlarmClockTable(
                      0,
                      "增加市场" + ++sum,
                      "增加币种$sum",
                      "增加价格$sum",
                      "增加状态$sum",
                      "增加上传状态$sum",
                      "增加单次状态$sum"))
            }
          }.lparams(width = 0, height = wrapContent, weight = 1f)
          button {
            text = "删除"
            onClick {
              if (dataArrayList.size != 0) {
                priceAlarmClockPresenter.deleteAlarmClock(dataArrayList[0])
              }
            }
          }.lparams(
                  width = 0,
                  height = wrapContent,
                  weight = 1f)
          button {
            text = "修改"
            onClick {
              priceAlarmClockPresenter.modifyAlarmClock(
                      1,
                      PriceAlarmClockTable(
                              0,
                              "修改市场",
                              "修改币种",
                              "修改价格",
                              "修改状态",
                              "修改上传状态",
                              "修改单次状态"))
            }
          }.lparams(
                  width = 0,
                  height = wrapContent,
                  weight = 1f)

          button {
            text = "闹钟"
            onClick {
              PriceAlarmClockUtils.sendAlarmReceiver(
                      0,
                      getContext(),
                      1)
              PriceAlarmClockNotificationUtils.sendPriceAlarmClockNotification(
                      getContext(),
                      "价格通知标题",
                      "价格通知内容",
                      null)

            }
          }.lparams(
                  width = 0,
                  height = wrapContent,
                  weight = 1f)

          button {
            text = "停止"
            onClick {
              PriceAlarmClockUtils.stopAlarmReceiver(getContext(), 1)
              PriceAlarmClockReceiver.stopAlarmClock()
            }
          }.lparams(
                  width = 0,
                  height = wrapContent,
                  weight = 1f)

        }.lparams(
                width = matchParent,
                height = wrapContent) {
          margin = DensityUtils.dp2px(
                  context,
                  10f)
          alignParentBottom()
        }
      }

    }.view
  }

  fun updateUI(dataArrayList: ArrayList<PriceAlarmClockTable>) {
    this.dataArrayList = dataArrayList
    priceAlarmClockAdapter.setData(dataArrayList)
    priceAlarmClockAdapter.notifyDataSetChanged()
  }

  fun getDbContext(): Context {
    return context
  }
}
