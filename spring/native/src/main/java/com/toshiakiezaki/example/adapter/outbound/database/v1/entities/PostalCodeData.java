package com.toshiakiezaki.example.adapter.outbound.database.v1.entities;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.toshiakiezaki.example.domain.entities.PostalCode;
import com.toshiakiezaki.example.domain.entities.PostalCodeSide;

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

    @Column(value = "side")
    private PostalCodeSide side;

    @Column(value = "start_range")
    private Integer startRange;

    @Column(value = "end_range")
    private Integer endRange;

    @Override
    public boolean isNew() {
        return !persisted;
    }

    private PostalCodeData() {
        this.persisted = true;
    }

    public PostalCode toEntity() {
        return PostalCode.builder()
                .code(this.code)
                .street(this.street)
                .neighborhood(this.neighborhood)
                .city(this.city)
                .state(this.state)
                .startRange(this.startRange)
                .endRange(this.endRange)
                .build();
    }

}
