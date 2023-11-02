package com.toshiakiezaki.example.utils;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ULID {

    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();

    private Random random = new Random();

    public UUID randomUUID() {
        var hexEpoch = StringUtils.leftPad(Long.toHexString(Instant.now().toEpochMilli()), 12, '0');
        var bytes = new byte[10];
        random.nextBytes(bytes);
        var hexRandom = bytesToHex(bytes);
        var strUUID = String.format("%s-%s-%s-%s-%s", hexEpoch.substring(0, 8), hexEpoch.substring(8, 12),
                hexRandom.substring(0, 4), hexRandom.substring(4, 8), hexRandom.substring(8, 20));
        return UUID.fromString(strUUID);
    }

    private String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

}
