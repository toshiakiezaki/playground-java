package com.toshiakiezaki.example.domain.services;

import org.springframework.stereotype.Service;

import com.toshiakiezaki.example.domain.entities.PostalCode;
import com.toshiakiezaki.example.domain.ports.PostalCodePort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PostalCodeService {

	private final PostalCodePort repository;

	public Flux<PostalCode> findAll() {
		return repository.findAll();
	}

	public Mono<PostalCode> findByCode(String code) {
		return repository.findByCode(code);
	}

}
