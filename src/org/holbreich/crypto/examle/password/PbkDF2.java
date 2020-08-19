package org.holbreich.crypto.examle.password;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PbkDF2 {
	
	private final SecretKeyFactory keyFactory;

	public PbkDF2() throws NoSuchAlgorithmException {
		keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
	}

	public byte[] generateSecret(final PBEKeySpec keySpec) throws InvalidKeySpecException {
		return keyFactory.generateSecret(keySpec).getEncoded();
	}
	

}
