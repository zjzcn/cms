package com.zjzcn.util;

import java.util.UUID;

public class UuidUtils {

    public static byte[] generateUuidBytes() {
        UUID uuid = UUID.randomUUID();
        return UuidUtils.uuidToBytes(uuid);
    }

    public static String generateUuidHexString() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "").toUpperCase();
    }

    public static byte[] uuidToBytes(UUID uuid) {
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();

        byte[] uuidBytes = new byte[16];

        for (int i = 0; i < 8; i++) {
            uuidBytes[i] = (byte) (msb >>> 8 * (7 - i));
        }

        for (int i = 8; i < 16; i++) {
            uuidBytes[i] = (byte) (lsb >>> 8 * (7 - i));
        }

        return uuidBytes;
    }

    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();

        System.out.println(uuid.toString().replaceAll("-", "").toUpperCase());
        long s1 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            UuidUtils.generateUuidHexString();
        }
        long e1 = System.currentTimeMillis();
        System.out.println(UuidUtils.generateUuidHexString());

    }

}
