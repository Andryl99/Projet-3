package com.gg.main.players;

import java.util.Random;

public class AIPlayer extends Player {

	private int[] currentTab;
	private int[] lowerBoundTab;
	private int[] upperBoundTab;
	Random rand = new Random();

	public AIPlayer(int solutionLength) {
		this.currentTab = new int[solutionLength];
		this.lowerBoundTab = new int[solutionLength];
		this.upperBoundTab = new int[solutionLength];

		for (int i = 0; i < solutionLength; i++) {
			lowerBoundTab[i] = 0;
			upperBoundTab[i] = 10;
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
		return null;
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
				lowerBoundTab[i] = currentTab[i];
				if ((upperBoundTab[i] - lowerBoundTab[i]) > 1) // si l'interval existe
				{
					currentTab[i] = (upperBoundTab[i] + lowerBoundTab[i]) / 2;
				} else {
					currentTab[i]++;
					//remplacer par une exeption
				}

			} else if (correction.charAt(i) == '-') {
				upperBoundTab[i] = currentTab[i];
				currentTab[i] = (lowerBoundTab[i] + upperBoundTab[i]) / 2;
			}
			proposition += currentTab[i];

		}
		return proposition;
	}
}
