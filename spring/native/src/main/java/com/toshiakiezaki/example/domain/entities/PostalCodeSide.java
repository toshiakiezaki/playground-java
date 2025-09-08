package com.toshiakiezaki.example.domain.entities;

import com.toshiakiezaki.example.domain.exceptions.UnmappablePropertyValueException;

import lombok.AllArgsConstructor;

import static java.util.Arrays.stream;

@AllArgsConstructor
public enum PostalCodeSide {

    ODD("ímpar", "i"),
    EVEN("par", "p");

    private final String code;

    private final String abbreviation;

    public String code() {
        return code;
    }

    public String abbreviation() {
        return abbreviation;
    }

    public static PostalCodeSide parse(String str) {
        return stream(values()).filter(f -> f.code().equals(str) || f.abbreviation().equals(str)).findFirst()
                .orElseThrow(() -> new UnmappablePropertyValueException(String.format("Unsupported side value: %s", str)));
    }

}
