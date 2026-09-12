package com.dantasit.ticketservice.src.model.Venda;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("vendas")
public class Venda {
    @Id
    private Long id;
    private Long ingressoId;
    private int total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIngressoId() {
        return ingressoId;
    }

    public void setIngressoId(Long ingressoId) {
        this.ingressoId = ingressoId;
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
        Venda venda = (Venda) o;
        return total == venda.total && Objects.equals(id, venda.id) && Objects.equals(ingressoId, venda.ingressoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingressoId, total);
    }
}