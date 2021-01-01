package com.kotatanaka.goodsapi.app.filter

import com.kotatanaka.goodsapi.domain.enums.GoodsParams
import com.kotatanaka.goodsapi.domain.exception.AuthenticationException
import com.kotatanaka.goodsapi.domain.service.UserService
import com.kotatanaka.goodsapi.factory.MessageFactory
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/** トークンチェックインターセプター */
@Component
class CheckTokenInterceptor(
  private val userService: UserService,
  private val messageFactory: MessageFactory
) : HandlerInterceptor {

  override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
    // インターセプター対象外のAPI(excludePathPatternsに入れるとパスごとはじかれるため)
    val createUser = request.requestURI == "/app/user" && request.method == RequestMethod.POST.toString()
    if (createUser) {
      return super.preHandle(request, response, handler)
    }

    val token = request.getHeader("Authorization")
      ?: throw AuthenticationException(messageFactory.required(GoodsParams.AUTHORIZATION.label))
    val userEntity = userService.checkToken(token.replace("Bearer", "").trim())
    request.setAttribute(GoodsParams.USER.key, userEntity)
    return super.preHandle(request, response, handler)
  }
}
