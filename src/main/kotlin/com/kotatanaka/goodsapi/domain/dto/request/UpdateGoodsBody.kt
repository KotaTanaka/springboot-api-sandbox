package com.kotatanaka.goodsapi.domain.dto.request

import javax.validation.constraints.Size

/** 商品情報更新リクエストボディ */
data class UpdateGoodsBody(
  @field:Size(max = 50)
  var name: String?,

  @field:Size(max = 500)
  var description: String?,

  var price: Int?
)
