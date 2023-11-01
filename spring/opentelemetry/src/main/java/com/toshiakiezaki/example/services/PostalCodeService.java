package com.toshiakiezaki.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toshiakiezaki.example.models.PostalCodeResponse;
import com.toshiakiezaki.example.repositories.PostalCodeRepository;

import reactor.core.publisher.Flux;

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

}
