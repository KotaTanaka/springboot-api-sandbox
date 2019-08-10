package com.kotatanaka.goodsapi.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

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
}