package com.kotatanaka.goodsapi.domain.dto.response

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError

/**
 * エラーレスポンス
 *
 * @author kotatanaka
 */
data class ErrorResponse(
  val code: Int,
  val message: String,
  val detailMessage: List<String>
) {

  companion object {

    /**
     * リクエスト形式不正エラー
     *
     * @param message エラーメッセージ
     * @return ResponseEntity
     */
    fun badRequestBody(message: String): ResponseEntity<ErrorResponse> {
      return ResponseEntity(
        ErrorResponse(HttpStatus.BAD_REQUEST.value(), message, emptyList()),
        HttpStatus.BAD_REQUEST
      )
    }

    /**
     * バリデーションエラー
     *
     * @param message エラーメッセージ
     * @param errorList エラーリスト
     * @return ResponseEntity
     */
    fun validationError(message: String, errorList: List<FieldError>): ResponseEntity<ErrorResponse> {
      return ResponseEntity(
        ErrorResponse(
          HttpStatus.BAD_REQUEST.value(),
          message,
          errorList.mapNotNull { fieldError ->
            fieldError.defaultMessage?.replace("{0}", fieldError.field)
          }
        ),
        HttpStatus.BAD_REQUEST
      )
    }

    /**
     * URLが存在しないエラー
     *
     * @param message エラーメッセージ
     * @return ResponseEntity
     */
    fun pathNotFound(message: String): ResponseEntity<ErrorResponse> {
      return ResponseEntity(
        ErrorResponse(HttpStatus.NOT_FOUND.value(), message, emptyList()),
        HttpStatus.NOT_FOUND
      )
    }

    /**
     * メソッド指定不可エラー
     *
     * @param message エラーメッセージ
     * @return ResponseEntity
     */
    fun methodNotAllowed(message: String): ResponseEntity<ErrorResponse> {
      return ResponseEntity(
        ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), message, emptyList()),
        HttpStatus.METHOD_NOT_ALLOWED
      )
    }

    /**
     * サーバーエラー
     *
     * @param message エラーメッセージ
     * @param detailMessage 詳細エラーメッセージ
     * @return ResponseEntity
     */
    fun internalServerError(message: String, detailMessage: String?): ResponseEntity<ErrorResponse> {
      return ResponseEntity(
        ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, listOfNotNull(detailMessage)),
        HttpStatus.INTERNAL_SERVER_ERROR
      )
    }
  }
}
