package com.kotatanaka.goodsapi.domain.exception

import org.springframework.validation.FieldError

/** バリデーションエラー例外 */
class ValidationException(var errorList: List<FieldError>) : RuntimeException()
