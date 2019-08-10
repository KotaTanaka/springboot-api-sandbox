package com.kotatanaka.goodsapi.domain.service

import com.kotatanaka.goodsapi.domain.dto.request.CreateGoodsBody
import com.kotatanaka.goodsapi.domain.entity.GoodsEntity
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
}
