package com.kotatanaka.goodsapi.domain.service

import com.kotatanaka.goodsapi.domain.dto.request.CreateUserBody
import com.kotatanaka.goodsapi.domain.dto.request.LoginBody
import com.kotatanaka.goodsapi.domain.entity.UserEntity
import com.kotatanaka.goodsapi.domain.enums.GoodsParams
import com.kotatanaka.goodsapi.domain.exception.AuthenticationException
import com.kotatanaka.goodsapi.domain.exception.ValidationException
import com.kotatanaka.goodsapi.domain.factory.MessageFactory
import com.kotatanaka.goodsapi.domain.repository.UserRepository
import com.kotatanaka.goodsapi.util.IdUtil
import org.springframework.stereotype.Service

/**
 * ユーザー操作サービス
 *
 * @author kotatanaka
 */
@Service
class UserService(
  private val userRepository: UserRepository,
  private val messageFactory: MessageFactory
) {

  /**
   * ユーザー登録サービス
   *
   * @param body CreateUserBody
   * @return 作成したUserEntity
   */
  fun create(body: CreateUserBody): UserEntity {
    val userId = body.id as String
    val existingUser = findById(userId)

    if (existingUser != null) {
      throw ValidationException(
        messageFactory.getValidationFieldErrorList(messageFactory.alreadyUsed(GoodsParams.USER_ID.key))
      )
    }

    val user = UserEntity(
      id = userId,
      name = body.name as String,
      password = body.password as String,
      loginToken = IdUtil.generateLoginToken()
    )
    return userRepository.save(user)
  }

  /**
   * ユーザー取得サービス
   *
   * @param id ユーザーID
   * @return UserEntity or Null
   */
  fun findById(id: String): UserEntity? {
    return userRepository.findById(id)
  }

  /**
   * ログインサービス
   *
   * @param body LoginBody
   * @return UserEntity
   */
  fun login(body: LoginBody): UserEntity {
    val user = findById(body.id as String)
      ?: throw AuthenticationException(messageFactory.targetNotFound(GoodsParams.USER.key))

    if (user.password != body.password) {
      throw AuthenticationException(messageFactory.invalidPassword())
    }

    user.loginToken = IdUtil.generateLoginToken()
    return userRepository.save(user)
  }

  /**
   * ログアウトサービス
   *
   * @param user UserEntity
   * @return ユーザーID
   */
  fun logout(user: UserEntity): String {
    user.loginToken = null
    userRepository.save(user)
    return user.id
  }

  /**
   * トークンチェックサービス
   *
   * @param token ログイン時に発行されたトークン
   * @return トークンに紐付くUserEntity
   */
  fun checkToken(token: String): UserEntity {
    return userRepository.findByLoginToken(token) ?: throw AuthenticationException(messageFactory.invalidToken())
  }
}
