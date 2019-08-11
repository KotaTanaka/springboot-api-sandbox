package com.kotatanaka.goodsapi.domain.exception

/**
 * 認証失敗例外
 *
 * @author kotatanaka
 */
class AuthenticationException(override val message: String) : RuntimeException()
