package com.toshiakiezaki.example.models;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.toshiakiezaki.example.entities.PostalCode;
import com.toshiakiezaki.example.entities.PostalCodeSide;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static java.util.Optional.of;

@Getter
@Setter
@Builder
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostalCodeResponse implements Serializable {

    private UUID id;

    private String code;

    private String street;

    private String neighborhood;

    private String city;

    private String state;

    private Optional<PostalCodeSide> side;

    private Optional<Integer> startRange;

    private Optional<Integer> endRange;

    public static PostalCodeResponse from(PostalCode postalCode) {
        return builder()
                .id(postalCode.getId())
                .code(postalCode.getCode())
                .street(postalCode.getStreet())
                .neighborhood(postalCode.getNeighborhood())
                .city(postalCode.getCity())
                .state(postalCode.getState())
                .side(of(postalCode.getSide()))
                .startRange(of(postalCode.getStartRange()))
                .endRange(of(postalCode.getEndRange()))
                .build();
    }

    public static PostalCodeResponse from(ViaCepResponse viaCep) {
        return PostalCodeResponse.builder()
                .code(viaCep.getCep().replaceAll("[^0-9]", ""))
                .street(viaCep.getLogradouro())
                .neighborhood(viaCep.getBairro())
                .city(viaCep.getLocalidade())
                .state(viaCep.getUf())
                .build();
    }

}
