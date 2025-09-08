package com.toshiakiezaki.example.adapter.outbound.webclient.v1.entities;

import java.util.Arrays;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.toshiakiezaki.example.domain.entities.PostalCode;
import com.toshiakiezaki.example.domain.entities.PostalCodeSide;
import com.toshiakiezaki.example.domain.entities.PostalCodeUnit;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ViaCepData {

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

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private Optional<PostalCodeSide> rangeSide;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private Optional<PostalCodeUnit> rangeUnit;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private Optional<Integer> rangeStart;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private Optional<Integer> rangeEnd;

    public Optional<PostalCodeSide> getRangeSide() {
        if (isNull(rangeSide)) {
            rangeSide = extractRangeSide(notes);
        }
        return rangeSide;
    }

    public Optional<PostalCodeUnit> getRangeUnit() {
        if (isNull(rangeUnit)) {
            rangeUnit = extractRangeUnit(notes);
        }
        return rangeUnit;
    }

    public Optional<Integer> getRangeStart() {
        if (isNull(rangeStart)) {
            rangeStart = extractRangeStart(getNotes(), getRangeSide());
        }
        return rangeStart;
    }

    public Optional<Integer> getRangeEnd() {
        if (isNull(rangeEnd)) {
            rangeEnd = extractRangeEnd(getNotes(), getRangeSide());
        }
        return rangeEnd;
    }

    private Optional<PostalCodeSide> extractRangeSide(String str) {
        if (isEmpty(str) || !str.contains("lado")) {
            return Optional.empty();
        }
        var side = str.replaceAll(".*lado ", "");
        return Optional.of(PostalCodeSide.parse(side));
    }

    private Optional<PostalCodeUnit> extractRangeUnit(String str) {
        if (isEmpty(str)) {
            return Optional.empty();
        }
        if  (str.contains("km")) {
            return Optional.of(PostalCodeUnit.KILOMETER);
        }
        return Optional.of(PostalCodeUnit.NUMBER);
    }

    private Optional<Integer> extractRangeStart(String str, Optional<PostalCodeSide> rangeSide) {
        // Remove the unnecessary part
        str = str.replaceAll("( - )?lado.*", "").trim();

        // Range start for numbers
        if (str.contains("de")) {
            var part = str.substring(str.indexOf("de") + 3).split(" ")[0];
            return extractRangeOption(part.replace(",", ""), true, rangeSide);
        } else if (str.startsWith("até")) {
            if (rangeSide.orElse(null) == PostalCodeSide.EVEN) {
                return Optional.of(2);
            } else {
                return Optional.of(1);
            }
        }

        // Range start for kilometers
        if (str.contains("do km")) {
            var part = str.substring(str.indexOf("do km") + 6).split(" ")[0];
            if (!part.contains(",")) {
                part += "000";
            }
            return extractRangeOption(part.replace(",", ""), true, rangeSide);
        } else if (str.startsWith("ao km")) {
            return Optional.of(0);
        }

        // Default decision
        return Optional.empty();
    }

    private Optional<Integer> extractRangeEnd(String str, Optional<PostalCodeSide> rangeSide) {
        // Remove the unnecessary part
        str = str.replaceAll("( - )?lado.*", "").trim();

        // Range end for numbers
        if (str.contains("até")) {
            var part = str.substring(str.indexOf("até") + 4).split(" ")[0];
            return extractRangeOption(part, false, rangeSide);
        } else if (str.contains(" a ")) {
            var part = str.substring(str.indexOf(" a ") + 3).split(" ")[0];
            return extractRangeOption(part, false, rangeSide);
        } else if (str.contains("ao fim")) {
            return Optional.empty();
        }

        // Range end for kilometers
        if (str.contains("ao km")) {
            var part = str.substring(str.indexOf("ao km") + 6).split(" ")[0];
            if (!part.contains(",")) {
                part += "000";
            }
            return extractRangeOption(part.replace(",", ""), false, rangeSide);
        }

        // Default decision
        return Optional.empty();
    }

    private Optional<Integer> extractRangeOption(String str, boolean first, Optional<PostalCodeSide> rangeSide) {
        var parts = str.split("/");
        var values = Arrays.stream(parts).map(Integer::parseInt).filter(value -> rangeSide.map(side -> side.isValid(value)).orElse(true)).toList();
        var index = (first) ? 0 : values.size() - 1;
        return Optional.of(values.get(index));
    }

    public PostalCode toEntity() {
        return PostalCode.builder()
                .code(getCode().replaceAll("[^0-9]", ""))
                .street(getStreet())
                .neighborhood(getNeighborhood())
                .city(getCity())
                .state(getState())
                .rangeSide(getRangeSide())
                .rangeUnit(getRangeUnit())
                .rangeStart(getRangeStart())
                .rangeEnd(getRangeEnd())
                .build();
    }

}
