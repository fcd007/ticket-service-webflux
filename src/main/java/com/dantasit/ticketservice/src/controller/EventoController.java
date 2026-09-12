package com.dantasit.ticketservice.src.controller;

import com.dantasit.ticketservice.src.service.EventoService;
import com.dantasit.ticketservice.src.model.evento.EventoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;
    private final Sinks.Many<EventoDto> eventoSinck;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
        this.eventoSinck = Sinks.many().multicast().onBackpressureBuffer();
    }

    @GetMapping
    public Flux<EventoDto> listar() {
        return eventoService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<EventoDto> cadastrar(@RequestBody EventoDto eventoDto) {
        return eventoService.cadastrar(eventoDto)
                .doOnSuccess(eventoSinck::tryEmitNext);
    }

    @GetMapping(value = "/categoria/{tipo}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EventoDto> onterPorTipo(@PathVariable String tipo) {
        return Flux.merge(eventoService.obterPorTipo(tipo), eventoSinck.asFlux());
    }

    @GetMapping("/{id}")
    public Mono<EventoDto> obterPorId(@PathVariable Long id) {
        return eventoService.obterPorId(id);
    }

    @PutMapping("/{id}")
    public Mono<EventoDto> atualizar(@PathVariable Long id, @RequestBody EventoDto eventoDto) {
        return eventoService.atualizar(id, eventoDto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletar(@PathVariable Long id) {
        return eventoService.deletar(id);
    }

    @GetMapping("/{id}/traduzir/{idioma}")
    public Mono<String> obterTraducao(@PathVariable Long id, @PathVariable String idioma) {
        return eventoService.obterTraducao(id, idioma);
    }
}
