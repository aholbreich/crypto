package org.holbreich.crypto.examle.password;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SaltFactory {

	private final SecureRandom secureRandom;

	public SaltFactory() throws NoSuchAlgorithmException {
		secureRandom = SecureRandom.getInstance("SHA1PRNG");
	}

	public byte[] getNewSalt() {

		byte[] salt = new byte[16];
		secureRandom.nextBytes(salt);
		return salt;

	}

}
