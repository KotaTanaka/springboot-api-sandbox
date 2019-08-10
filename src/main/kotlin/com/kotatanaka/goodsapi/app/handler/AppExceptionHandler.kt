package com.kotatanaka.goodsapi.app.handler

import com.kotatanaka.goodsapi.domain.dto.response.ErrorResponse
import com.kotatanaka.goodsapi.domain.exception.NotFoundException
import com.kotatanaka.goodsapi.domain.exception.ValidationException
import com.kotatanaka.goodsapi.domain.factory.MessageFactory
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException

/**
 * 例外ハンドラー
 *
 * @author kotatanaka
 */
@RestControllerAdvice
class AppExceptionHandler(private val messageFactory: MessageFactory) {

  companion object {
    private val log = LoggerFactory.getLogger(AppExceptionHandler::class.java)
  }

  /**
   * リクエスト形式不正 >> 400 Bad Request
   *
   * @param e HttpMessageNotReadableException
   * @return ResponseEntity
   */
  @ExceptionHandler(HttpMessageNotReadableException::class)
  fun handleHttpMessageNotReadableException(
    e: HttpMessageNotReadableException
  ): ResponseEntity<ErrorResponse> {
    val massage = messageFactory.badRequestBody()
    log.warn(massage)
    return ErrorResponse.badRequestBody(massage)
  }

  /**
   * バリデーションエラー >> 400 Bad Request
   *
   * @param e ValidationException
   * @return ResponseEntity
   */
  @ExceptionHandler(ValidationException::class)
  fun handleValidationException(
    e: ValidationException
  ): ResponseEntity<ErrorResponse> {
    val massage = messageFactory.validationError()
    log.warn(massage)
    return ErrorResponse.validationError(massage, e.errorList)
  }

  /**
   * 対象が見つからない >> 400 Bad Request
   *
   * @param e NotFoundException
   * @return ResponseEntity
   */
  @ExceptionHandler(NotFoundException::class)
  fun handleNotFoundException(
    e: NotFoundException
  ): ResponseEntity<ErrorResponse> {
    val detailMessage = messageFactory.targetNotFound(e.target)
    log.warn(detailMessage)
    return ErrorResponse.validationError(messageFactory.validationError(), detailMessage)
  }

  /**
   * URLが見つからない >> 404 Not Found
   *
   * @param e NoHandlerFoundException
   * @return ResponseEntity
   */
  @ExceptionHandler(NoHandlerFoundException::class)
  fun handleNoHandlerFoundException(
    e: NoHandlerFoundException
  ): ResponseEntity<ErrorResponse> {
    val massage = messageFactory.pathNotFound()
    log.warn(massage)
    return ErrorResponse.pathNotFound(massage)
  }

  /**
   * メソッド指定不可 >> 405 Method Not Allowed
   *
   * @param e HttpRequestMethodNotSupportedException
   * @return ResponseEntity
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
  fun handleHttpRequestMethodNotSupportedException(
    e: HttpRequestMethodNotSupportedException
  ): ResponseEntity<ErrorResponse> {
    val massage = messageFactory.methodNotAllowed()
    log.warn(massage)
    return ErrorResponse.methodNotAllowed(massage)
  }

  /**
   * その他の例外 >> 500 InternalServerError
   *
   * @param e Exception
   * @return ResponseEntity
   */
  @ExceptionHandler(Exception::class)
  fun handleOtherException(e: Exception): ResponseEntity<ErrorResponse> {
    val message = messageFactory.internalServerError()
    log.error("{} - {}", message, e.message, e)
    return ErrorResponse.internalServerError(message, e.message)
  }
}
