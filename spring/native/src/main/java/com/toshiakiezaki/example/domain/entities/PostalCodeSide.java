package com.toshiakiezaki.example.domain.entities;

import com.toshiakiezaki.example.domain.exceptions.UnmappablePropertyValueException;

import lombok.AllArgsConstructor;

import static java.util.Arrays.stream;

@AllArgsConstructor
public enum PostalCodeSide {

    ODD("ímpar"),
    EVEN("par");

    private final String code;

    public String code() {
        return code;
    }

    public static PostalCodeSide parse(String code) {
        return stream(values()).filter(f -> f.code().equals(code)).findFirst()
                .orElseThrow(() -> new UnmappablePropertyValueException(String.format("Unsupported side value: %s", code)));
    }

}
