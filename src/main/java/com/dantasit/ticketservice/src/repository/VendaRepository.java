package com.dantasit.ticketservice.src.repository;

import com.dantasit.ticketservice.src.model.Venda.Venda;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface VendaRepository extends ReactiveCrudRepository<Venda, Long> {
}