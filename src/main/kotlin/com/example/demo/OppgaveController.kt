package com.example.demo 
  
import org.springframework.http.HttpStatus 
import org.springframework.http.ResponseEntity 
import org.springframework.web.bind.annotation.* 

data class NyOppgave(val tekst: String)

@RestController
@RequestMapping("/api/oppgaver")
class OppgaveController(private val repository: OppgaveRepository) {

    @GetMapping
    fun hentOppgaver(): List<Oppgave> = repository.hentAlle()

    @GetMapping("/{id}")
    fun hentOppgave(@PathVariable id: Int): ResponseEntity<Oppgave> {
        val oppgave = repository.hentEn(id)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        return ResponseEntity.ok(oppgave)
    }
    
    @PostMapping
    fun opprettOppgave(@RequestBody ny: NyOppgave): ResponseEntity<Oppgave> {
        val opprettet = repository.opprett(ny.tekst)
        return ResponseEntity.status(HttpStatus.CREATED).body(opprettet)
    }

    @DeleteMapping("/{id}")
    fun slettOppgave(@PathVariable id: Int): ResponseEntity<Map<String, String>> {
        if (repository.slett(id) == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
        return ResponseEntity.ok(mapOf("resultat" to "slettet"))
    }
}