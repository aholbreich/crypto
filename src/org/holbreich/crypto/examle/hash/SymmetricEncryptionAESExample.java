package org.holbreich.crypto.examle.hash;

import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class SymmetricEncryptionAESExample {
	private String AlphaNum = "A1B2C%D3EF)GH5IJ4K6L9MN7OP0QRS8TUVWXYZa!bc@de$fg#hij&k*l(m-n_op+q{r][st}u:v;w,x.?";


	public byte[] encryptECB(String originalText, byte[] secretKey) {
	
		byte[] encrypted =null;
		if (originalText != null && secretKey != null) {
			try {
				byte[] encryptText = originalText.getBytes();
				SecretKey skeySpec = new SecretKeySpec(secretKey, "AES");
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
				encrypted = cipher.doFinal(encryptText);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		return encrypted;
	}

	public String decryptECB(byte[] chiptherText, byte[] secretKey) {

		String plainText =null;

		if (chiptherText != null && secretKey != null) {
			try {
	
				SecretKeySpec skeySpec = new SecretKeySpec(secretKey, "AES");
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				cipher.init(Cipher.DECRYPT_MODE, skeySpec);
				plainText =new String(cipher.doFinal(chiptherText));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return plainText;
	}

	public byte[] encryptCBC(String originalText, byte[] secretKey, byte[] iv) {
		
		byte[] encrypted =null;
		if (originalText != null && secretKey != null) {
			try {
				byte[] encryptText = originalText.getBytes();
				SecretKey skeySpec = new SecretKeySpec(secretKey, "AES");
				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //AES/CTR/NoPadding for CTR.
				IvParameterSpec ivspec = new IvParameterSpec(iv);
				cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivspec);
				encrypted = cipher.doFinal(encryptText);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		return encrypted;
	}
	
	public String decryptCBC(byte[] chiptherText, byte[] secretKey, byte[] iv) {

		String plainText =null;

		if (chiptherText != null && secretKey != null) {
			try {
	
				SecretKeySpec skeySpec = new SecretKeySpec(secretKey, "AES");
				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				IvParameterSpec ivspec = new IvParameterSpec(iv);
				cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivspec);
				plainText =new String(cipher.doFinal(chiptherText));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return plainText;
	}
	
	
 
	
	/**
	 * Generate random text/string of 200 characters
	 * 
	 * @return String
	 */
	public String generateData(int length) {
		String Randomline = new String();
		Random rand = new Random();
		char tokenChar = '\000';
		for (int i = 0; i < length; i++) {
			tokenChar = AlphaNum.charAt(rand.nextInt(AlphaNum.length()));
			Randomline = Randomline + tokenChar;
		}
		return Randomline;
	}

	/**
	 * Generate Secret Key using javax.crypto.KeyGenerator class
	 * 
	 * @return String
	 */
	public byte[] getSecretKey() {
		
		byte[] strSecretkey = null;
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			SecretKey secretkey = keyGen.generateKey();
			return secretkey.getEncoded();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strSecretkey;
	}
	
	public byte[] getIV(){
	    
        SecureRandom random = new SecureRandom(); // build the initialization vector (randomly).
        byte iv[] = new byte[16];//generate random 16 byte IV. AES IS is 16 bytes = 128 bits here 
        random.nextBytes(iv);
        return  iv;
	}
	

	
}
