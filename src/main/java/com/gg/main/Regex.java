package com.gg.main;

import com.gg.main.players.SequenceType;

public class Regex {

	public static boolean isValidCombination(String combination, int solutionLength, int nbColors,
			SequenceType sequence) {
		
		String regExp;
		
		switch (sequence) {
		case ISCOMBINATION:
			regExp = "^[0-" + (nbColors - 1) + "]{" + solutionLength + "}$";
			return combination.matches(regExp);
		case ISCORRECTION:
			regExp = "^[-=+]{" + solutionLength + "}$";
			return combination.matches(regExp);
		case ISMASTERMINDCORRECTION:
			regExp = "^[0-" + (nbColors - 1) + "]$";
			return combination.matches(regExp);
		default:
			return false;
		}
	}
}
