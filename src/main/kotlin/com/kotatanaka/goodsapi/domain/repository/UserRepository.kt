package com.kotatanaka.goodsapi.domain.repository

import com.kotatanaka.goodsapi.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * [UserEntity] のリポジトリ
 *
 * @author kotatanaka
 */
@Repository
interface UserRepository : JpaRepository<UserEntity, Int>
