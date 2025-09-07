package com.toshiakiezaki.example.domain.ports;

import com.toshiakiezaki.example.domain.entities.PostalCode;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostalCodePort {

    Flux<PostalCode> findAll();

    Mono<PostalCode> findByCode(String code);

}
