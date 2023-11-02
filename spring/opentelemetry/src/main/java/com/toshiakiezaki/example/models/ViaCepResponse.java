package com.toshiakiezaki.example.models;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.toshiakiezaki.example.entities.PostalCodeSide;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ViaCepResponse {

    @JsonProperty(value = "cep")
    private String code;

    @JsonProperty(value = "logradouro")
    private String street;

    @JsonProperty(value = "bairro")
    private String neighborhood;

    @JsonProperty(value = "localidade")
    private String city;

    @JsonProperty(value = "uf")
    private String state;

    @JsonProperty(value = "complemento")
    private String notes;

    @JsonProperty(value = "ddd")
    private String ddd;

    @JsonProperty(value = "ibge")
    private String ibge;

    @JsonProperty(value = "siafi")
    private String siafi;

    @JsonProperty(value = "gia")
    private String gia;

    public Optional<PostalCodeSide> getSide() {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    public Optional<Integer> getStartRange() {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    public Optional<Integer> getEndRange() {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

}
