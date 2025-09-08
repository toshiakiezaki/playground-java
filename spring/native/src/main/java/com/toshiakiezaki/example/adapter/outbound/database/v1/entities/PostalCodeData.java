package com.toshiakiezaki.example.adapter.outbound.database.v1.entities;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.toshiakiezaki.example.domain.entities.PostalCode;
import com.toshiakiezaki.example.domain.entities.PostalCodeSide;
import com.toshiakiezaki.example.domain.entities.PostalCodeUnit;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Table(name = "postal_code")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostalCodeData implements Persistable<UUID> {

    @Transient
    @Builder.Default
    private boolean persisted = false;

    @Id
    @Column(value = "id")
    private UUID id;

    @Column(value = "code")
    private String code;

    @Column(value = "street")
    private String street;

    @Column(value = "neighborhood")
    private String neighborhood;

    @Column(value = "city")
    private String city;

    @Column(value = "state")
    private String state;

    @Column(value = "range_side")
    private PostalCodeSide rangeSide;

    @Column(value = "range_unit")
    private PostalCodeUnit rangeUnit;

    @Column(value = "range_start")
    private Integer rangeStart;

    @Column(value = "range_end")
    private Integer rangeEnd;

    @Override
    public boolean isNew() {
        return !persisted;
    }

    private PostalCodeData() {
        this.persisted = true;
    }

    public PostalCode toEntity() {
        return PostalCode.builder()
                .id(getId())
                .code(getCode())
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

    public static PostalCodeData from(PostalCode postalCode) {
        return PostalCodeData.builder()
                .id(postalCode.getId())
                .code(postalCode.getCode())
                .street(postalCode.getStreet())
                .neighborhood(postalCode.getNeighborhood())
                .city(postalCode.getCity())
                .state(postalCode.getState())
                .rangeSide(postalCode.getRangeSide())
                .rangeUnit(postalCode.getRangeUnit())
                .rangeStart(postalCode.getRangeStart())
                .rangeEnd(postalCode.getRangeEnd())
                .build();
    }

}
