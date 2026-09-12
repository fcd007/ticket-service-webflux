package com.dantasit.ticketservice.src.model.traducao;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class TraducaoDetextos {

    public static Mono<String> obterTraducao(String texto, String idioma) {
        WebClient webClient = WebClient.builder()
                .baseUrl(System.getenv("BASE_URI"))
                .build();

        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("text", texto);
        request.add("target_lang", idioma);

        return webClient.post()
                .header("Authorization", "DeepL-Auth-Key "
                        + System.getenv("DEEPLAUTH_KEY"))
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Traducao.class)
                .map(Traducao::getTexto);
    }
}
