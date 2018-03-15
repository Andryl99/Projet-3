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
	
	private int previousPresent;
	private int previousRightPosition;

	public AIPlayerMastermind(ConfigurationClass config) {
		super(config);
		this.lastPlayedPropostion = "";
		this.initStep = true;
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
		int present = 0;
		int rightPosition = 0;
		String proposition = "";

		if (initStep) {
			if (memPlayableFigures.isEmpty()) {
				for (int i = 0; i <= config.getNbColors(); i++) {
					memPlayableFigures.add(i);
				}
			}

			for (int i = 0; i < config.getSolutionLength(); i++) {
				if (i < config.getNbColors()) {
					proposition += i;
					memPlayedFigures.add(i);
				}
				else {
					proposition += i - config.getNbColors();
				}
			}
			initStep = false;
			return proposition;
		}

		if (correction != "") {
			present = correction.charAt(2);
			rightPosition = correction.charAt(4);
		}

		if ((present + rightPosition) == 0) {
			for (int i = 0; i < lastPlayedPropostion.length(); i++) {
				// on retire les chiffres joués au dernier coup de la liste memPlayableFigures
				memPlayableFigures.remove(lastPlayedPropostion.charAt(i));
			}
			
			/*
			 * 
			 * Puis on joue un nouveau coup
			 * return proposition
			 */
		}

		
		if (rightPosition > previousRightPosition) {
			// le nouveau chiffre est dans la combi a la bonne place
		}
		
			
		// sauvegarde de la correction
		present = previousPresent;
		rightPosition = previousRightPosition;
		// rentre la proposition en mémoire
		this.lastPlayedPropostion = proposition;
		return proposition;

		/*
		 * ALGO
		 *
		 * 
		 * 
		 * Proposition : 0123 Nombre bien placé : 1 Nombre present : 1
		 * 
		 * info : on peut jouer sur ces chiffre on part d'un ensemble de X-1 chiffre on
		 * en change un au hazard et on regarde la réponse
		 * 
		 * Proposition : 0124 Nombre bien placé : 2 Nombre present : 0
		 * 
		 * 
		 * 3 possibiliité : Si BP et NP = 0, on vire les 4 chiffres
		 * 
		 * Si BP augmente le nouveau chiffre est dans la combinaison a la BP et Si NP
		 * change pas : l'ancien pas dans la combi Si NP diminu : l'ancien etait présent
		 * a la mauvaise place Si BP diminue la nouveau chiffre n'est pas dans dans la
		 * combi et l'ancien était dans la combi a la BP
		 * 
		 * Si BP ne change pas et Si NP augmente le nouverau chiffre est dans la
		 * combinaison a la mauvaise place et l'ancien chiffre n'était pas dans la combi
		 * Si NP diminu, le nouveau chiffre n'est pas dans la combi et l'ancien y était.
		 * Si Np egale on entre dans un fonction qui test l'ancien et le nouveau
		 * 
		 * A = ancien N = nouveau X = quelconque
		 * 
		 * On joue XXAN
		 * 
		 * Si NP diminu : les deux n'tait pas dans la combi et --X- y était Si NP
		 * augmente : les deux sont dedans Si Np ne change pas : les deux y était pas
		 * 
		 * ++++ Une mémoire qui élimine les nombres au fur et a mesure elimination de
		 * nombre si NP + BP = 0 elimination eventuelle des nombre ancien et nouveau .
		 * ++++ Une mémoire BP les chiffre a ne plus jouer sauf pour donné la solution
		 * ++++ Une mémoire précdent contient la derniere combi avec sa correction ++++
		 * Une mémoire a supprimé vide la mémoire a chaque appel
		 * 
		 * 
		 * Proposition : 3124 Nombre bien placé : 1 Nombre present : 1
		 * 
		 * Proposition : 0324 Nombre bien placé : 3 Nombre present : 0
		 * 
		 * Proposition : 0334 Nombre bien placé : 3 Nombre present : 0
		 * 
		 * Proposition : 0344 Nombre bien placé : 4 Nombre present : 0
		 * 
		 * 
		 * 
		 * 
		 */
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

	/*
	 * private String startTheMatch(String answer) { String proposition = ""; for
	 * (int i = 0 ; i < config.getSolutionLength() ; i++) { proposition += } return
	 * null; }
	 */
}
