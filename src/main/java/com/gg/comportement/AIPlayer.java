package com.gg.comportement;

import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player {

	private int[] memLastPlayed;
	private int[] memMin;
	private int[] memMax;
	private int[] propositionTab;
	Random rand = new Random();
	
	public AIPlayer(int nbCases) {
		this.memLastPlayed = new int[nbCases];
		this.memMin = new int[nbCases];
		this.memMax = new int[nbCases];
		this.propositionTab = new int[nbCases];

		for (int i = 0; i < nbCases; i++) {
			memMin[i] = 1;
			memMax[i] = 9;
		}
	}

	@Override
	public String choisitSolution(int nbCases) {
		
		int min = 1;
		int max = 9;
		int nbRandom;
		String solution = "";

		for (int i = 0; i < nbCases; i++) {
			nbRandom = rand.nextInt(max - min + 1) + min;
			solution += String.valueOf(nbRandom);
		}
		System.out.println("l'AI a selectionné une combinaison de " + nbCases + " chiffres.");
		return solution;
	}

	@Override
	public String donneReponse(String proposition, String solution, int nbCases) {

		int digitProp;
		int digitSol;

		String reponse = "";

		// On compare les combinaison et determine le r�sultat
		// Ici les param�tre de l'application change la borne sup;
		for (int i = 0; i < nbCases; i++) {

			digitProp = Integer.parseInt(String.valueOf(proposition.charAt(i)));
			digitSol = Integer.parseInt(String.valueOf(solution.charAt(i)));

			if (digitProp == digitSol)
				reponse += "=";
			else if (digitProp < digitSol)
				reponse += "+";
			else if (digitProp > digitSol)
				reponse += "-";
		}

		System.out.println("Proposition : " + proposition + "\tSolution : " + solution + "\t\t==> " + reponse);

		return reponse;
	}

	@Override
	// public String joueUnCoup(String reponse, String derniereProposition, int
	// nbCases) {
	//
	// String proposition = "";
	// if (reponse == "") {
	// for (int i = 0; i < nbCases; i++)
	// proposition += "5";
	// return proposition;
	// }
	// //
	// // J'ai r�cup�r� la derniere combinaison ce qui donne une IA simple qui
	// ajoute
	// // ou soustraie 1 au dernier coup pour s'approcher de la solution
	// for (int i = 0; i < nbCases; i++) {
	//
	// if (reponse.charAt(i) == '+') {
	// proposition += (char) (derniereProposition.charAt(i) + 1);
	// } else if (reponse.charAt(i) == '-') {
	// proposition += (char) (derniereProposition.charAt(i) - 1);
	// } else if (reponse.charAt(i) == '=') {
	// proposition += derniereProposition.charAt(i);
	// }
	// }
	// return proposition;
	// }
	public String joueUnCoup(String correction, String derniereProposition, int nbCases) {

		String proposition = "";
		if (correction == "") {
			int min = 1;
			int max = 9;
			
			for (int i = 0; i < nbCases; i++) {
				memLastPlayed[i] = rand.nextInt(max - min + 1) + min;
				proposition += memLastPlayed[i];
			}

			return proposition;
		}

		for (int i = 0; i < nbCases; i++) {

			if (correction.charAt(i) == '+') {

				memMin[i] = memLastPlayed[i];

				//if (((memMax[i] + memLastPlayed[i]) / 2) != memLastPlayed[i]) // si l'interval existe
				if (((memMax[i] - memLastPlayed[i] !=0)) && ((memMax[i] - memLastPlayed[i]) != 1 )) // si l'interval existe
				{

					propositionTab[i] = (memMax[i] + memLastPlayed[i]) / 2;
					memLastPlayed[i] = propositionTab[i];
				} else {
					propositionTab[i] = memLastPlayed[i] + 1;
					memLastPlayed[i] = propositionTab[i];
				}

			} else if (correction.charAt(i) == '-') {

				memMax[i] = memLastPlayed[i];
				propositionTab[i] = (memMin[i] + memLastPlayed[i]) / 2;
				memLastPlayed[i] = propositionTab[i];

			} else if (correction.charAt(i) == '=') {
				propositionTab[i] = memLastPlayed[i];
			}

			proposition += propositionTab[i];
		}
		return proposition;
	}
}
