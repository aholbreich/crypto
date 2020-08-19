package org.holbreich.crypto.examle.password;

import static org.holbreich.crypto.examle.password.HexHelper.toHex;
import static org.holbreich.crypto.examle.password.HexHelper.fromHex;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.spec.PBEKeySpec;

public class PbkDF2Example {

	private static final int HASH_BYTE_SIZE = 64*8; 
	
	private final int iterations;
	private final SaltFactory saltFactory; 
	private final PbkDF2 keyFactory;
	
	public PbkDF2Example(int iterations) throws NoSuchAlgorithmException {
	
		this.iterations = iterations;
		saltFactory = new SaltFactory();
		keyFactory = new PbkDF2();
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String plainTextPassword = "MyPassword";
		int iterations = 10000;
		PbkDF2Example example = new PbkDF2Example(iterations);
		long startMilis = System.currentTimeMillis();
		String securedPasswordHash = example.encrypt(plainTextPassword);
		long endMilis = System.currentTimeMillis();
		System.out.println("Took" +(endMilis-startMilis)+"ms., Iterations "+iterations+" Enctypted to:");
		System.out.println(securedPasswordHash);
		
		long validationStart = System.currentTimeMillis();
		boolean isValid = example.validatePassword(plainTextPassword, securedPasswordHash);
		long validationEnd = System.currentTimeMillis();
		System.out.print("Took:"+(validationEnd-validationStart)+"ms., valid:"+isValid);
		
	}


	private String encrypt(String plainTextPassword) throws InvalidKeySpecException {
		byte[] salt = saltFactory.getNewSalt();
		PBEKeySpec spec = new PBEKeySpec(plainTextPassword.toCharArray(), salt, iterations, HASH_BYTE_SIZE);

		byte[] resultHash = keyFactory.generateSecret(spec);
		return  iterations + ":" + toHex(salt) + ":" + toHex(resultHash);
	}

	private  boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);
         
        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        byte[] testHash = keyFactory.generateSecret(spec);
         
        return isEqual(hash, testHash);
        
    }

	private boolean isEqual(byte[] hash, byte[] testHash) {
		int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
	}

}
