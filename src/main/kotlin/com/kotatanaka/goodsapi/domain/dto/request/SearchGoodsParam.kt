package com.kotatanaka.goodsapi.domain.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * 商品名部分一致検索リクエストパラメータ
 *
 * @author kotatanaka
 */
data class SearchGoodsParam(
  @field:NotBlank
  @field:Size(max = 50)
  var key: String?
)
