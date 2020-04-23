package com.kotatanaka.goodsapi.domain.service

import com.kotatanaka.goodsapi.domain.dto.request.CreateGoodsBody
import com.kotatanaka.goodsapi.domain.dto.request.UpdateGoodsBody
import com.kotatanaka.goodsapi.domain.entity.GoodsEntity
import com.kotatanaka.goodsapi.domain.exception.NotFoundException
import com.kotatanaka.goodsapi.domain.repository.GoodsRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import java.util.Optional

/**
 * [GoodsService] のテスト
 * @author tanakakota
 */
class GoodsServiceTest : ServiceTestBase() {

  @InjectMockKs
  private lateinit var goodsService: GoodsService

  @MockK
  private lateinit var goodsRepository: GoodsRepository

  companion object {
    val mockGoods1 = GoodsEntity(id = 0, name = "Goods1", price = 100)
    val mockGoods2 = GoodsEntity(id = 1, name = "Goods2", price = 200)
    val mockGoods3 = GoodsEntity(id = 2, name = "Goods3", price = 300)
  }

  @Before
  override fun setUp() {
    super.setUp()
  }

  @Test
  fun create_正常系() {
    every { goodsRepository.save(any<GoodsEntity>()) } returns mockGoods1

    val result = goodsService.create(CreateGoodsBody(mockGoods1.name, mockGoods1.description, mockGoods1.price))
    assertThat(result).isEqualTo(mockGoods1)

    verify(exactly = 1) { goodsRepository.save(any<GoodsEntity>()) }
    confirmVerified(goodsRepository)
  }

  @Test
  fun finAll_正常系() {
    every { goodsRepository.findAll() } returns listOf(mockGoods1, mockGoods2, mockGoods3)

    val result = goodsService.findAll()
    assertThat(result).isEqualTo(listOf(mockGoods1, mockGoods2, mockGoods3))

    verify(exactly = 1) { goodsRepository.findAll() }
    confirmVerified(goodsRepository)
  }

  @Test
  fun finById_正常系() {
    every { goodsRepository.findById(mockGoods1.id) } returns Optional.of(mockGoods1)

    val result = goodsService.findById(mockGoods1.id)
    assertThat(result).isEqualTo(mockGoods1)

    verify(exactly = 1) { goodsRepository.findById(mockGoods1.id) }
    confirmVerified(goodsRepository)
  }

  @Test(expected = NotFoundException::class)
  fun finById_異常系_商品が存在しない() {
    every { goodsRepository.findById(NOT_EXIST_ID) } returns Optional.empty()
    goodsService.findById(NOT_EXIST_ID)
  }

  @Test
  fun update_正常系() {
    every { goodsService.findById(mockGoods1.id) } returns mockGoods1
    every { goodsRepository.save(any<GoodsEntity>()) } returns mockGoods2

    val result = goodsService.update(
      mockGoods1.id,
      UpdateGoodsBody(mockGoods2.name, mockGoods2.description, mockGoods2.price)
    )
    assertThat(result).isEqualTo(mockGoods2)

    verify(exactly = 1) { goodsRepository.findById(mockGoods1.id) }
    verify(exactly = 1) { goodsRepository.save(any<GoodsEntity>()) }
    confirmVerified(goodsRepository)
  }

  @Test(expected = NotFoundException::class)
  fun update_異常系_商品が存在しない() {
    every { goodsRepository.findById(NOT_EXIST_ID) } returns Optional.empty()
    goodsService.update(
      NOT_EXIST_ID, UpdateGoodsBody(mockGoods1.name, null, null)
    )
  }

  @Test
  fun delete_正常系() {
    every { goodsService.findById(mockGoods1.id) } returns mockGoods1

    val result = goodsService.delete(mockGoods1.id)
    assertThat(result).isEqualTo(Unit)

    verify(exactly = 1) { goodsRepository.findById(mockGoods1.id) }
    verify(exactly = 1) { goodsRepository.delete(mockGoods1) }
    confirmVerified(goodsRepository)
  }

  @Test(expected = NotFoundException::class)
  fun delete_異常系_商品が存在しない() {
    every { goodsRepository.findById(NOT_EXIST_ID) } returns Optional.empty()
    goodsService.delete(NOT_EXIST_ID)
  }
}
