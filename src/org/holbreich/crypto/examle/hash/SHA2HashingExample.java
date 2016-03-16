package org.holbreich.crypto.examle.hash;

import java.security.MessageDigest;

public class SHA2HashingExample 
{
    public static void main(String[] args)throws Exception
    {
    	String password = "MyWeakPassword";
    	
    	//Possible SHA-256, SHA-384, and SHA-512
    	//See http://docs.oracle.com/javase/1.5.0/docs/guide/security/CryptoSpec.html#AppA
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        
        byte mdByteData[] = md.digest();
        
    	System.out.println("Hex format output: " + Converter.toHexString(mdByteData));
    }
}