package com.simple.ecommerce.commons;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import com.google.common.hash.Hashing;

public class ShaUtil {
	public static String sha256Encode(String plainText) {
		
	    return Hashing.sha256()
	        .hashString(plainText, StandardCharsets.UTF_8)
	        .toString();
	  }
	
	public static String randomString() {
		int limit = 8;
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		
		SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(limit);
        
        for (int i = 0; i < limit; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString().toLowerCase();
	}
}
