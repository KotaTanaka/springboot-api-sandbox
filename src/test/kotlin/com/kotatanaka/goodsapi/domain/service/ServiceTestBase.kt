package com.kotatanaka.goodsapi.domain.service

import io.mockk.MockKAnnotations
import org.junit.Before

/**
 * サービスのユニットテスト基底クラス
 * @author tanakakota
 */
open class ServiceTestBase {
  @Before
  open fun setUp() {
    MockKAnnotations.init(this, relaxUnitFun = true)
  }
}
