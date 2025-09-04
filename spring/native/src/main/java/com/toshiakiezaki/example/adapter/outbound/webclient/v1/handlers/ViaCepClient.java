package com.toshiakiezaki.example.adapter.outbound.webclient.v1.handlers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ViaCepClient {

	private final WebClient client;

	public ViaCepClient() {
		this.client = WebClient.create("https://viacep.com.br");
	}

}
