package com.toshiakiezaki.example.adapter.outbound.webclient.v1.handlers;

import com.toshiakiezaki.example.adapter.outbound.webclient.v1.entities.ViaCepData;
import com.toshiakiezaki.example.domain.entities.PostalCode;

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
@RegisterReflectionForBinding(ViaCepData.class)
public class ViaCepClient {

	private static final String DEFAULT_TYPE = "json";

	private final WebClient client;

	public ViaCepClient() {
		this.client = WebClient.create("https://viacep.com.br");
	}

	public Mono<PostalCode> findByCode(String code) {
		return client.get().uri(String.format("/ws/%s/%s", code, DEFAULT_TYPE)).accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToMono(ViaCepData.class).map(ViaCepData::toEntity);
	}

}
