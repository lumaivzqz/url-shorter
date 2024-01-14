package com.lumaivzqz.urlshortener.application.services;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class Base64Service {


    private static final Base64.Decoder decoder = Base64.getDecoder();

    private static final Base64.Encoder encoder = Base64.getEncoder();

    public Long decode(String value) throws NumberFormatException{
        return Long.decode(decoder.decode(value).toString());
    }
}