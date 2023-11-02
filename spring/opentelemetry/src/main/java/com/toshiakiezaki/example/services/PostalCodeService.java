package com.toshiakiezaki.example.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toshiakiezaki.example.clients.ViaCepClient;
import com.toshiakiezaki.example.models.PostalCodeResponse;
import com.toshiakiezaki.example.models.PostalCodeSearch;
import com.toshiakiezaki.example.repositories.PostalCodeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostalCodeService {

    private final PostalCodeRepository repository;

    private final ViaCepClient client;

    @Autowired
    public PostalCodeService(PostalCodeRepository repository, ViaCepClient client) {
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
        // TODO Implement viacep client search and database persistence based on its results
        return this.repository.findByCode(payload.getCode()).map(PostalCodeResponse::from)
                .switchIfEmpty(client.findByCodeAndType(payload.getCode(), "json").map(PostalCodeResponse::from));
    }
}
