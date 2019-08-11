package com.kotatanaka.goodsapi.domain.validation

import com.kotatanaka.goodsapi.domain.validation.validator.AlphaNumericValidator
import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.ReportAsSingleViolation
import kotlin.reflect.KClass

/**
 * 半角英数バリデーション アノテーション
 *
 * @author kotatanaka
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@ReportAsSingleViolation
@Constraint(validatedBy = [AlphaNumericValidator::class])
annotation class AlphaNumeric(
  val message: String = "{custom.validation.alphaNumeric}",
  val groups: Array<KClass<out Any>> = [],
  val payload: Array<KClass<out Payload>> = []
)
