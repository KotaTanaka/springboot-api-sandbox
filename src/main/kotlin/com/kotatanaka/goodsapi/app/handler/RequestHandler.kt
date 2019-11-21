package com.kotatanaka.goodsapi.app.handler

import com.kotatanaka.goodsapi.domain.enums.GoodsParams
import com.kotatanaka.goodsapi.factory.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

/**
 * リクエストパラメータをログ出力するためのハンドラー
 *
 * @author kotatanaka
 */
@RestControllerAdvice
class RequestHandler(private val objectMapper: ObjectMapper) {

  companion object {
    private val log = LoggerFactory.getLogger(RequestHandler::class.java)
  }

  @InitBinder
  fun getRequestParam(binder: WebDataBinder, request: HttpServletRequest) {
    val requestParamMap = if (binder.target != null) {
      objectMapper.toMap(binder.target as Any)
    } else null

    val requestId = request.getAttribute(GoodsParams.REQUEST_ID.key)
    log.info("[$requestId] Request Parameters: $requestParamMap")
  }
}
