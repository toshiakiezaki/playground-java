package com.toshiakiezaki.example.adapter.outbound.composite.v1.handlers;

import com.toshiakiezaki.example.adapter.outbound.database.v1.handlers.PostalCodeRepository;
import com.toshiakiezaki.example.adapter.outbound.webclient.v1.handlers.ViaCepClient;
import com.toshiakiezaki.example.domain.entities.PostalCode;
import com.toshiakiezaki.example.domain.ports.PostalCodePort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostalCodeComposite implements PostalCodePort {

    private final PostalCodeRepository repository;

    private final ViaCepClient client;

    @Override
    public Flux<PostalCode> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<PostalCode> findByCode(String code) {
        return repository.findByCode(code).switchIfEmpty(client.findByCode(code));
    }
}
