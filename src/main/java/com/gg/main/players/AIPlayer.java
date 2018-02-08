package com.gg.main.players;

import java.util.Random;

public class AIPlayer extends Player {

	private int[] currentTab;
	private int[] memMin;
	private int[] memMax;
	Random rand = new Random();

	public AIPlayer(int solutionLength) {
		this.currentTab = new int[solutionLength];
		this.memMin = new int[solutionLength];
		this.memMax = new int[solutionLength];

		for (int i = 0; i < solutionLength; i++) {
			memMin[i] = 0;
			memMax[i] = 10;
		}
	}

	@Override
	public String selectSolution(int solutionLength) {

		int min = 1;
		int max = 9;
		int nbRandom;
		String solution = "";

		for (int i = 0; i < solutionLength; i++) {
			nbRandom = rand.nextInt(max - min + 1) + min;
			solution += String.valueOf(nbRandom);
		}
		System.out.println("l'AI a selectionné une combinaison de " + solutionLength + " chiffres.");
		return solution;
	}

	@Override
	public String giveAnswer(String proposition, String solution, int solutionLength) {

		int digitProp;
		int digitSol;

		String reponse = "";

		// On compare les combinaison et determine le r�sultat
		// Ici les param�tre de l'application change la borne sup;
		for (int i = 0; i < solutionLength; i++) {

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
	public String play(String correction, int solutionLength) {

		String proposition = "";
		if (correction.equals("")) {
			int min = 1;
			int max = 9;

			for (int i = 0; i < solutionLength; i++) {
				currentTab[i] = rand.nextInt(max - min + 1) + min;
				proposition += currentTab[i];
			}

			return proposition;
		}

		for (int i = 0; i < solutionLength; i++) {

			if (correction.charAt(i) == '+') {
				memMin[i] = currentTab[i];
				// if (((memMax[i] + memLastPlayed[i]) / 2) != memLastPlayed[i])
				if ((memMax[i] - currentTab[i]) > 1 ) // si l'interval existe
				{
					currentTab[i] = (memMax[i] + currentTab[i]) / 2;
				} else {
					currentTab[i]++ ;
				}

			} else if (correction.charAt(i) == '-') {
				memMax[i] = currentTab[i];
				currentTab[i] = (memMin[i] + currentTab[i]) / 2;
				}
			proposition += currentTab[i];
			
		}
		return proposition;
	}
}

