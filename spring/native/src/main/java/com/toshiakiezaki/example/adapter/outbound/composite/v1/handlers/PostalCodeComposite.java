package com.toshiakiezaki.example.adapter.outbound.composite.v1.handlers;

import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.toshiakiezaki.example.adapter.outbound.database.v1.handlers.PostalCodeRepository;
import com.toshiakiezaki.example.adapter.outbound.webclient.v1.handlers.ViaCepClient;
import com.toshiakiezaki.example.commons.utils.ULID;
import com.toshiakiezaki.example.domain.entities.PostalCode;
import com.toshiakiezaki.example.domain.ports.PostalCodePort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PostalCodeComposite implements PostalCodePort {

    private final Map<String, UUID> codes;

    private final PostalCodeRepository repository;

    private final ViaCepClient client;

    @Override
    public Flux<PostalCode> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<PostalCode> findByCode(String code) {
        return repository.findByCode(code).switchIfEmpty(client.findByCode(code).doOnSuccess(postalCode -> {
            if (!codes.containsKey(postalCode.getCode())) {
                codes.put(postalCode.getCode(), ULID.randomUUID());
            }
            postalCode.setId(codes.get(postalCode.getCode()));
            this.repository.existsById(postalCode.getId()).doOnNext(exists -> {
                if (!exists) {
                    this.repository.save(postalCode).doOnSuccess(result -> {
                        codes.remove(result.getCode());
                    }).subscribe();
                }
            }).subscribe();

        }));
    }
}
