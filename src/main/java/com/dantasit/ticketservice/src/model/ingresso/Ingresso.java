package com.dantasit.ticketservice.src.model.ingresso;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("ingressos")
public class Ingresso {
    @Id
    private Long id;
    private Long eventoId;
    private TipoIngresso tipo;
    private Double valor;
    private int total;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

    public TipoIngresso getTipo() {
        return tipo;
    }

    public void setTipo(TipoIngresso tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ingresso ingresso = (Ingresso) o;
        return total == ingresso.total && Objects.equals(id, ingresso.id) && Objects.equals(eventoId, ingresso.eventoId) && tipo == ingresso.tipo && Objects.equals(valor, ingresso.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventoId, tipo, valor, total);
    }
}