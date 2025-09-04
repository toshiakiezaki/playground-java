package com.toshiakiezaki.example.domain.services;

import org.springframework.stereotype.Service;

import com.toshiakiezaki.example.domain.entities.PostalCode;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PostalCodeService {

	public Flux<PostalCode> findAll() {
		// TODO Auto-generated method stub
		return Flux.empty();
	}

	public Mono<PostalCode> findByCode(String code) {
		// TODO Auto-generated method stub
		return Mono.empty();
	}

}
