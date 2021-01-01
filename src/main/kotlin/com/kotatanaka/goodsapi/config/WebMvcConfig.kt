package com.kotatanaka.goodsapi.config

import com.kotatanaka.goodsapi.app.filter.CheckTokenInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/** Spring MVC の設定 */
@Configuration
class WebMvcConfig(private val checkTokenInterceptor: CheckTokenInterceptor) : WebMvcConfigurer {

  /** インターセプイターを適用する設定 */
  override fun addInterceptors(registry: InterceptorRegistry) {
    registry
      .addInterceptor(checkTokenInterceptor)
      .addPathPatterns("/app/**")
      .excludePathPatterns("/app/user/login")
  }
}
