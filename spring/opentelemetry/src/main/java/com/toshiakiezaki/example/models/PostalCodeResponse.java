package com.toshiakiezaki.example.models;

import static java.util.Objects.nonNull;
import static java.util.Optional.of;
import static java.util.Optional.empty;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.toshiakiezaki.example.entities.PostalCode;
import com.toshiakiezaki.example.entities.PostalCodeSide;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostalCodeResponse implements Serializable {

    @JsonProperty(value = "id")
    private UUID id;

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

    @JsonProperty(value = "side")
    private Optional<PostalCodeSide> side;

    @JsonProperty(value = "start_range")
    private Optional<Integer> startRange;

    @JsonProperty(value = "end_range")
    private Optional<Integer> endRange;

    public static PostalCodeResponse from(PostalCode postalCode) {
        return builder()
                .id(postalCode.getId())
                .code(postalCode.getCode())
                .street(postalCode.getStreet())
                .neighborhood(postalCode.getNeighborhood())
                .city(postalCode.getCity())
                .state(postalCode.getState())
                .side(nonNull(postalCode.getSide()) ? of(postalCode.getSide()) : empty())
                .startRange(nonNull(postalCode.getStartRange()) ? of(postalCode.getStartRange()) : empty())
                .endRange(nonNull(postalCode.getEndRange()) ? of(postalCode.getEndRange()) : empty())
                .build();
    }

    public static PostalCodeResponse from(ViaCepResponse viaCep) {
        return builder()
                .code(viaCep.getCode().replaceAll("[^0-9]", ""))
                .street(viaCep.getStreet())
                .neighborhood(viaCep.getNeighborhood())
                .city(viaCep.getCity())
                .state(viaCep.getState())
                .side(viaCep.getSide())
                .startRange(viaCep.getStartRange())
                .endRange(viaCep.getEndRange())
                .build();
    }

}
