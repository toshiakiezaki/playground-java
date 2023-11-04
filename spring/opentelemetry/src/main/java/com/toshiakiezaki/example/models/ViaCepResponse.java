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
        if ("".equals(notes) || !notes.contains("lado")) {
            return Optional.empty();
        }
        var side = notes.replaceAll(".*lado\s", "");
        return Optional.of(PostalCodeSide.parse(side));
    }

    public Optional<Integer> getStartRange() {
        if ("".equals(notes) || !notes.startsWith("de")) {
            return Optional.empty();
        }
        var parts = notes.split(" ");
        return this.extractRange(parts[1]);
    }

    public Optional<Integer> getEndRange() {
        if ("".equals(notes) || notes.startsWith("lado") || notes.contains("ao fim")) {
            return Optional.empty();
        }
        var parts = notes.split(" ");
        if (parts[0].equals("até")) {
            return this.extractRange(parts[1], false);
        }
        return this.extractRange(parts[3], false);
    }

    private Optional<Integer> extractRange(String value) {
        return this.extractRange(value, true);
    }

    private Optional<Integer> extractRange(String value, boolean startingRange) {
        var parts = value.split("/");
        var index = (startingRange) ? 0 : parts.length - 1;
        return Optional.of(Integer.parseInt(parts[index]));
    }

}
