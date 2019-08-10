package com.kotatanaka.goodsapi.domain.exception

import org.springframework.validation.FieldError

/**
 * バリデーションエラー例外
 *
 * @author kotatanaka
 */
class ValidationException(var errorList: List<FieldError>) : RuntimeException()
