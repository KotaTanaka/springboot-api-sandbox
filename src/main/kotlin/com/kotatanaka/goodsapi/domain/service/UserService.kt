package com.kotatanaka.goodsapi.domain.service

import com.kotatanaka.goodsapi.domain.dto.request.CreateUserBody
import com.kotatanaka.goodsapi.domain.entity.UserEntity
import com.kotatanaka.goodsapi.domain.repository.UserRepository
import com.kotatanaka.goodsapi.util.IdUtil
import org.springframework.stereotype.Service

/**
 * ユーザー操作サービス
 *
 * @author kotatanaka
 */
@Service
class UserService(private val userRepository: UserRepository) {

  /**
   * ユーザー登録サービス
   *
   * @param body CreateUserBody
   * @return 作成したGoodsEntity
   */
  fun create(body: CreateUserBody): UserEntity {
    val user = UserEntity(
      id = body.id as String,
      name = body.name as String,
      password = body.password as String,
      loginToken = IdUtil.generateLoginToken()
    )
    return userRepository.save(user)
  }
}
