package com.example.demo

import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository
import org.springframework.jdbc.support.GeneratedKeyHolder

data class Oppgave(val id: Int, val tekst: String, val ferdig: Boolean = false)

data class OppgaveMedBruker(val tekst: String, val ferdig: Boolean, val navn: String)

@Repository
class OppgaveRepository(private val jdbcClient: JdbcClient) {

    fun hentAlle(): List<Oppgave> =
        jdbcClient.sql("SELECT id, tekst, ferdig FROM oppgaver ORDER BY id")
            .query(Oppgave::class.java)
            .list()
            .filterNotNull()

    fun hentEn(id: Int): Oppgave? = 
        jdbcClient.sql("SELECT id, tekst, ferdig FROM oppgaver WHERE id = :id")
            .param("id", id)
            .query(Oppgave::class.java)
            .optional()
            .orElse(null)

    fun opprett(tekst: String): Oppgave {
        val keyHolder = GeneratedKeyHolder()
        jdbcClient
            .sql("INSERT INTO oppgaver (tekst, ferdig) VALUES (:tekst, :ferdig)")
            .param("tekst", tekst)
            .param("ferdig", false)
            .update(keyHolder)
        return Oppgave(id = keyHolder.key!!.toInt(), tekst = tekst)
    }
    
    fun merkFerdig(id: Int): Int =
    jdbcClient.sql("UPDATE oppgaver SET ferdig = TRUE WHERE id = :id")
        .param("id", id)
        .update()

    fun slett(id: Int): Int = 
        jdbcClient.sql("DELETE FROM oppgaver WHERE id = :id")
            .param("id", id)
            .update()

    fun hentMedBruker(): List <OppgaveMedBruker> = 
        jdbcClient.sql("""
            SELECT o.tekst, o.ferdig, b.navn
            FROM oppgaver o
            JOIN brukere b ON o.bruker_id = b.id
            ORDER BY b.navn, o.id
        """)
            .query(OppgaveMedBruker::class.java)
            .list()
            .filterNotNull()
}