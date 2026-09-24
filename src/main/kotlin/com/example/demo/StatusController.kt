package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StatusController {

    @GetMapping("/api/status")
    fun status(): Map<String, String> {
        return mapOf(
            "status" to "ok",
            "melding" to "API-et kjører som det skal"
        )
    }
}