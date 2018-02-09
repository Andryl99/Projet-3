package com.gg.main.players;

import java.util.InputMismatchException;

import com.gg.main.Regex;

public class HumanPlayer extends Player {

	@Override
	public String selectSolution(int solutionLength) {
		System.out.println("Entrez une combinaison de " + solutionLength + " chiffres à trouver.");
		return this.inputErrorCheck(solutionLength, SequenceType.ISCOMBINATION);
	}

	@Override
	public String giveAnswer(String proposition, String solution, int solutionLength) {
		return this.inputErrorCheck(solutionLength, SequenceType.ISCORRECTION); 
	}

	@Override
	public String play(String reponse, int solutionLength) {
		return this.inputErrorCheck(solutionLength, SequenceType.ISCOMBINATION);
	}

	public String inputErrorCheck(int solutionLength, SequenceType sequence) {
		String str = "";
		while (true) {
			try {
				str = scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Erreur de saisie, recommencez :");
			}

			if (Regex.isValidCombination(str, solutionLength, sequence)) {
				break;
			} else {
				System.out.println("Erreur de saisie, recommencez :");
			}
		}
		return str;
	}
}
