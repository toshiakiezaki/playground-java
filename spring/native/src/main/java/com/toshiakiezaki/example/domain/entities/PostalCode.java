package com.toshiakiezaki.example.domain.entities;

import java.util.Optional;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostalCode {

    private UUID id;

    private String code;

    private String street;

    private String neighborhood;

    private String city;

    private String state;

    private Optional<PostalCodeSide> rangeSide;

    private Optional<PostalCodeUnit> rangeUnit;

    private Optional<Integer> rangeStart;

    private Optional<Integer> rangeEnd;

}
