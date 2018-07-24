package com.example.wangchenxing.price_alarm.network

import java.util.concurrent.*
import java.util.concurrent.ThreadPoolExecutor

/**
 * @data 07/23/2018 16/30
 * @author wcx
 * @description 临时线程池工具类
 */
object TemporaryThreadPoolExecutor {
  private lateinit var threadPoolExecutor: ThreadPoolExecutor

  fun getThreadPoolExecutor(): ThreadPoolExecutor? {
    if (threadPoolExecutor.isShutdown || threadPoolExecutor.isTerminated) {
      val keepAliveTime: Long = 3000
      val unit = TimeUnit.MILLISECONDS
      val workQueue: BlockingQueue<Runnable> = LinkedBlockingDeque<Runnable>()
      val threadFactory = Executors.defaultThreadFactory()
      val handler: RejectedExecutionHandler = ThreadPoolExecutor.DiscardPolicy()
      threadPoolExecutor = ThreadPoolExecutor(
              5,
              5,
              keepAliveTime,
              unit,
              workQueue,
              threadFactory,
              handler)
    }
    return threadPoolExecutor
  }
}