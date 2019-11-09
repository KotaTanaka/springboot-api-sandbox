package com.kotatanaka.goodsapi.config

import com.kotatanaka.goodsapi.app.filter.LoggingFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

/**
 * アプリケーションの汎用的な設定
 *
 * @author kotatanaka
 */
@Configuration
class AppConfig {

  /** MessageSource の設定 */
  @Bean
  fun messageSource(): MessageSource {
    val messageSource = ReloadableResourceBundleMessageSource()
    messageSource.setBasename("classpath:messages")
    messageSource.setDefaultEncoding("UTF-8")
    return messageSource
  }

  /** Bean Validation で MessageSource の文言を使えるようにする設定 */
  @Bean
  fun getValidator(): LocalValidatorFactoryBean {
    val bean = LocalValidatorFactoryBean()
    bean.setValidationMessageSource(messageSource())
    return bean
  }

  /** ログ出力フィルターの設定 */
  @Bean
  fun loggingFilter(): FilterRegistrationBean<*> {
    val bean = FilterRegistrationBean(LoggingFilter())
    bean.addUrlPatterns("/app/*")
    return bean
  }

  /** CORS の設定 */
  @Bean
  fun corsFilter(): FilterRegistrationBean<CorsFilter> {
    val source = UrlBasedCorsConfigurationSource()
    val config = CorsConfiguration()

    config.allowCredentials = true
    config.addAllowedOrigin(CorsConfiguration.ALL)
    config.addAllowedHeader(CorsConfiguration.ALL)
    config.addAllowedMethod(CorsConfiguration.ALL)

    source.registerCorsConfiguration("/**", config)
    val bean = FilterRegistrationBean(CorsFilter(source))
    bean.order = 0
    return bean
  }
}
