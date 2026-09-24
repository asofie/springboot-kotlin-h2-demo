# Webutvikling øving 4

Spring Boot-prosjekt med Kotlin og H2-database.

## Database

Prosjektet bruker H2 som database og har to relaterte tabeller: 

### brukere
- id
- navn
- epost

### oppgaver
- id
- tekst
- ferdig 
- bruker_id

'bruker_id' er en fremmednøkkel som peker på 'id' i 'brukere'-tabellen.

Tabellene opprettes automatisk fra 'schema.sql' når applikasjonen starter.

## API

Applikasjonen har blant annet følgende API-endepunkter:
- 'GET /api/oppgaver' - henter alle oppgaver
- 'GET /api/oppgaver/{id}' - henter én oppgave
- 'POST /api/oppgaver' - oppretter en ny oppgave
- 'DELETE /api/oppgaver/{id}' - sletter en oppgave

## JOIN

Siden '/oppgaver' bruker en JOIN mellom 'oppgaver' og 'brukere' for å vise oppgaver sammen med navnet på brukeren.

## Kjøre prosjektet

Start applikasjonen med:

./gradlew bootRun

Applikasjonen kjører deretter på:

http://localhost:8080