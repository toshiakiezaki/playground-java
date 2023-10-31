package com.toshiakiezaki.example.entities;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Embedded.Nullable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static java.util.Objects.isNull;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "postal_code")
public class PostalCode implements Persistable<UUID> {

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

    @Nullable
    @Column(value = "side")
    private PostalCodeSide side;

    @Nullable
    @Column(value = "start_range")
    private Integer startRange;

    @Nullable
    @Column(value = "end_range")
    private Integer endRange;

    @Override
    public boolean isNew() {
        return isNull(id);
    }

}
