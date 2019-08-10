package com.kotatanaka.goodsapi.domain.dto.response

import com.kotatanaka.goodsapi.domain.entity.GoodsEntity
import com.kotatanaka.goodsapi.util.DateTimeUtil

/**
 * 商品詳細取得レスポンス
 *
 * @author kotatanaka
 */
data class GoodsDetailResponse(
  val id: Int,
  val name: String,
  val description: String,
  val price: Int,
  val createdAt: String,
  val updateAt: String
) {

  /** Entityをレスポンス形式に変換するコンストラクタ */
  constructor(goodsEntity: GoodsEntity) : this(
    goodsEntity.id,
    goodsEntity.name,
    goodsEntity.description ?: "",
    goodsEntity.price,
    DateTimeUtil.toSimpleDateStr(goodsEntity.createdAt),
    DateTimeUtil.toSimpleDateStr(goodsEntity.updatedAt)
  )
}
