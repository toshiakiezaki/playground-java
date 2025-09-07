package com.toshiakiezaki.example.adapter.inbound.webflux.v1.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.toshiakiezaki.example.adapter.inbound.webflux.v1.entities.PostalCodeResponse;
import com.toshiakiezaki.example.domain.services.PostalCodeService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/postal-codes")
public class PostalCodeController {

    private final PostalCodeService service;

    @RequestMapping(path = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Flux<PostalCodeResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.service.findAll().map(PostalCodeResponse::from));
    }

    @RequestMapping(path = "/{code}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Mono<PostalCodeResponse>> findById(@PathVariable(name = "code") String code) {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.service.findByCode(code).map(PostalCodeResponse::from));
    }

}
