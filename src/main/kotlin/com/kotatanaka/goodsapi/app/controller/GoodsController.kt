package com.kotatanaka.goodsapi.app.controller

import com.kotatanaka.goodsapi.domain.dto.request.CreateGoodsBody
import com.kotatanaka.goodsapi.domain.dto.request.SearchGoodsParam
import com.kotatanaka.goodsapi.domain.dto.request.UpdateGoodsBody
import com.kotatanaka.goodsapi.domain.dto.response.GoodsDetailResponse
import com.kotatanaka.goodsapi.domain.dto.response.GoodsIdResponse
import com.kotatanaka.goodsapi.domain.dto.response.GoodsListingResponse
import com.kotatanaka.goodsapi.domain.exception.ValidationException
import com.kotatanaka.goodsapi.domain.service.GoodsService
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 商品コントローラー
 *
 * @author kotatanaka
 */
@RestController
@RequestMapping("/app/goods")
class GoodsController(private val goodsService: GoodsService) {

  /**
   * 商品登録
   *
   * @param body リクエストボディ
   * @param result BindingResult
   * @return ResponseEntity
   */
  @PostMapping
  fun createGoods(
    @RequestBody @Validated body: CreateGoodsBody,
    result: BindingResult
  ): ResponseEntity<GoodsIdResponse> {
    if (result.hasErrors()) throw ValidationException(result.fieldErrors)
    val response = goodsService.create(body)
    return ResponseEntity.ok(GoodsIdResponse(response.id))
  }

  /**
   * 商品一覧取得
   *
   * @return ResponseEntity
   */
  @GetMapping
  fun getGoodsList(): ResponseEntity<GoodsListingResponse> {
    val response = goodsService.findAll()
    return ResponseEntity.ok(GoodsListingResponse(response))
  }

  /**
   * 商品名部分一致検索
   *
   * @param param リクエストパラメータ
   * @param result BindingResult
   * @return ResponseEntity
   */
  @GetMapping("/search")
  fun searchGoodsByName(
    @ModelAttribute @Validated param: SearchGoodsParam,
    result: BindingResult
  ): ResponseEntity<GoodsListingResponse> {
    if (result.hasErrors()) throw ValidationException(result.fieldErrors)
    val response = goodsService.findByName(param.key)
    return ResponseEntity.ok(GoodsListingResponse(response))
  }

  /**
   * 商品詳細取得
   *
   * @param id 商品ID(パスパラメータ)
   * @return ResponseEntity
   */
  @GetMapping("/{id:[0-9]{1,10}}")
  fun getGoodsDetail(@PathVariable id: Int): ResponseEntity<GoodsDetailResponse> {
    val response = goodsService.findById(id)
    return ResponseEntity.ok(GoodsDetailResponse(response))
  }

  /**
   * 商品情報更新
   *
   * @param id 商品ID(パスパラメータ)
   * @return ResponseEntity
   */
  @PutMapping("/{id:[0-9]{1,10}}")
  fun updateGoods(
    @PathVariable id: Int,
    @RequestBody @Validated body: UpdateGoodsBody,
    result: BindingResult
  ): ResponseEntity<GoodsIdResponse> {
    if (result.hasErrors()) throw ValidationException(result.fieldErrors)
    val response = goodsService.update(id, body)
    return ResponseEntity.ok(GoodsIdResponse(response.id))
  }

  /**
   * 商品削除
   *
   * @param id 商品ID(パスパラメータ)
   * @return ResponseEntity
   */
  @DeleteMapping("/{id:[0-9]{1,10}}")
  fun deleteGoods(@PathVariable id: Int): ResponseEntity<GoodsIdResponse> {
    goodsService.delete(id)
    return ResponseEntity.ok(GoodsIdResponse(id))
  }
}
