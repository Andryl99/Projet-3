package com.gg.main;

import com.gg.main.players.SequenceType;

public class Regex {


	public static boolean isValidCombination(String combination, int solutionLength, SequenceType sequence) {
		String regExp;
		switch (sequence) {
		case ISCOMBINATION :
			 regExp = "^[1-9]{" + solutionLength + "}$";
			return combination.matches(regExp);
		case ISCORRECTION :
			 regExp = "^[-=+]{" + solutionLength + "}$";
			return combination.matches(regExp);
		default :
			return false;
		}


	}
}
