package com.webstaurantstore.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
	/**
	 * Extract Numeric value from String
	 * @param input Input String
	 * @return numeric value
	 */
	public static String extractNumericValue(String input) {
		// Define a regular expression pattern to match numeric values
		Pattern pattern = Pattern.compile("\\b(\\d+)\\b");
		Matcher matcher = pattern.matcher(input);

		// Find the first match
		if (matcher.find()) {
			return matcher.group(1);
		}

		// Return null if no match is found
		return null;
	}
}
