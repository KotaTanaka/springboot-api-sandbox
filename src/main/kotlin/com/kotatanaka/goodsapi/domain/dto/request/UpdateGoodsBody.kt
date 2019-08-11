package com.kotatanaka.goodsapi.domain.dto.request

/**
 * 商品情報更新リクエストボディ
 *
 * @author kotatanaka
 */
data class UpdateGoodsBody(
  var name: String?,
  var description: String?,
  var price: Int?
)
