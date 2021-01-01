package com.kotatanaka.goodsapi.util

import java.util.UUID

/** IDのユーティリティ */
class IdUtil {

  companion object {
    /**
     * UUIDを生成する
     * @return UUID
     */
    fun generateUUID(): String {
      return UUID.randomUUID().toString()
    }

    /**
     * ログイントークンを生成する
     * @return UUIDからハイフンを除いた文字列
     */
    fun generateLoginToken(): String {
      return generateUUID().replace("-", "")
    }
  }
}
