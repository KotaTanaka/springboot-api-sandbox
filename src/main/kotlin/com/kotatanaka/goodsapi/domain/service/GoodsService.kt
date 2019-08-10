package com.kotatanaka.goodsapi.domain.service

import com.kotatanaka.goodsapi.domain.dto.request.CreateGoodsBody
import com.kotatanaka.goodsapi.domain.entity.GoodsEntity
import com.kotatanaka.goodsapi.domain.enums.GoodsParams
import com.kotatanaka.goodsapi.domain.exception.NotFoundException
import com.kotatanaka.goodsapi.domain.repository.GoodsRepository
import org.springframework.stereotype.Service

/**
 * 商品操作サービス
 *
 * @author kotatanaka
 */
@Service
class GoodsService(private val goodsRepository: GoodsRepository) {

  /**
   * 商品登録サービス
   *
   * @param body CreateGoodsBody
   * @return GoodsEntity
   */
  fun save(body: CreateGoodsBody): GoodsEntity {
    val goods = GoodsEntity(name = body.name, description = body.description, price = body.price)
    return goodsRepository.save(goods)
  }

  /**
   * 商品一覧取得サービス
   *
   * @return List<GoodsEntity>
   */
  fun findAll(): List<GoodsEntity> {
    return goodsRepository.findAll()
  }

  /**
   * 商品一件取得サービス
   *
   * @param id 商品ID
   * @return GoodsEntity
   */
  fun findById(id: Int): GoodsEntity {
    return goodsRepository.findById(id).orElseThrow { NotFoundException(GoodsParams.GOODS.label) }
  }
}
