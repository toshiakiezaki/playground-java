package com.toshiakiezaki.example.adapter.outbound.database.v1.handlers;

import java.util.UUID;

import com.toshiakiezaki.example.adapter.outbound.database.v1.entities.PostalCodeData;
import com.toshiakiezaki.example.domain.entities.PostalCode;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class PostalCodeRepository {

	private final PostalCodeDataRepository repository;

	public Mono<PostalCode> findByCode(String code) {
		return this.repository.findByCode(code).map(PostalCodeData::toEntity);
	}

}

@Repository
interface PostalCodeDataRepository extends R2dbcRepository<PostalCodeData, String> {

	Mono<PostalCodeData> findByCode(String code);

}
