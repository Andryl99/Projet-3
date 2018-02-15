package com.gg.main.players;

import java.util.Random;

import com.gg.main.ConfigurationClass;

public class AIPlayerMoreLess extends Player {

	private int[] currentTab;
	private int[] lowerBoundTab;
	private int[] upperBoundTab;
	Random rand = new Random();

	public AIPlayerMoreLess(ConfigurationClass config) {
		super(config);
		this.currentTab = new int[config.getSolutionLength()];
		this.lowerBoundTab = new int[config.getSolutionLength()];
		this.upperBoundTab = new int[config.getSolutionLength()];

		for (int i = 0; i < config.getSolutionLength(); i++) {
			lowerBoundTab[i] = -1;
			upperBoundTab[i] = 10;
		}
	}
	
	@Override
	public String selectSolution() {

		int min = 0;
		int max = 9;
		int nbRandom;
		String solution = "";

		for (int i = 0; i < config.getSolutionLength(); i++) {
			nbRandom = rand.nextInt(max - min + 1) + min;
			solution += String.valueOf(nbRandom);
		}
		System.out.println("l'AI a selectionné une combinaison de " + config.getSolutionLength() + " chiffres.");
		return solution;
	}

	@Override
	public String giveAnswer(String proposition, String solution) {
		return null;
	}

	@Override
	public String play(String correction) {

		String proposition = "";
		if (correction.equals("")) {
			int min = 0;
			int max = 9;

			for (int i = 0; i < config.getSolutionLength(); i++) {
				currentTab[i] = rand.nextInt(max - min + 1) + min;
				proposition += currentTab[i];
			}
			return proposition;
		}

		for (int i = 0; i < config.getSolutionLength(); i++) {

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
