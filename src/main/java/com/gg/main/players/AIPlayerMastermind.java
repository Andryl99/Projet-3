package com.gg.main.players;

import java.util.Random;

import com.gg.main.ConfigurationClass;

public class AIPlayerMastermind extends Player {

	Random rand = new Random();
	ConfigurationClass config;
	
	public AIPlayerMastermind(ConfigurationClass config) {
		this.config = config;
	}
	
	@Override
	public String selectSolution(int solutionLength) {
		int min = 0;
		int max = config.getNbColors() - 1;
		int nbRandom;
		String solution = "";

		for (int i = 0; i < solutionLength; i++) {
			nbRandom = rand.nextInt(max - min + 1) + min;
			solution += String.valueOf(nbRandom);
		}
		System.out.println("l'AI a selectionné une combinaison de " + solutionLength + " chiffres.");
		System.out.println("Cette combinaison contient des chiffres entre 0 et " + (config.getNbColors() - 1) + ".");
		return solution;
	}

	@Override
	public String giveAnswer(String proposition, String solution, int solutionLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String play(String reponse, int solutionLength) {
		// TODO Auto-generated method stub
		return null;
	}
}
