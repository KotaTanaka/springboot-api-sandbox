package com.kotatanaka.goodsapi.app.handler

import com.kotatanaka.goodsapi.domain.dto.response.ErrorResponse
import com.kotatanaka.goodsapi.domain.enums.GoodsParams
import com.kotatanaka.goodsapi.domain.exception.AuthenticationException
import com.kotatanaka.goodsapi.domain.exception.NotFoundException
import com.kotatanaka.goodsapi.domain.exception.ValidationException
import com.kotatanaka.goodsapi.factory.MessageFactory
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException
import javax.servlet.http.HttpServletRequest

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
    e: HttpMessageNotReadableException,
    request: HttpServletRequest
  ): ResponseEntity<ErrorResponse> {
    val message = messageFactory.badRequestBody()
    log.warn("[${request.getAttribute(GoodsParams.REQUEST_ID.key)}] $message")
    return ErrorResponse.badRequestBody(message)
  }

  /**
   * バリデーションエラー >> 400 Bad Request
   *
   * @param e ValidationException
   * @return ResponseEntity
   */
  @ExceptionHandler(ValidationException::class)
  fun handleValidationException(
    e: ValidationException,
    request: HttpServletRequest
  ): ResponseEntity<ErrorResponse> {
    val message = messageFactory.validationError()
    log.warn("[${request.getAttribute(GoodsParams.REQUEST_ID.key)}] $message")
    return ErrorResponse.validationError(message, e.errorList)
  }

  /**
   * 対象が見つからない >> 400 Bad Request
   *
   * @param e NotFoundException
   * @return ResponseEntity
   */
  @ExceptionHandler(NotFoundException::class)
  fun handleNotFoundException(
    e: NotFoundException,
    request: HttpServletRequest
  ): ResponseEntity<ErrorResponse> {
    val detailMessage = messageFactory.targetNotFound(e.target)
    log.warn("[${request.getAttribute(GoodsParams.REQUEST_ID.key)}] $detailMessage")
    return ErrorResponse.validationError(messageFactory.validationError(), detailMessage)
  }

  /**
   * 認証失敗 >> 403 Forbidden
   *
   * @param e AuthenticationException
   * @return ResponseEntity
   */
  @ExceptionHandler(AuthenticationException::class)
  fun handleAuthenticationException(
    e: AuthenticationException,
    request: HttpServletRequest
  ): ResponseEntity<ErrorResponse> {
    log.warn("[${request.getAttribute(GoodsParams.REQUEST_ID.key)}] ${e.message}")
    return ErrorResponse.forbidden(messageFactory.forbidden(), e.message)
  }

  /**
   * URLが見つからない >> 404 Not Found
   *
   * @param e NoHandlerFoundException
   * @return ResponseEntity
   */
  @ExceptionHandler(NoHandlerFoundException::class)
  fun handleNoHandlerFoundException(
    e: NoHandlerFoundException,
    request: HttpServletRequest
  ): ResponseEntity<ErrorResponse> {
    val message = messageFactory.pathNotFound()
    log.warn("[${request.getAttribute(GoodsParams.REQUEST_ID.key)}] $message")
    return ErrorResponse.pathNotFound(message)
  }

  /**
   * メソッド指定不可 >> 405 Method Not Allowed
   *
   * @param e HttpRequestMethodNotSupportedException
   * @return ResponseEntity
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
  fun handleHttpRequestMethodNotSupportedException(
    e: HttpRequestMethodNotSupportedException,
    request: HttpServletRequest
  ): ResponseEntity<ErrorResponse> {
    val message = messageFactory.methodNotAllowed()
    log.warn("[${request.getAttribute(GoodsParams.REQUEST_ID.key)}] $message")
    return ErrorResponse.methodNotAllowed(message)
  }

  /**
   * その他の例外 >> 500 InternalServerError
   *
   * @param e Exception
   * @return ResponseEntity
   */
  @ExceptionHandler(Exception::class)
  fun handleOtherException(
    e: Exception,
    request: HttpServletRequest
  ): ResponseEntity<ErrorResponse> {
    val message = messageFactory.internalServerError()
    log.error("[{}] {} {}", request.getAttribute(GoodsParams.REQUEST_ID.key), message, e.message, e)
    return ErrorResponse.internalServerError(message, e.message)
  }
}
