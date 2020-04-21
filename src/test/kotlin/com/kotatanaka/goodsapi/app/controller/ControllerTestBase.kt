package com.kotatanaka.goodsapi.app.controller

import io.mockk.MockKAnnotations
import org.junit.Before
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders

/**
 * コントローラーのユニットテスト基底クラス
 * @author tanakakota
 */
open class ControllerTestBase {

  lateinit var mockMvc: MockMvc

  @Before
  open fun setUp() {
    MockKAnnotations.init(this, relaxUnitFun = true)
  }

  fun setUpMockMvc(controller: Any): MockMvc {
    return MockMvcBuilders.standaloneSetup(controller).build()
  }

  /**
   * GETリクエスト
   * @param url URL
   * @return MockHttpServletRequestBuilder
   */
  fun get(url: String): MockHttpServletRequestBuilder {
    return MockMvcRequestBuilders.get(url)
  }

  /**
   * POSTリクエスト
   * @param url URL
   * @return MockHttpServletRequestBuilder
   */
  fun post(url: String, bodyStr: String): MockHttpServletRequestBuilder {
    return MockMvcRequestBuilders
      .post(url)
      .contentType(MediaType.APPLICATION_JSON)
      .content(bodyStr)
  }

  /**
   * PUTリクエスト
   * @param url URL
   * @return MockHttpServletRequestBuilder
   */
  fun put(url: String, bodyStr: String): MockHttpServletRequestBuilder {
    return MockMvcRequestBuilders
      .put(url)
      .contentType(MediaType.APPLICATION_JSON)
      .content(bodyStr)
  }

  /**
   * DELETEリクエスト
   * @param url URL
   * @return MockHttpServletRequestBuilder
   */
  fun delete(url: String): MockHttpServletRequestBuilder {
    return MockMvcRequestBuilders.delete(url)
  }
}
