package com.kotatanaka.goodsapi.domain.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * ユーザー登録リクエストボディ
 *
 * @author kotatanaka
 */
data class CreateUserBody(
  @field:NotBlank
  @field:Size(max = 16)
  var id: String?,

  @field:NotBlank
  @field:Size(max = 10)
  var name: String?,

  @field:NotBlank
  @field:Size(max = 8)
  var password: String?
)
