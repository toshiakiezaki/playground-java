package com.toshiakiezaki.example.clients;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.toshiakiezaki.example.models.ViaCepResponse;

import reactor.core.publisher.Mono;

@Component
public class ViaCepClient {

    private final WebClient client;

    public ViaCepClient() {
        this.client = WebClient.create("https://viacep.com.br");
    }

    public Mono<ViaCepResponse> findByCodeAndType(String code, String type) {
        return client.get().uri(String.format("/ws/%s/%s", code, type)).accept(MediaType.APPLICATION_JSON).retrieve()
                .bodyToMono(ViaCepResponse.class);
    }

}
