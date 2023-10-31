package com.toshiakiezaki.example.entities;

import com.fasterxml.jackson.annotation.JsonValue;

import static java.util.Arrays.stream;

public enum PostalCodeSide {

    ODD,
    EVEN;

    @JsonValue
    public String code() {
        return name();
    }

    public static PostalCodeSide parse(String code) {
        return stream(values()).filter(f -> f.code().equals(code)).findFirst().orElseThrow();
    }

}
