package com.kotatanaka.goodsapi.util

import java.util.UUID

/**
 * IDのユーティリティ
 *
 * @author kotatanaka
 */
class IdUtil {

  companion object {
    /**
     * UUIDを生成する
     * @return UUID
     */
    fun generateUUID(): String {
      return UUID.randomUUID().toString()
    }
  }
}
