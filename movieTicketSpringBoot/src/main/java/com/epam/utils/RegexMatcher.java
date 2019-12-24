package com.epam.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class RegexMatcher {

	public boolean matchPattern(String regex, String inputValue) {
		boolean isPatternMatched = false;
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(inputValue);
		if (m.find()) {
			isPatternMatched = true;
		}
		return isPatternMatched;
	}
}
