package org.holbreich.crypto.examle.hash;

public class Converter {

	public static String toHexString(byte[] bytes){
	       //Convert the byte to hex format method 
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<bytes.length;i++) {
    		String hex=Integer.toHexString(0xff & bytes[i]);
   	     	if(hex.length()==1) {
   	     		hexString.append('0');
   	     		}
   	     	hexString.append(hex);
    	}
    	return hexString.toString();
	}
}
