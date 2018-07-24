package com.example.wangchenxing.price_alarm.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.wangchenxing.price_alarm.R
import com.example.wangchenxing.price_alarm.model.PriceAlarmClockTable
import com.example.wangchenxing.price_alarm.utils.DensityUtils
import org.jetbrains.anko.*

/**
 * @data 07/23/2018 16/32
 * @author wcx
 * @description 价格闹钟列表适配器
 */
class PriceAlarmClockAdapter(private val context: Context) : RecyclerView.Adapter<PriceAlarmClockAdapter.PriceAlarmClockRecyclerViewHolder>() {
  private var dataArrayList: ArrayList<PriceAlarmClockTable> = ArrayList()

  override fun onCreateViewHolder(
          parent: ViewGroup?,
          viewType: Int): PriceAlarmClockRecyclerViewHolder {

    val view = with(context) {
      linearLayout {
        lparams(
                width = matchParent,
                height = wrapContent) {
          margin = DensityUtils.dp2px(
                  context,
                  10f)
        }

        textView {
          id = R.id.tv_market
        }.lparams(
                width = 0,
                height = wrapContent,
                weight = 1f)
        textView {
          id = R.id.tv_currency
        }.lparams(
                width = 0,
                height = wrapContent,
                weight = 1f)
        textView {
          id = R.id.tv_price
        }.lparams(
                width = 0,
                height = wrapContent,
                weight = 1f)
        textView {
          id = R.id.tv_status
        }.lparams(
                width = 0,
                height = wrapContent,
                weight = 1f)
      }
    }
    val viewHolder = PriceAlarmClockRecyclerViewHolder(view)
    viewHolder.apply {
      tvMarket = view.find(R.id.tv_market)
      tvCurrency = view.find(R.id.tv_currency)
      tvPrice = view.find(R.id.tv_price)
      tvStatus = view.find(R.id.tv_status)
    }
    return viewHolder
  }

  override fun getItemCount(): Int {
    return dataArrayList.size
  }

  override fun onBindViewHolder(
          holder: PriceAlarmClockRecyclerViewHolder?,
          position: Int) {
    val priceAlarmClockBean = dataArrayList[position]
    holder?.apply {
      tvMarket?.text = priceAlarmClockBean.marketName
      tvCurrency?.text = priceAlarmClockBean.currencyName
      tvPrice?.text = priceAlarmClockBean.price
      tvStatus?.text = priceAlarmClockBean.status
    }
  }

  class PriceAlarmClockRecyclerViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    internal var tvMarket: TextView? = null
    internal var tvCurrency: TextView? = null
    internal var tvPrice: TextView? = null
    internal var tvStatus: TextView? = null
  }

  fun setData(dataArrayList: ArrayList<PriceAlarmClockTable>) {
    this.dataArrayList = dataArrayList
  }
}