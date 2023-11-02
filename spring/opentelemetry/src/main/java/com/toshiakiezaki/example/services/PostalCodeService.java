package com.toshiakiezaki.example.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toshiakiezaki.example.clients.ViaCepClient;
import com.toshiakiezaki.example.entities.PostalCode;
import com.toshiakiezaki.example.models.PostalCodeResponse;
import com.toshiakiezaki.example.models.PostalCodeSearch;
import com.toshiakiezaki.example.repositories.PostalCodeRepository;
import com.toshiakiezaki.example.utils.ULID;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.util.Objects.isNull;

import java.util.HashMap;
import java.util.Map;

@Service
public class PostalCodeService {

    private final Map<String, UUID> codes;

    private final PostalCodeRepository repository;

    private final ViaCepClient client;

    @Autowired
    public PostalCodeService(PostalCodeRepository repository, ViaCepClient client) {
        this.codes = new HashMap<>();
        this.repository = repository;
        this.client = client;
    }

    public Flux<PostalCodeResponse> findAll() {
        return this.repository.findAll().map(PostalCodeResponse::from);
    }

    public Mono<PostalCodeResponse> findById(UUID id) {
        return this.repository.findById(id).map(PostalCodeResponse::from);
    }

    public Mono<PostalCodeResponse> search(PostalCodeSearch payload) {
        return this.repository.findByCode(payload.getCode()).map(PostalCodeResponse::from)
                .switchIfEmpty(client.findByCodeAndType(payload.getCode(), "json").map(PostalCodeResponse::from)
                .doOnSuccess(postalCodeResponse -> {
                    if (isNull(postalCodeResponse.getId())) {
                        if (!codes.containsKey(postalCodeResponse.getCode())) {
                            codes.put(postalCodeResponse.getCode(), ULID.randomUUID());
                        }
                        postalCodeResponse.setId(codes.get(postalCodeResponse.getCode()));
                        this.repository.existsById(postalCodeResponse.getId()).doOnNext(exists -> {
                            if (!exists) {
                                var postalCode = PostalCode.builder()
                                        .id(postalCodeResponse.getId())
                                        .code(postalCodeResponse.getCode())
                                        .street(postalCodeResponse.getStreet())
                                        .neighborhood(postalCodeResponse.getNeighborhood())
                                        .city(postalCodeResponse.getCity())
                                        .state(postalCodeResponse.getState())
                                        .side(postalCodeResponse.getSide().orElse(null))
                                        .startRange(postalCodeResponse.getStartRange().orElse(null))
                                        .endRange(postalCodeResponse.getEndRange().orElse(null))
                                        .build();
                                this.repository.save(postalCode).doOnSuccess(entity -> {
                                    codes.remove(entity.getCode());
                                }).subscribe();
                            }
                        }).subscribe();
                    }
                }));
    }

}
