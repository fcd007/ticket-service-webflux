package com.dantasit.ticketservice;

import com.dantasit.ticketservice.src.model.evento.EventoDto;
import com.dantasit.ticketservice.src.model.evento.TipoEvento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class TicketserviceApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void cadastrarEventoWithSuccess() {
        var dto = new EventoDto(null, TipoEvento.SHOW, "Laura Pausini",
                LocalDate.parse("2027-01-01"), "Melhor show do mundo que possa assistir");

        webTestClient.post().uri("/eventos")
                .bodyValue(dto)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(EventoDto.class)
                .value(response -> {
                    assertNotNull(response.id());
                    assertEquals(dto.tipo(), response.tipo());
                    assertEquals(dto.nome(), response.nome());
                    assertEquals(dto.data(), response.data());
                    assertEquals(dto.descricao(), response.descricao());
                });
    }

    @Test
    void buscarEventoByIdWithSuccess() {
        var dto = new EventoDto(13L, TipoEvento.SHOW,
                "The Weeknd", LocalDate.parse("2025-11-02"),
                "Um show eletrizante ao ar livre com muitos efeitos especiais.");

        webTestClient.get().uri("/eventos")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(EventoDto.class)
                .value(response -> {
                    var eventoResponse = response.get(12);
                    assertEquals(dto.id(), eventoResponse.id());
                    assertEquals(dto.tipo(), eventoResponse.tipo());
                    assertEquals(dto.nome(), eventoResponse.nome());
                    assertEquals(dto.data(), eventoResponse.data());
                    assertEquals(dto.descricao(), eventoResponse.descricao());
                });

    }

}
