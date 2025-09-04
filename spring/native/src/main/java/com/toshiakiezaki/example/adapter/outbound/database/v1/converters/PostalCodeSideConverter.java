package com.toshiakiezaki.example.adapter.outbound.database.v1.converters;

import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.convert.EnumWriteSupport;

import com.toshiakiezaki.example.domain.entities.PostalCodeSide;

@WritingConverter
public class PostalCodeSideConverter extends EnumWriteSupport<PostalCodeSide> {

}
