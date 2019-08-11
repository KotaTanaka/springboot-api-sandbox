package com.kotatanaka.goodsapi.domain.service

import com.kotatanaka.goodsapi.domain.dto.request.CreateGoodsBody
import com.kotatanaka.goodsapi.domain.dto.request.UpdateGoodsBody
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
   * @return 作成したGoodsEntity
   */
  fun create(body: CreateGoodsBody): GoodsEntity {
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
   * 商品名部分一致検索サービス
   *
   * @param key 検索文字列
   * @return List<GoodsEntity>
   */
  fun findByName(key: String): List<GoodsEntity> {
    return goodsRepository.findByNameContaining(key)
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

  /**
   * 商品更新サービス
   *
   * @param id 商品ID
   * @return 更新後のGoodsEntity
   */
  fun update(id: Int, body: UpdateGoodsBody): GoodsEntity {
    val targetGoods = findById(id)
    targetGoods.name = body.name ?: targetGoods.name
    targetGoods.description = body.description ?: targetGoods.description
    targetGoods.price = body.price ?: targetGoods.price
    return goodsRepository.save(targetGoods)
  }

  /**
   * 商品削除サービス
   *
   * @param id 商品ID
   */
  fun delete(id: Int) {
    val targetGoods = findById(id)
    goodsRepository.delete(targetGoods)
  }
}
