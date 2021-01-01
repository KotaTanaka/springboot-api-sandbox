package com.kotatanaka.goodsapi.domain.enums

/** パラメータ定数 */
enum class GoodsParams(
  val key: String,
  val label: String
) {
  REQUEST_ID("requestId", "リクエストID"),
  GOODS("goods", "商品"),
  GOODS_ID("id", "商品ID"),
  USER("user", "ユーザー"),
  USER_ID("id", "ユーザーID"),
  AUTHORIZATION("Authorization", "Authorization ヘッダ")
}
