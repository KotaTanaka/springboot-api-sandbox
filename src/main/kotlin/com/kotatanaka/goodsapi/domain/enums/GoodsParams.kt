package com.kotatanaka.goodsapi.domain.enums

/**
 * パラメータ定数
 *
 * @author kotatanaka
 */
enum class GoodsParams(
  val key: String,
  val label: String
) {
  REQUEST_ID("requestId", "リクエストID"),
  GOODS("goods", "商品"),
  GOODS_ID("id", "商品ID")
}
