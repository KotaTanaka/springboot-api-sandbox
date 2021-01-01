package com.kotatanaka.goodsapi.app.filter

import com.kotatanaka.goodsapi.domain.enums.GoodsParams
import com.kotatanaka.goodsapi.util.IdUtil
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/** ログ出力フィルター */
@Component
class LoggingFilter : OncePerRequestFilter() {

  companion object {
    private val log = LoggerFactory.getLogger(LoggingFilter::class.java)
  }

  override fun doFilterInternal(
    request: HttpServletRequest,
    response: HttpServletResponse,
    filterChain: FilterChain
  ) {
    val requestId = IdUtil.generateUUID()
    val startTime = System.currentTimeMillis()
    preFilter(requestId, request)
    filterChain.doFilter(request, response)
    postFilter(requestId, startTime, request, response)
  }

  /**
   * リクエストの前処理
   *
   * @param requestId リクエストを識別するID
   * @param request HttpServletRequest
   */
  private fun preFilter(requestId: String, request: HttpServletRequest) {
    request.setAttribute(GoodsParams.REQUEST_ID.key, requestId)
    log.info("========== START REQUEST [$requestId] ==========")
    log.info("[$requestId] ${request.method} ${request.requestURI}")
    log.info("[$requestId] Remote IP: ${request.remoteAddr}")
  }

  /**
   * リクエストの後処理
   *
   * @param requestId リクエストを識別するID
   * @param startTime リクエスト開始時刻
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   */
  private fun postFilter(
    requestId: String,
    startTime: Long,
    request: HttpServletRequest,
    response: HttpServletResponse
  ) {
    log.info(
      "[{}] Response Status: {}, Request Method: {}, Request URI: {}, Response Time: {} ms",
      requestId,
      response.status,
      request.method,
      request.requestURI,
      System.currentTimeMillis() - startTime
    )
  }
}
