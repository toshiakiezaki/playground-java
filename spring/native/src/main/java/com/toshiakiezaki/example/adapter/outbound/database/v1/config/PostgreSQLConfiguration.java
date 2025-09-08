package com.toshiakiezaki.example.adapter.outbound.database.v1.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

import com.toshiakiezaki.example.adapter.outbound.database.v1.converters.PostalCodeSideConverter;
import com.toshiakiezaki.example.adapter.outbound.database.v1.converters.PostalCodeUnitConverter;
import com.toshiakiezaki.example.domain.entities.PostalCodeSide;
import com.toshiakiezaki.example.domain.entities.PostalCodeUnit;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.postgresql.codec.EnumCodec;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
public class PostgreSQLConfiguration extends AbstractR2dbcConfiguration {

    @Value(value = "${spring.r2dbc.hostname}")
    private String hostname;

    @Value(value = "${spring.r2dbc.port}")
    private int port;

    @Value(value = "${spring.r2dbc.database}")
    private String database;

    @Value(value = "${spring.r2dbc.username}")
    private String username;

    @Value(value = "${spring.r2dbc.password}")
    private String password;

    @Bean
    @Override
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
                .host(hostname)
                .port(port)
                .database(database)
                .username(username)
                .password(password)
                .codecRegistrar(EnumCodec.builder()
                        .withEnum("postal_code_side", PostalCodeSide.class)
                        .withEnum("postal_code_unit", PostalCodeUnit.class)
                        .build())
                .build());
    }

    @Override
    protected List<Object> getCustomConverters() {
        return List.of(new PostalCodeSideConverter(), new PostalCodeUnitConverter());
    }

}
