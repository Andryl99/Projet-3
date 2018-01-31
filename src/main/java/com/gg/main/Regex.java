package com.gg.main;

public class Regex {
	
	
	public static boolean estCorrectionValide(String correction, int nbCases) {
		String regExp = "^[-=+]{"+ nbCases +"}$";
		return correction.matches(regExp);
	}
	
	
	public static boolean estCombinaisonValide(String combinaison, int nbCases) {
		String regExp = "^[1-9]{"+ nbCases +"}$";
		return combinaison.matches(regExp);
	}
}
