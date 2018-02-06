package com.gg.joueurs;

import java.util.InputMismatchException;
import com.gg.main.Regex;

public class HumanPlayer extends Player {

	@Override
	public String selectSolution(int solutionLength) {
		System.out.println("Entrez une combinaison de " + solutionLength + " chiffres à trouver.");
		return this.inputErrorCheck(solutionLength);
	}

	@Override
	public String giveAnswer(String proposition, String solution, int solutionLength) {
		String reponse;
		String verification = "";
		int digitProp;
		int digitSol;



		// On compare les combinaison et determine le r�sultat
		// Ici les param�tre de l'application change la borne sup;
		for (int i = 0; i < solutionLength; i++) {

			digitProp = Integer.parseInt(String.valueOf(proposition.charAt(i)));
			digitSol = Integer.parseInt(String.valueOf(solution.charAt(i)));

			if (digitProp == digitSol)
				verification += "=";
			else if (digitProp < digitSol)
				verification += "+";
			else if (digitProp > digitSol)
				verification += "-";
		}

		System.out.println("Corrigez la proposition de l'attaquant : " + proposition + "\t Solution : " + solution);

		while (true) {
			reponse = "";
			try {
				reponse = scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Erreur de saisie, recommencez");
			}
			if	(!Regex.isValidCorrection(reponse, solutionLength)){
				System.out.println("Saisie incorrecte, recommencez");
				continue; // Si la saisie ne match pas l'expression reguliere, on ne teste meme pas la condition suivante
			}
			if (reponse.equals(verification)) {	break;} 
			else { System.out.println("Erreur de correction");}
		}
		return reponse;
	}

	@Override
	public String play(String reponse, int solutionLength) {
		return this.inputErrorCheck(solutionLength);
	}
	
	
	public String inputErrorCheck(int solutionLength) {
		String str = "";
		while (true) {
			try {
				str = scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Erreur de saisie, recommencez :");
			}
			if (Regex.isValidCombination(str, solutionLength)) {
				break;
			} else {
				System.out.println("Veillez saisir un entier à " + solutionLength + " chiffre compris entre 1 et 9");
			}
		}
		return str;
	}
}
