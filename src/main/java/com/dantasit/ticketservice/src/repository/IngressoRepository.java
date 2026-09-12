package com.dantasit.ticketservice.src.repository;

import com.dantasit.ticketservice.src.model.ingresso.Ingresso;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IngressoRepository extends ReactiveCrudRepository<Ingresso, Long> {
}
