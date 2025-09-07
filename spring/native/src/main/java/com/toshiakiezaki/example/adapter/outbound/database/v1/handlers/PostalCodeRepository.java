package com.toshiakiezaki.example.adapter.outbound.database.v1.handlers;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.toshiakiezaki.example.adapter.outbound.database.v1.entities.PostalCodeData;
import com.toshiakiezaki.example.domain.entities.PostalCode;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class PostalCodeRepository {

    private final PostalCodeManager manager;

    public Mono<Boolean> existsById(UUID id) {
        return manager.existsById(id);
    }

    public Mono<PostalCode> save(PostalCode postalCode) {
        var data = PostalCodeData.from(postalCode);
        return manager.save(data).map(PostalCodeData::toEntity);
    }

    public Flux<PostalCode> findAll() {
        return manager.findAll().map(PostalCodeData::toEntity);
    }

    public Mono<PostalCode> findByCode(String code) {
        return manager.findByCode(code).map(PostalCodeData::toEntity);
    }

}
