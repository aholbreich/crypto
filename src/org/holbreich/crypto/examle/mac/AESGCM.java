package org.holbreich.crypto.examle.mac;

import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class AESGCM {

	// AES-GCM parameters
	public static final int GCM_NONCE_LENGTH = 12; // in bytes
	public static final int GCM_TAG_LENGTH = 16; // in bytes

	public static void main(String[] args) throws Exception {

		byte[] input = "AES-GCM Test ".getBytes();

		SecureRandom random = SecureRandom.getInstanceStrong();
		
		//AES secrete key generation
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128, random);
		SecretKey key = keyGen.generateKey();

		// Init cipher with nonce(IV) and tag length and AAD
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "SunJCE");
		GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, getIV(random));
		cipher.init(Cipher.ENCRYPT_MODE, key, spec);
		byte[] aadHeader = "Joe Your Friend".getBytes();
		cipher.updateAAD(aadHeader); //Additional Authentication Data (AAD) is plaintext

		byte[] cipherText = cipher.doFinal(input);

		// Decrypt; nonce is shared implicitly
		cipher.init(Cipher.DECRYPT_MODE, key, spec);

		cipher.updateAAD(aadHeader);

		try {
			byte[] plainText = cipher.doFinal(cipherText);

			// check if the decryption result matches
			if (Arrays.equals(input, plainText)) {
				System.out.println("Test Passed: match!");
			} else {
				System.out.println("Test Failed: result mismatch!");
				System.out.println(new String(plainText));
			}

		} catch (AEADBadTagException ex) {

			System.out.println("Test Failed: unexpected ex " + ex);
			ex.printStackTrace();

		}
	}

	private static byte[] getIV(SecureRandom random) {
		final byte[] nonce = new byte[GCM_NONCE_LENGTH];
		random.nextBytes(nonce);
		return nonce;
	}
}
