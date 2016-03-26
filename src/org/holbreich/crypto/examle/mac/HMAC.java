package org.holbreich.crypto.examle.mac;

import java.security.SecureRandom;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.holbreich.crypto.examle.hash.Converter;


public class HMAC {

	public static void main(String[] args) throws Exception {

		SecureRandom random = new SecureRandom();
		byte[] keyBytes = new byte[64];
		random.nextBytes(keyBytes);
		SecretKey key = new SecretKeySpec(keyBytes, "HMACSHA1");
		System.out.println("Key: " + Converter.toHexString(key.getEncoded()));

		Mac mac = Mac.getInstance("HmacSHA1");
		// A signing algorithm efficiently returns a tag given the key and the
		// message.
		mac.init(key);
		byte[] result = mac.doFinal("quick brown fox jumps over the lazy dog"
				.getBytes("UTF8"));

		System.out.println("MAC: " + Converter.toHexString(result));
		
	}
}
