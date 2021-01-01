package com.kotatanaka.goodsapi.domain.exception

/** 認証失敗例外 */
class AuthenticationException(override val message: String) : RuntimeException()
