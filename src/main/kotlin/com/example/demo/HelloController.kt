package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
class HelloController (private val repository: OppgaveRepository) {
    @GetMapping("/")
    fun hjem(model: Model): String {
        model.addAttribute("navn", "Kari")
        model.addAttribute("antallBesok", 42)
        return "index"
    }

    @GetMapping("/oppgaver")
    fun visOppgaver(model: Model): String {
        val oppgaver = repository.hentMedBruker()
        
        model.addAttribute("oppgaver", oppgaver)
        return "oppgaver"
    }

    @GetMapping("/om")
    fun om(): String {
        return "meg"
    }
}

@RestController
class SideController {

    @GetMapping("/verktoy")
    fun verktoy(): String {
        return "Vi har hittil jobbet med Flask, HTML, CSS, Javascript, og skal nå lære oss Kotlin og Spring Boot."
    }

    @GetMapping("/bruker/{navn}")
    fun bruker(@PathVariable navn: String): String {
        return "Hei, $navn!"
    }

    @GetMapping("/produkt/{produktId}")
    fun produkt(@PathVariable produktId: Int): String {
        return "Du ser på produkt nummer $produktId"
    }
}