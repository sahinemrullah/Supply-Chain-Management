package com.webapi.application.utils;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public final class EncryptionUtils {

	private EncryptionUtils() {
		
	}
	
	public static String HashString(String str) {
		return Hashing.sha256()
				.hashString(str, StandardCharsets.UTF_8)
				.toString();
	}
	
	public static boolean CheckHash(String str, String hashToCompare) {
		return StringUtils.isEqual(str, hashToCompare);
	}

}
