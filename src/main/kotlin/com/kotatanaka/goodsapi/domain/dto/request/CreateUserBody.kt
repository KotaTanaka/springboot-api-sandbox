package com.kotatanaka.goodsapi.domain.dto.request

import com.kotatanaka.goodsapi.domain.validation.AlphaNumeric
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * ユーザー登録リクエストボディ
 *
 * @author kotatanaka
 */
data class CreateUserBody(
  @AlphaNumeric
  @field:NotBlank
  @field:Size(max = 16)
  var id: String?,

  @field:NotBlank
  @field:Size(max = 10)
  var name: String?,

  @AlphaNumeric
  @field:NotBlank
  @field:Size(max = 8)
  var password: String?
)
