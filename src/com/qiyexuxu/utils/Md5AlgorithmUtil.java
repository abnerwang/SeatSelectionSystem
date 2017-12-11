package com.qiyexuxu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5AlgorithmUtil {
    public static String bytes2HexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; ++i) {
            String hex = Integer.toHexString(b[i] & 255);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret = ret + hex.toUpperCase();
        }
        return ret;
    }

    public static String genEncryptMessage(String message) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("md5");
            byte[] md5 = md.digest(message.getBytes());
            return bytes2HexString(md5).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }

}
