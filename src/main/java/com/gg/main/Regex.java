package com.gg.main;

public class Regex {
	
	
	public static boolean isValidCorrection(String correction, int solutionLength) {
		String regExp = "^[-=+]{"+ solutionLength +"}$";
		return correction.matches(regExp);
	}
	
	
	public static boolean isValidCombination(String combination, int solutionLength) {
		String regExp = "^[1-9]{"+ solutionLength +"}$";
		return combination.matches(regExp);
	}
}
