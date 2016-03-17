package org.holbreich.crypto.examle.hash;

import java.time.Duration;
import java.time.Instant;

import javax.crypto.spec.IvParameterSpec;

public class SymmericEncryptionTest {

	public static void main(String[] args) {
		
		ecbTest();
		cbcTest();
	}

	private static void ecbTest() {
		System.out.println("========== AES ECB Test ===========");
		SymmetricEncryptionAESExample sTest = new SymmetricEncryptionAESExample();
		byte[] secretKey = sTest.getSecretKey();
		String plainText = sTest.generateData(5000);

		/**
		 * You are adviced to check/validate null values before using this piece
		 * of code for real time application
		 */
		System.out.println("Secret Key:" + Converter.toHexString(secretKey));
		System.out.println("Original text:" + plainText);
        Instant before= Instant.now();
		byte[] cipherText = sTest.encryptECB(plainText, secretKey);
		Instant after= Instant.now();
		System.out.println("Encrypted text in "+Duration.between(before, after).toMillis()+"ms :" + Converter.toHexString(cipherText));

		before= Instant.now();
		String decryptedText = sTest.decryptECB(cipherText, secretKey);
		after= Instant.now();
		System.out.println("Decrypted text in "+Duration.between(before, after).toMillis()+"ms :" + decryptedText);
		System.out.println("Do match:" + decryptedText.equals(plainText));
	}
	
	private static void cbcTest() {
		System.out.println("========== AES CBC Test ===========");
		SymmetricEncryptionAESExample sTest = new SymmetricEncryptionAESExample();
		byte[] secretKey = sTest.getSecretKey();
		String plainText = sTest.generateData(5000);
		byte[] iv = sTest.getIV();

		/**
		 * You are adviced to check/validate null values before using this piece
		 * of code for real time application
		 */
		System.out.println("Secret Key:" + Converter.toHexString(secretKey));
		System.out.println("Original text:" + plainText);
        Instant before= Instant.now();
		byte[] cipherText = sTest.encryptCBC(plainText, secretKey, iv);
		Instant after= Instant.now();
		System.out.println("Encrypted text in "+Duration.between(before, after).toMillis()+"ms :" + Converter.toHexString(cipherText));

		before= Instant.now();
		String decryptedText = sTest.decryptCBC(cipherText, secretKey, iv);
		after= Instant.now();
		System.out.println("Decrypted text in "+Duration.between(before, after).toMillis()+"ms :" + decryptedText);
		System.out.println("Do match:" + decryptedText.equals(plainText));
	}
	
}
