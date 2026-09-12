package com.dantasit.ticketservice.src.model.evento;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Objects;

@Table("eventos")
public class Evento {

    @Id
    private Long id;

    private TipoEvento tipo;

    private String nome;

    private LocalDate data;

    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoEvento getTipo() {
        TipoEvento tipo = this.tipo;
        return tipo;
    }

    public void setTipo(TipoEvento tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(id, evento.id) && Objects.equals(tipo, evento.tipo) && Objects.equals(nome, evento.nome) && Objects.equals(data, evento.data) && Objects.equals(descricao, evento.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, nome, data, descricao);
    }
}
