package com.kotatanaka.goodsapi.validation.validator

import com.kotatanaka.goodsapi.validation.AlphaNumeric
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * 半角英数バリデータ
 *
 * @author kotatanaka
 */
class AlphaNumericValidator : ConstraintValidator<AlphaNumeric, String> {

  companion object {
    private val alphaNumericRegex = Regex("^\\p{Alnum}*$")
  }

  override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
    return if (value == null) true
    else alphaNumericRegex.matches(value)
  }
}
