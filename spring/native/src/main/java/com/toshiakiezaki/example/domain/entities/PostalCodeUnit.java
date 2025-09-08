package com.toshiakiezaki.example.domain.entities;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PostalCodeUnit {

    NUMBER("NR"),
    KILOMETER("KM");

    private final String code;

    public String code() {
        return code;
    }

}
