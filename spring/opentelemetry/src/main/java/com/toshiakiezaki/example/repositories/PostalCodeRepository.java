package com.toshiakiezaki.example.repositories;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.toshiakiezaki.example.entities.PostalCode;

import reactor.core.publisher.Mono;

@Repository
public interface PostalCodeRepository extends R2dbcRepository<PostalCode, UUID> {    

    Mono<PostalCode> findByCode(String code);

}
