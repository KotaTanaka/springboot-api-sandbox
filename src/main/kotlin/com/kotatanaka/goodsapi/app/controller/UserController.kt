package com.kotatanaka.goodsapi.app.controller

import com.kotatanaka.goodsapi.domain.dto.request.CreateUserBody
import com.kotatanaka.goodsapi.domain.dto.request.LoginBody
import com.kotatanaka.goodsapi.domain.dto.response.LoginResponse
import com.kotatanaka.goodsapi.domain.dto.response.UserIdResponse
import com.kotatanaka.goodsapi.domain.entity.UserEntity
import com.kotatanaka.goodsapi.domain.enums.GoodsParams
import com.kotatanaka.goodsapi.domain.exception.ValidationException
import com.kotatanaka.goodsapi.domain.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

/** ユーザーコントローラー */
@RestController
@RequestMapping("/app/user")
class UserController(private val userService: UserService) {

  /**
   * ユーザー登録
   *
   * @param body リクエストボディ
   * @param result BindingResult
   * @return ResponseEntity
   */
  @PostMapping
  fun createUser(
    @RequestBody @Validated body: CreateUserBody,
    result: BindingResult
  ): ResponseEntity<LoginResponse> {
    if (result.hasErrors()) throw ValidationException(result.fieldErrors)
    val response = userService.create(body)
    return ResponseEntity.ok(LoginResponse(response.id, response.loginToken as String))
  }

  /**
   * ログイン
   *
   * @param body リクエストボディ
   * @param result BindingResult
   * @return ResponseEntity
   */
  @PutMapping("/login")
  fun login(
    @RequestBody @Validated body: LoginBody,
    result: BindingResult
  ): ResponseEntity<LoginResponse> {
    val response = userService.login(body)
    return ResponseEntity.ok(LoginResponse(response.id, response.loginToken as String))
  }

  /**
   * ログアウト
   *
   * @param request HttpServletRequest
   * @return ResponseEntity
   */
  @PutMapping("/logout")
  fun logout(request: HttpServletRequest): ResponseEntity<UserIdResponse> {
    val user = request.getAttribute(GoodsParams.USER.key) as UserEntity
    val userId = userService.logout(user)
    return ResponseEntity.ok(UserIdResponse(userId))
  }
}
