package com.gg.joueurs;

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
			}
		}
		return choixCombinaison;
	}

	@Override
	public String donneReponse(String proposition, String solution, int nbCases) {
		String reponse;
		String verification = "";
		int digitProp;
		int digitSol;



		// On compare les combinaison et determine le r�sultat
		// Ici les param�tre de l'application change la borne sup;
		for (int i = 0; i < nbCases; i++) {

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
			if	(!Regex.estCorrectionValide(reponse, nbCases)){
				System.out.println("Saisie incorrecte, recommencez");
				continue; // Si la saisie ne match pas l'expression reguliere, on ne teste meme pas la condition suivante
			}
			if (reponse.equals(verification)) {	break;} 
			else { System.out.println("Erreur de correction");}

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
			}
		}
		return choixCombinaison;
	}
}
