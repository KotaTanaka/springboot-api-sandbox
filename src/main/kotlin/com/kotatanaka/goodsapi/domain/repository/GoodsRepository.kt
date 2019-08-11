package com.kotatanaka.goodsapi.domain.repository

import com.kotatanaka.goodsapi.domain.entity.GoodsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * [GoodsEntity] のリポジトリ
 *
 * @author kotatanaka
 */
@Repository
interface GoodsRepository : JpaRepository<GoodsEntity, Int> {
  fun findByNameContaining(key: String): List<GoodsEntity>
}
