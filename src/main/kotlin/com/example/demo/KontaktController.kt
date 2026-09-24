package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class KontaktController(private val kontaktRepository: KontaktRepository) {
    @GetMapping("/kontakt")
    fun visSkjema(): String {
        return "kontakt"
    }

    @PostMapping("/kontakt")
    fun mottaSkjema(
        @RequestParam navn: String,
        @RequestParam melding: String,
        model: Model
    ): String {
        kontaktRepository.lagre(navn, melding)
        return "redirect:/takk?navn=$navn"
    }

    @GetMapping("/takk")
    fun takk(
        @RequestParam(defaultValue = "der") navn: String,
        model : Model
    ): String {
        model.addAttribute("navn", navn)
        return "takk"
    }
}