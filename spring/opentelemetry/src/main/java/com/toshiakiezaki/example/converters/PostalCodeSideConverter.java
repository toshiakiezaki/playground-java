package com.toshiakiezaki.example.converters;

import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.convert.EnumWriteSupport;

import com.toshiakiezaki.example.entities.PostalCodeSide;

@WritingConverter
public class PostalCodeSideConverter extends EnumWriteSupport<PostalCodeSide> {

}
