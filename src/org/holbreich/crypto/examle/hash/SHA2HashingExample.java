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
 
        //Convert the byte to hex format method 
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<mdByteData.length;i++) {
    		String hex=Integer.toHexString(0xff & mdByteData[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    	System.out.println("Hex format output: " + hexString.toString());
    }
}