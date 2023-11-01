package com.toshiakiezaki.example.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.toshiakiezaki.example.models.PostalCodeResponse;
import com.toshiakiezaki.example.models.PostalCodeSearch;
import com.toshiakiezaki.example.services.PostalCodeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/v1/postal-codes")
public class PostalCodeController {

    private final PostalCodeService service;

    @Autowired
    public PostalCodeController(PostalCodeService service) {
        this.service = service;
    }

    @RequestMapping(path = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Flux<PostalCodeResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.service.findAll());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Mono<PostalCodeResponse>> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.service.findById(id));
    }

    @RequestMapping(path = "/search", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Mono<PostalCodeResponse>> findByCode(@RequestBody PostalCodeSearch payload) {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.service.search(payload));
    }

}
