package com.kotatanaka.goodsapi.app.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/** Hello World コントローラー */
@RestController
class HelloWorldController {
  @GetMapping("/hello")
  fun hello(): String {
    return "Hello, World!"
  }
}
