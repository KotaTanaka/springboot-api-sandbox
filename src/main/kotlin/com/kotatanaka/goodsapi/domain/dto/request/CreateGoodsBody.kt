package com.kotatanaka.goodsapi.domain.dto.request

import javax.validation.constraints.NotBlank

/**
 * 商品登録リクエストボディ
 *
 * @author kotatanaka
 */
data class CreateGoodsBody(
  @NotBlank
  var name: String,

  var description: String?,

  @NotBlank
  var price: Int
)
