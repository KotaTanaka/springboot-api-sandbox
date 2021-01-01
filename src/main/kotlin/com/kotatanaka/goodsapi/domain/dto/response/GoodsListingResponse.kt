package com.kotatanaka.goodsapi.domain.dto.response

import com.kotatanaka.goodsapi.domain.entity.GoodsEntity
import com.kotatanaka.goodsapi.util.DateTimeUtil

/** 商品一覧取得レスポンス */
data class GoodsListingResponse(
  val total: Int,
  val goodsList: List<GoodsListElement>
) {
  /** 商品一覧リスト要素 */
  data class GoodsListElement(
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val createdAt: String
  )

  /** Entityをレスポンス形式に変換するコンストラクタ */
  constructor(goodsEntityList: List<GoodsEntity>) : this(
    goodsEntityList.size,
    goodsEntityList.map { goodsEntity ->
      GoodsListElement(
        goodsEntity.id,
        goodsEntity.name,
        goodsEntity.description ?: "",
        goodsEntity.price,
        DateTimeUtil.toSimpleDateStr(goodsEntity.createdAt)
      )
    }
  )
}
