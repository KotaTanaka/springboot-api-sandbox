package com.kotatanaka.goodsapi.domain.dto.request

import com.kotatanaka.goodsapi.validation.AlphaNumeric
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * ユーザー登録リクエストボディ
 *
 * @author kotatanaka
 */
data class LoginBody(
  @AlphaNumeric
  @field:NotBlank
  @field:Size(max = 16)
  var id: String?,

  @AlphaNumeric
  @field:NotBlank
  @field:Size(max = 8)
  var password: String?
)
