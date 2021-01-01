package com.kotatanaka.goodsapi.domain.exception

/** 対象が存在しない例外 */
class NotFoundException(var target: String) : RuntimeException()
