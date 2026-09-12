package com.dantasit.ticketservice.src.repository;

import com.dantasit.ticketservice.src.model.evento.Evento;
import com.dantasit.ticketservice.src.model.evento.TipoEvento;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EventoRepository extends ReactiveCrudRepository<Evento, Integer> {
    Flux<Evento> findByTipo(TipoEvento tipoEvento);
}
