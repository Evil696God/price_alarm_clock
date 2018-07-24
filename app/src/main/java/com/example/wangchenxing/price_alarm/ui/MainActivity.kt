package com.example.wangchenxing.price_alarm.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import android.widget.TextView
import com.example.wangchenxing.price_alarm.R
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.matchParent

/**
 * @data 07/23/2018 16/32
 * @author wcx
 * @description 临时主界面
 */
class MainActivity : AppCompatActivity() {
  var tvText: TextView? = null;
  var frameLayout: FrameLayout? = null;
  var priceAlarmFragment: PriceAlarmClockFragment? = null

  override
  fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    priceAlarmFragment = PriceAlarmClockFragment()
    initView()
  }

  override fun onDestroy() {
    super.onDestroy()
  }

  private fun initView() {
    frameLayout = frameLayout {
      id = R.id.fl_main
      lparams(width = matchParent,
              height = matchParent)
    }

    getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fl_main, priceAlarmFragment)
            .commit();
  }
}


