package com.dantasit.ticketservice.src.model.traducao;

import java.util.List;

public record Traducao(List<Texto> translations) {

    public String getTexto() {
        return translations.getFirst().text();
    }
}
