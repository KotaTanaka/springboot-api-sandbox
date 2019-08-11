package com.kotatanaka.goodsapi.domain.dto.response

/**
 * ログインレスポンス
 *
 * @author kotatanaka
 */
data class LoginResponse(
  val id: String,
  val loginToken: String
)
