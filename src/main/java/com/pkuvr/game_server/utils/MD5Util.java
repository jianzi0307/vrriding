package com.pkuvr.game_server.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    private MessageDigest md5;

    public MD5Util() {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public byte[] md5(byte[] data) {
        return md5.digest(data);
    }

    public byte[] md5(String data) {
        return md5(data.getBytes());
    }

    public String md5Hex(byte[] data) {
        return HexUtil.toHexString(md5(data));
    }

    public String md5Hex(String data) {
        return HexUtil.toHexString(md5(data));
    }

    public void update(byte[] data, int begin, int offset) {
        md5.update(data, begin, offset);
    }

    public String getDigestHex() {
        byte[] digestData = md5.digest();
        if (digestData != null && digestData.length > 0) {
            return HexUtil.toHexString(digestData);
        }
        return null;
    }
}
