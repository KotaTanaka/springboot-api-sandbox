package com.kotatanaka.goodsapi.util

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

/** 日付のユーティリティ */
class DateTimeUtil {

  companion object {
    /**
     * 年月日時分秒形式にフォーマットする
     * @param value Timestamp
     * @return yyyy/MM/dd HH:mm:ss
     */
    fun toSimpleDateStr(value: Timestamp): String {
      return SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault()).format(value)
    }
  }
}
