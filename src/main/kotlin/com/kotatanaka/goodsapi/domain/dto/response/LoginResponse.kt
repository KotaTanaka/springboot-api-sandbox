package com.kotatanaka.goodsapi.domain.dto.response

/** ログインレスポンス */
data class LoginResponse(
  val id: String,
  val loginToken: String
)
