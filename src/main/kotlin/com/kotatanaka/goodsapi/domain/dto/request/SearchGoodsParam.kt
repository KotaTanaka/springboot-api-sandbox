package com.kotatanaka.goodsapi.domain.dto.request

import javax.validation.constraints.NotBlank

/**
 * 商品名部分一致検索リクエストパラメータ
 *
 * @author kotatanaka
 */
data class SearchGoodsParam(
  @NotBlank
  var key: String
)
