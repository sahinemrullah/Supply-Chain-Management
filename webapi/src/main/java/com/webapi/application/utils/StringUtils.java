package com.webapi.application.utils;

import java.util.regex.Pattern;

public final class StringUtils {

	private StringUtils() {
		
	}
	
	public static boolean isNull(String str) {
		return str == null;
	}
	
	public static boolean isEqual(String str, String compare, boolean caseSensitive) {
		if(isNull(str) || isNull(compare))
			return false;
		
		if(caseSensitive)
			return str.equals(compare);
		
		return str.equalsIgnoreCase(compare);
	}
	
	public static boolean isEqual(String str, String compare) {
		return isEqual(str, compare, false);
	}
	
	public static boolean isEmpty(String str) {
		return isNull(str) || str.isEmpty();
	}
	
	public static boolean isEmptyOrWhiteSpace(String str) {
		return isNull(str) || str.isBlank();
	}
	
	public static boolean patternMatches(String str, String regex) {
	    return Pattern.compile(regex)
	      .matcher(str)
	      .matches();
	}

}
