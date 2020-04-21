package com.kotatanaka.goodsapi.app.controller

import com.kotatanaka.goodsapi.domain.dto.request.CreateGoodsBody
import com.kotatanaka.goodsapi.domain.dto.request.UpdateGoodsBody
import com.kotatanaka.goodsapi.domain.dto.response.GoodsDetailResponse
import com.kotatanaka.goodsapi.domain.dto.response.GoodsListingResponse
import com.kotatanaka.goodsapi.domain.entity.GoodsEntity
import com.kotatanaka.goodsapi.domain.service.GoodsService
import com.kotatanaka.goodsapi.factory.ObjectMapper
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * [GoodsController] のテスト
 * @author tanakakota
 */
class GoodsControllerTest : ControllerTestBase() {

  @InjectMockKs
  private lateinit var goodsController: GoodsController

  @MockK
  private lateinit var goodsService: GoodsService

  companion object {
    val mockGoods1 = GoodsEntity(id = 0, name = "Goods1", price = 100)
    val mockGoods2 = GoodsEntity(id = 1, name = "Goods2", price = 200)
    val mockGoods3 = GoodsEntity(id = 2, name = "Goods3", price = 300)
  }

  @Before
  override fun setUp() {
    super.setUp()
    mockMvc = setUpMockMvc(goodsController)
  }

  @Test
  fun createGoods_正常系() {
    val createGoodsBody = CreateGoodsBody(mockGoods1.name, mockGoods1.description, mockGoods1.price)

    every { goodsService.create(createGoodsBody) } returns mockGoods1

    val result = mockMvc
      .perform(post("/app/goods", ObjectMapper().toJsonString(createGoodsBody)))
      .andExpect(status().isOk)
      .andReturn()

    val responseBody = ObjectMapper().toMap(result.response.contentAsString)
    assertThat(responseBody).isEqualTo(mapOf("id" to mockGoods1.id))

    verify(exactly = 1) { goodsService.create(createGoodsBody) }
    confirmVerified(goodsService)
  }

  @Test
  fun getGoodsList_正常系() {
    every { goodsService.findAll() } returns listOf(mockGoods1, mockGoods2, mockGoods3)

    val result = mockMvc
      .perform(get("/app/goods"))
      .andExpect(status().isOk)
      .andReturn()

    val responseBody = ObjectMapper().toMap(result.response.contentAsString)
    assertThat(responseBody).isEqualTo(
      ObjectMapper().toMap(GoodsListingResponse(listOf(mockGoods1, mockGoods2, mockGoods3)))
    )

    verify(exactly = 1) { goodsService.findAll() }
    confirmVerified(goodsService)
  }

  @Test
  fun getGoodsDetail_正常系() {
    every { goodsService.findById(mockGoods1.id) } returns mockGoods1

    val result = mockMvc
      .perform(get("/app/goods/${mockGoods1.id}"))
      .andExpect(status().isOk)
      .andReturn()

    val responseBody = ObjectMapper().toMap(result.response.contentAsString)
    assertThat(responseBody).isEqualTo(
      ObjectMapper().toMap(GoodsDetailResponse(mockGoods1))
    )

    verify(exactly = 1) { goodsService.findById(mockGoods1.id) }
    confirmVerified(goodsService)
  }

  @Test
  fun updateGoods_正常系() {
    val updateGoodsBody = UpdateGoodsBody(mockGoods2.name, mockGoods2.description, mockGoods2.price)

    every { goodsService.update(mockGoods1.id, updateGoodsBody) } returns mockGoods2

    val result = mockMvc
      .perform(put("/app/goods/${mockGoods1.id}", ObjectMapper().toJsonString(updateGoodsBody)))
      .andExpect(status().isOk)
      .andReturn()

    val responseBody = ObjectMapper().toMap(result.response.contentAsString)
    assertThat(responseBody).isEqualTo(mapOf("id" to mockGoods2.id))

    verify(exactly = 1) { goodsService.update(mockGoods1.id, updateGoodsBody) }
    confirmVerified(goodsService)
  }

  @Test
  fun deleteGoods_正常系() {
    val result = mockMvc
      .perform(delete("/app/goods/${mockGoods1.id}"))
      .andExpect(status().isOk)
      .andReturn()

    val responseBody = ObjectMapper().toMap(result.response.contentAsString)
    assertThat(responseBody).isEqualTo(mapOf("id" to mockGoods1.id))

    verify(exactly = 1) { goodsService.delete(mockGoods1.id) }
    confirmVerified(goodsService)
  }
}
