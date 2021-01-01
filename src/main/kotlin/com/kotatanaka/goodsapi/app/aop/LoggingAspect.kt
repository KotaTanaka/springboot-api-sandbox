package com.kotatanaka.goodsapi.app.aop

import com.kotatanaka.goodsapi.domain.enums.GoodsParams
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

/** ログ出力用AOPクラス */
@Aspect
@Component
class LoggingAspect(private val request: HttpServletRequest) {

  companion object {
    private val log = LoggerFactory.getLogger(LoggingAspect::class.java)
  }

  /**
   * JPAクエリ(Repositoryクラスの各種メソッド)が呼び出された時にログを出力する
   *
   * @param joinPoint JoinPoint
   */
  @Before("execution(* com.kotatanaka.goodsapi.domain.repository.*.*(..))")
  fun dataAccessStartLog(joinPoint: JoinPoint) {
    val requestId = request.getAttribute(GoodsParams.REQUEST_ID.key)
    log.info("[{}] ===== Execute SQL{} =====", requestId, sqlActionName(joinPoint.signature.name))
    log.info("[{}] JPA Query Method: {}", requestId, joinPoint.toShortString())
    log.info("[{}] JPA Query Arguments: {}", requestId, joinPoint.args)
  }

  /**
   * Jメソッド名からログに表示するアクション名を決定する
   *
   * @param methodName Repositoryクラスのメソッド名
   * @return SQLのアクション名
   */
  private fun sqlActionName(methodName: String): String {
    return when {
      methodName.contains("find") -> "<SELECT>"
      methodName.contains("save") -> "<INSERT / UPDATE>"
      methodName.contains("delete") -> "<DELETE>"
      methodName.contains("count") -> "<SELECT COUNT>"
      else -> ""
    }
  }
}
