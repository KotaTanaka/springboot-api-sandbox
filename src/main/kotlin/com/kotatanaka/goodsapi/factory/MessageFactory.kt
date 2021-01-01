package com.kotatanaka.goodsapi.factory

import org.springframework.context.MessageSource
import org.springframework.context.support.DefaultMessageSourceResolvable
import org.springframework.stereotype.Component
import org.springframework.validation.FieldError
import java.util.Locale

/** MessageSource からメッセージを生成する */
@Component
class MessageFactory(private val messageSource: MessageSource) {

  /** 「リクエスト形式が不正です。」 */
  fun badRequestBody(): String {
    return messageSource.getMessage(
      DefaultMessageSourceResolvable("error.badRequest.body"),
      Locale.getDefault()
    )
  }

  /** 「パラメータが不正です。」 */
  fun validationError(): String {
    return messageSource.getMessage(
      DefaultMessageSourceResolvable("error.badRequest.validation"),
      Locale.getDefault()
    )
  }

  /** 「URLが見つかりません。」 */
  fun pathNotFound(): String {
    return messageSource.getMessage(
      DefaultMessageSourceResolvable("error.notFound.path"),
      Locale.getDefault()
    )
  }

  /** 「{value}が見つかりません。」 */
  fun targetNotFound(value: String): String {
    return messageSource.getMessage("error.notFound.target", arrayOf(value), Locale.getDefault())
  }

  /** 「{value}は必須項目です。」 */
  fun required(value: String): String {
    return messageSource.getMessage(
      "javax.validation.constraints.NotNull.message",
      arrayOf(value),
      Locale.getDefault()
    )
  }

  /** 「{value}は既に使用されています。」 */
  fun alreadyUsed(value: String): String {
    return messageSource.getMessage("custom.validation.alreadyUsed", arrayOf(value), Locale.getDefault())
  }

  /** 「認証に失敗しました。」 */
  fun forbidden(): String {
    return messageSource.getMessage(
      DefaultMessageSourceResolvable("error.forbidden"),
      Locale.getDefault()
    )
  }

  /** 「パスワードが間違っています。」 */
  fun invalidPassword(): String {
    return messageSource.getMessage(
      DefaultMessageSourceResolvable("error.forbidden.password"),
      Locale.getDefault()
    )
  }

  /** 「トークンが不正です。」 */
  fun invalidToken(): String {
    return messageSource.getMessage(
      DefaultMessageSourceResolvable("error.forbidden.token"),
      Locale.getDefault()
    )
  }

  /** 「許可されていないメソッドです。」 */
  fun methodNotAllowed(): String {
    return messageSource.getMessage(
      DefaultMessageSourceResolvable("error.methodNotAllowed"),
      Locale.getDefault()
    )
  }

  /** 「サーバー内部で問題が発生しました。」 */
  fun internalServerError(): String {
    return messageSource.getMessage(
      DefaultMessageSourceResolvable("error.internalServerError"),
      Locale.getDefault()
    )
  }

  /**
   * FieldErrorのリストを作る
   * ValidationExceptionをBeanValidation以外で発生させる場合に用いる
   *
   * @param errorMessages エラーメッセージ(複数可能)
   * @return List<FieldError>
   */
  fun getValidationFieldErrorList(vararg errorMessages: String): List<FieldError> {
    return errorMessages.map { errorMessage ->
      FieldError("", "", errorMessage)
    }
  }
}
