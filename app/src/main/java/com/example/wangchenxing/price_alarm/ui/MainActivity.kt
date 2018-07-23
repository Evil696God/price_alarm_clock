package com.example.wangchenxing.price_alarm.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.FrameLayout
import android.widget.TextView
import com.example.wangchenxing.price_alarm.R
import com.example.wangchenxing.price_alarm.common.PriceAlarmClockView
import org.jetbrains.anko.*
import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService

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
    SQLiteStudioService.instance().start(this);
  }

  override fun onDestroy() {
    super.onDestroy()
    SQLiteStudioService.instance().stop()
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
    // 加入回退栈
//    .addToBackStack(priceAlarmFragment.javaClass.getSimpleName())

//    relativeLayout {
//      lparams(width = wrapContent, height = wrapContent)
//
//      id = R.id.fl_main
//
//      val tv_test = textView {
//        text = "hello world11111"
//        id = R.id.tv_test
//
//      }.lparams {
//        centerInParent()
//      }
//
//      button {
//        text = "test11"
//        onClick {
//          tv_test.text = "Mvp Update UI" + +System.currentTimeMillis()
//        }
//      }.lparams {
//        alignParentBottom()
//        alignParentLeft()
//      }
//    }
  }
}


