package com.gg.main.players;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.gg.main.ConfigurationClass;

public class AIPlayerMastermind extends Player {

	private Random rand = new Random();
	private Map<Character, Integer> solutionMap;

	private String lastPlayedPropostion;
	private boolean initStep;
	private ArrayList<Integer> memPlayedFigures;
	private ArrayList<Integer> memPlayableFigures;
	private Map<Integer, Integer> memSolutionFigures;
	private int[] memSolutionTab;

	private int previousPresent;
	private int previousRightPosition;

	private int newSwappedFigure;
	private int previousSwappedFigure;
	private int swappedPosition;

	private int present = 0;
	private int rightPosition = 0;

	private NextPlayEnum nextPlayEnum;
	private boolean hold;

	public AIPlayerMastermind(ConfigurationClass config) {
		super(config);
		this.lastPlayedPropostion = "";
		this.initStep = true;
		this.hold = false;
		this.nextPlayEnum = NextPlayEnum.UNDEFINED;
		this.memSolutionTab = new int[config.getSolutionLength()];
	}

	@Override
	public String selectSolution() {
		int min = 0;
		int max = config.getNbColors() - 1;
		int nbRandom;
		String solution = "";

		for (int i = 0; i < config.getSolutionLength(); i++) {
			nbRandom = rand.nextInt(max - min + 1) + min;
			solution += String.valueOf(nbRandom);
		}
		System.out.println("L'AI a selectionné une combinaison de " + config.getSolutionLength() + " chiffres.");
		System.out.println("Cette combinaison contient des chiffres entre 0 et " + (config.getNbColors() - 1) + ".");
		return solution;
	}

	@Override
	public String giveAnswer(String proposition, String solution) {

		String str = "";
		int areRightPosition = 0;
		int arePresent = 0;

		Map<Character, Integer> propositionMap;

		for (int i = 0; i < config.getSolutionLength(); i++) {

			for (int j = 0; j < config.getSolutionLength(); j++) {

				if (proposition.charAt(i) == solution.charAt(j)) {
					if (i == j) {
						areRightPosition++;
					}
				}
			}
		}

		/*
		 * Creation d'une map du qui associe chiffre et occurence du chiffre [
		 * 0,1,1,1,2,0] devient l'ensemble (clé,valeur) (0,2)(1,3)(2,1)
		 * 
		 */
		solutionMap = this.createMap(solution);
		propositionMap = this.createMap(proposition);

		/*
		 * On test chaque entrée de la map solution Si on retrouve un chiffre en commun
		 * on va chercher les valeurs associés on garde la plus petite des deux valeur
		 * (qui correspond au nombre de correspondances solution / proposition)
		 * 
		 */
		for (Map.Entry<Character, Integer> entry : solutionMap.entrySet()) {
			if (propositionMap.containsKey(entry.getKey())) {
				arePresent += getTheLesser(propositionMap.get(entry.getKey()), entry.getValue());
			}
		}

		/*
		 * La boucle si dessus nous a renvoyé tout le nombre de chiffre present dans les
		 * deux solutions, il faut les distingué des valeurs bien placé, déterminée plus
		 * haut [0,0,0,2] [4,4,0,0] (0,3) (0,2)
		 * 
		 * nombre presents... ... le plus petit des deux valeur 2
		 * 
		 */
		arePresent -= areRightPosition;

		str = "Nombre bien placé : " + areRightPosition + "\tNombre present : " + arePresent;
		System.out.println(str);
		return str;
	}

	@Override
	public String play(String correction) {
		String proposition = "";


		if (correction != "") {
			present = correction.charAt(1) - 48;
			rightPosition = correction.charAt(3) - 48;
		}

		memPlayableFigures = cleanMemory();
		memPlayableFigures = memoryOptimizer();
		this.memoryChecker();

		proposition = this.playMethod();

		// sauvegarde de la correction
		present = previousPresent;
		rightPosition = previousRightPosition;
		// rentre la proposition en mémoire
		this.lastPlayedPropostion = proposition;
		return proposition;
	}

	// methode de giveAnswer
	public Map<Character, Integer> createMap(String str) {
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < config.getSolutionLength(); i++) {
			if (!map.containsKey(str.charAt(i))) {
				map.put(str.charAt(i), 1);
			} else {
				map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
			}
		}
		return map;
	}

	// methode de giveAnswer
	public int getTheLesser(int a, int b) {
		return (a > b) ? b : a;
	}

	private ArrayList<Integer> cleanMemory() {
		if (memPlayableFigures.isEmpty()) {
			for (int i = 0; i <= config.getNbColors(); i++) {
				memPlayableFigures.add(i);
			}
			nextPlayEnum = NextPlayEnum.NEW_COMBINIATION;
		}
		if ((present + rightPosition) == 0) {
			int j;
			for (int i = 0; i < config.getSolutionLength(); i++) {
				j = lastPlayedPropostion.charAt(i) - 48;
				if (memPlayableFigures.contains(j))
					memPlayableFigures.remove(j);
			}
			nextPlayEnum = NextPlayEnum.NEW_COMBINIATION;
		}
		return memPlayableFigures;
	}

	private ArrayList<Integer> memoryOptimizer() {
		/*
		 * Prend la liste des coups jouer et la nettoi a la lumiere du dernier joué
		 */
		return memPlayableFigures;
	}

	private void memoryChecker() {
		if (hold == false) {
			if (present > previousPresent) {
				this.addToSolution(newSwappedFigure, swappedPosition);
				if (rightPosition < previousRightPosition)
					this.addToPresent(previousSwappedFigure, swappedPosition);
				else if (rightPosition == previousRightPosition)
					this.removeFromPlayable(previousSwappedFigure);
				else
					System.out.println("erreur d'algo ligne 221");
				nextPlayEnum = NextPlayEnum.MID_GAME_COMBINATION;
			} else if (present < previousPresent) {
				this.removeFromPlayable(previousSwappedFigure);
				this.removeFromPlayable(newSwappedFigure);
				nextPlayEnum = NextPlayEnum.MID_GAME_COMBINATION;
			} else {
				if (present > previousPresent) {
					this.addToPresent(newSwappedFigure, swappedPosition);
					this.removeFromPlayable(previousSwappedFigure);
					nextPlayEnum = NextPlayEnum.MID_GAME_COMBINATION;
				} else if (present < previousPresent) {
					this.addToPresent(previousSwappedFigure, swappedPosition);
					this.removeFromPlayable(previousSwappedFigure);
					nextPlayEnum = NextPlayEnum.MID_GAME_COMBINATION;
				} else {
					nextPlayEnum = NextPlayEnum.TEST_WITH_TWO_FIGURES_SAME_TIME;
					this.hold = true;
				}
			}
		} else {
			if (((present + rightPosition) == (previousPresent + previousRightPosition))
					&& ((present + rightPosition) < (previousPresent + previousRightPosition))) {
				this.removeFromPlayable(newSwappedFigure);
				this.removeFromPlayable(previousSwappedFigure);
			} else {
				this.addToSolution(newSwappedFigure, swappedPosition);
				this.addToSolution(previousSwappedFigure, swappedPosition);
				hold = false;
				nextPlayEnum = NextPlayEnum.MID_GAME_COMBINATION;
			}
		}
	}

	private void removeFromPlayable(int figure) {
		// TODO Auto-generated method stub
	}

	private void removeFromMap(int figure) {
		// TODO
	}

	private void addToSolution(int figure, int swappedPosition) {
		this.memSolutionTab[swappedPosition] = figure;
	}

	private void addToPresent(int figure, int swappedPosition) {
		// TODO
	}

	private String playMethod() {
		String proposition = "";
		switch (nextPlayEnum) {
		case UNDEFINED:
			System.out.println("erreur algo ligne 248");
			break;
		case NEW_COMBINIATION:
			// Jouer X chiffre random qui appartienne a la table memPlayableFigure
			break;
		case MID_GAME_COMBINATION:
			//
			break;
		case TEST_WITH_TWO_FIGURES_SAME_TIME:
			// proposition = XXAN
			/*
			 */
			if (swappedPosition == 0)
				for (int i = 0; i < config.getSolutionLength(); i++) {
					if (i == 1) {
						proposition += previousSwappedFigure;
					}
					else proposition += lastPlayedPropostion.charAt(i);
				}
			else {
				for (int i = 0; i < config.getSolutionLength(); i++) {
					if (i == (swappedPosition - 1)) {
						proposition += previousSwappedFigure;
					}
					else proposition += lastPlayedPropostion.charAt(i);
				}
			}
			return proposition;
		case FINAL_COMBINATION:
			// Jouer les X chiffre de la table memSolutionTab
			break;
		}
	}
}
