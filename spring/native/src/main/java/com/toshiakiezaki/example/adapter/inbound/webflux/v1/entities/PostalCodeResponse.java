package com.toshiakiezaki.example.adapter.inbound.webflux.v1.entities;

import java.io.Serializable;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.toshiakiezaki.example.domain.entities.PostalCode;
import com.toshiakiezaki.example.domain.entities.PostalCodeSide;
import com.toshiakiezaki.example.domain.entities.PostalCodeUnit;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static java.util.Objects.nonNull;
import static java.util.Optional.empty;
import static java.util.Optional.of;

@Getter
@Setter
@Builder
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostalCodeResponse implements Serializable {

    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "street")
    private String street;

    @JsonProperty(value = "neighborhood")
    private String neighborhood;

    @JsonProperty(value = "city")
    private String city;

    @JsonProperty(value = "state")
    private String state;

    @JsonProperty(value = "rangeSide")
    private Optional<PostalCodeSide> rangeSide;

    @JsonProperty(value = "rangeUnit")
    private Optional<PostalCodeUnit> rangeUnit;

    @JsonProperty(value = "range_start")
    private Optional<Integer> rangeStart;

    @JsonProperty(value = "range_end")
    private Optional<Integer> rangeEnd;

    public static PostalCodeResponse from(PostalCode postalCode) {
        return builder()
                .code(postalCode.getCode())
                .street(postalCode.getStreet())
                .neighborhood(postalCode.getNeighborhood())
                .city(postalCode.getCity())
                .state(postalCode.getState())
                .rangeSide(nonNull(postalCode.getRangeSide()) ? of(postalCode.getRangeSide()) : empty())
                .rangeUnit(nonNull(postalCode.getRangeUnit()) ? of(postalCode.getRangeUnit()) : empty())
                .rangeStart(nonNull(postalCode.getRangeStart()) ? of(postalCode.getRangeStart()) : empty())
                .rangeEnd(nonNull(postalCode.getRangeEnd()) ? of(postalCode.getRangeEnd()) : empty())
                .build();
    }

}
