package com.kotatanaka.goodsapi.factory

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Component
import java.io.IOException

/**
 * JSONデータ変換クラス
 *
 * @author kotatanaka
 */
@Component
class ObjectMapper {

  /**
   * JSON文字列をマップに変換する
   *
   * @param jsonStr JSON形式の文字列
   * @return Map<String, Any>
   */
  fun toMap(jsonStr: String): Map<String, Any> {
    try {
      return jacksonObjectMapper().readValue(jsonStr)
    } catch (e: IOException) {
      throw IllegalArgumentException(e)
    }
  }

  /**
   * オブジェクトをJSON文字列に変換する
   *
   * @param target オブジェクト
   * @return JSON形式の文字列
   */
  fun toJsonString(target: Any): String {
    try {
      return jacksonObjectMapper().writeValueAsString(target)
    } catch (e: JsonProcessingException) {
      throw IllegalArgumentException(e)
    }
  }

  /**
   * オブジェクトをマップに変換する
   *
   * @param target オブジェクト
   * @return Map<String, Any>
   */
  fun toMap(target: Any): Map<String, Any> {
    return toMap(toJsonString(target))
  }

  /**
   * JSON文字列をオブジェクトに変換する
   *
   * @param jsonStr JSON形式の文字列
   * @param dataClass Class<T>
   * @return T
   */
  fun <T> toModel(jsonStr: String, dataClass: Class<T>): T {
    try {
      return jacksonObjectMapper().readValue(jsonStr, dataClass)
    } catch (e: IOException) {
      throw IllegalArgumentException(e)
    }
  }
}
