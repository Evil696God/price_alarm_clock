package com.example.wangchenxing.price_alarm.utils

import android.content.Context
import android.util.TypedValue

/**
 * @data 07/23/2018 16/32
 * @author wcx
 * @description dp转化工具类
 */
object DensityUtils {
  /**
   * @data 07/24/2018 10/15
   * @author wcx
   * @description dp转px
   */
  fun dp2px(
          context: Context,
          dpVal: Float): Int {
    return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dpVal,
            context.resources.displayMetrics).toInt()
  }

  /**
   * @data 07/24/2018 10/15
   * @author wcx
   * @description sp转px
   */
  fun sp2px(
          context: Context,
          spVal: Float): Int {
    return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            spVal,
            context.resources.displayMetrics).toInt()
  }

  /**
   * @data 07/24/2018 10/15
   * @author wcx
   * @description px转dp
   */
  fun px2dp(
          context: Context,
          pxVal: Float): Float {
    val scale = context.resources.displayMetrics.density
    return pxVal / scale
  }

  /**
   * @data 07/24/2018 10/15
   * @author wcx
   * @description px转sp
   */
  fun px2sp(
          context: Context,
          pxVal: Float): Float {
    return pxVal / context.resources.displayMetrics.scaledDensity
  }
}