package com.example.demo

import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository

@Repository
class KontaktRepository(private val jdbcClient: JdbcClient) {

    fun lagre(navn: String, melding: String) {
        jdbcClient.sql("""
            INSERT INTO kontaktmeldinger (navn, melding)
            VALUES (:navn, :melding)
        """)
            .param("navn", navn)
            .param("melding", melding)
            .update()
    }
}