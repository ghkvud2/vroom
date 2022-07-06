package com.woorifis.vroom.util;

public class CollectUtils {

	public static String remove(String text, String remove) {
		return text.replaceAll(remove, "");
	}

	public static String remove(String text, String... removes) {
		String result = text;
		for (String remove : removes) {
			result = result.replaceAll(remove, "");
		}
		return result;
	}

	public static String removeRange(String text, char startChar, char endChar) {
		return text.substring(0, text.indexOf(startChar)) + text.substring(text.indexOf(endChar) + 1);
	}

	public static String includeRange(String text, int startIdx, int endIdx) {
		return text.substring(startIdx, endIdx + 1);
	}
}
