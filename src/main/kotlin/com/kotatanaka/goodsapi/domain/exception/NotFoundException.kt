package com.kotatanaka.goodsapi.domain.exception

/**
 * 対象が存在しない例外
 *
 * @author kotatanaka
 */
class NotFoundException(var target: String) : RuntimeException()
