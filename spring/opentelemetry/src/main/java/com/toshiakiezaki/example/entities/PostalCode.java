package com.toshiakiezaki.example.entities;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder.Default;

@Getter
@Setter
@Builder
@Table(name = "postal_code")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostalCode implements Persistable<UUID> {

    @Default
    @Transient
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

    private PostalCode() {
        this.persisted = true;
    }

}
