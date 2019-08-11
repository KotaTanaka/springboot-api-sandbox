package com.kotatanaka.goodsapi.domain.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * 商品登録リクエストボディ
 *
 * @author kotatanaka
 */
data class CreateGoodsBody(
  @field:NotBlank
  @field:Size(max = 50)
  var name: String?,

  @field:Size(max = 500)
  var description: String?,

  @field:NotNull
  var price: Int?
)
