package com.qiyexuxu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Md5AndBase64AlgorithmUtil {

	public static String genEncryptMessage(String message){
		
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("md5");
			byte[] md5 = md.digest(message.getBytes());
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(md5);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException();
		}
	}

}
