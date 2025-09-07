package com.toshiakiezaki.example.adapter.outbound.database.v1.handlers;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.toshiakiezaki.example.adapter.outbound.database.v1.entities.PostalCodeData;

import reactor.core.publisher.Mono;

@Repository
interface PostalCodeManager extends R2dbcRepository<PostalCodeData, UUID> {

    Mono<PostalCodeData> findByCode(String code);

}
