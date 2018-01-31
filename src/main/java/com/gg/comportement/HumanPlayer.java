package com.gg.comportement;

import java.util.InputMismatchException;
import com.gg.main.Regex;

public class HumanPlayer extends Player {

	@Override
	public String choisitSolution(int nbCases) {
		String choixCombinaison = "";
		System.out.println("Entrez une combinaison de " + nbCases + " chiffres à trouver.");
		
		while (true) {
			try {
				choixCombinaison = scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Erreur de saisie, recommencez :");
			}
			if (Regex.estCombinaisonValide(choixCombinaison, nbCases)) {
				break;
			} else {
				System.out.println("Veillez saisir un entier à " + nbCases + " chiffre compris entre 1 et 9");
				continue;
			}
		}
		return choixCombinaison;
	}

	@Override
	public String donneReponse(String proposition, String solution, int nbCases) {
		String reponse;

		System.out.println("Corrigez la proposition de l'attaquant : " + proposition + "\t reponse : " + solution);

		while (true) {
			reponse = "";
			try {
				reponse = scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Erreur de saisie, recommencez");
			}
			if (Regex.estCorrectionValide(reponse, nbCases))
				break;
			else {
				System.out.println("Saisie incorrecte, recommencez");
				continue;
			}
		}
		return reponse;
	}

	@Override
	public String joueUnCoup(String reponse, String derniereProposition, int nbCases) {
		String choixCombinaison = "";
		
		while (true) {
			try {
				choixCombinaison = scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Erreur de saisie, recommencez :");
			}
			if (Regex.estCombinaisonValide(choixCombinaison, nbCases)) {
				break;
			} else {
				System.out.println("Veillez saisir un entier à " + nbCases + " chiffre compris entre 1 et 9");
				continue;
			}
		}
		return choixCombinaison;
	}

}
