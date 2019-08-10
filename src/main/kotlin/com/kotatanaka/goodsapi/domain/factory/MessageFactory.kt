package com.kotatanaka.goodsapi.domain.factory

import org.springframework.context.MessageSource
import org.springframework.context.support.DefaultMessageSourceResolvable
import org.springframework.stereotype.Component
import java.util.Locale

/**
 * MessageSource からメッセージを生成する
 *
 * @author kotatanaka
 */
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
}
