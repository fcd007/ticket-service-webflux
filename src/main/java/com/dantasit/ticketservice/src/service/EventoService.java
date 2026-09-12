package com.dantasit.ticketservice.src.service;

import com.dantasit.ticketservice.src.model.evento.Evento;
import com.dantasit.ticketservice.src.model.evento.EventoDto;
import com.dantasit.ticketservice.src.model.evento.TipoEvento;
import com.dantasit.ticketservice.src.model.traducao.TraducaoDetextos;
import com.dantasit.ticketservice.src.repository.EventoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EventoService {

    private final EventoRepository eventosRepository;

    public EventoService(EventoRepository eventosRepository) {
        this.eventosRepository = eventosRepository;
    }

    public Flux<EventoDto> listar() {
        return eventosRepository.findAll()
                .map(EventoDto::toDto);
    }

    public Mono<EventoDto> obterPorId(Long id) {
        return eventosRepository.findById(id.intValue())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(EventoDto::toDto);
    }

    public Mono<Void> deletar(Long id) {
        return eventosRepository.findById(id.intValue())
                .flatMap(eventosRepository::delete);
    }

    public Mono<EventoDto> cadastrar(EventoDto eventoDto) {
        return eventosRepository.save(eventoDto.toEntity())
                .map(EventoDto::toDto);
    }

    public Mono<EventoDto> atualizar(Long id, EventoDto eventoDto) {
        return eventosRepository.findById(id.intValue())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .flatMap(evento -> {
                    Evento entity = eventoDto.toEntity();
                    entity.setId(evento.getId());
                    return eventosRepository.save(entity);
                })
                .map(EventoDto::toDto);
    }

    public Flux<EventoDto> obterPorTipo(String tipo) {
        TipoEvento tipoEvento = TipoEvento.valueOf(tipo.toUpperCase());

        return eventosRepository.findByTipo(tipoEvento)
                .map(EventoDto::toDto);
    }

    public Mono<String> obterTraducao(Long id, String idioma) {
        return eventosRepository.findById(id.intValue())
                .flatMap(e -> TraducaoDetextos.obterTraducao(e.getDescricao(), idioma));
    }
}
