package com.simple.ecommerce.util;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import com.google.common.hash.Hashing;

public class ShaUtil {

	/**
	 * 문자열 암호화 하는 메소드
	 * @param plainText - (String) 변환할 문자열
	 * @return sha256으로 인코딩된 문자열
	 */
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
