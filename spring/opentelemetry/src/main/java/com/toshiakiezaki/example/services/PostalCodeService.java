package com.toshiakiezaki.example.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toshiakiezaki.example.models.PostalCodeResponse;
import com.toshiakiezaki.example.models.PostalCodeSearch;
import com.toshiakiezaki.example.repositories.PostalCodeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostalCodeService {

    private final PostalCodeRepository repository;

    @Autowired
    public PostalCodeService(PostalCodeRepository repository) {
        this.repository = repository;
    }

    public Flux<PostalCodeResponse> findAll() {
        return this.repository.findAll().map(PostalCodeResponse::from);
    }

    public Mono<PostalCodeResponse> findById(UUID id) {
        return this.repository.findById(id).map(PostalCodeResponse::from);
    }

    public Mono<PostalCodeResponse> search(PostalCodeSearch payload) {
        // TODO Implement viacep client search and database persistence based on its results
        return this.repository.findByCode(payload.getCode()).map(PostalCodeResponse::from).or(null);
    }

}
