package com.gg.main;

public class Regex {
	
	
	public static boolean isValidCorrection(String correction, int nbCases) {
		String regExp = "^[-=+]{"+ nbCases +"}$";
		return correction.matches(regExp);
	}
	
	
	public static boolean isValidCombination(String combination, int nbCases) {
		String regExp = "^[1-9]{"+ nbCases +"}$";
		return combination.matches(regExp);
	}
}
