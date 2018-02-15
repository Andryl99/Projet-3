package com.gg.main.players;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.gg.main.ConfigurationClass;

public class AIPlayerMastermind extends Player {

	private Random rand = new Random();
	private Map<Character,Integer> solutionMap;

	public AIPlayerMastermind(ConfigurationClass config) {
		super(config);
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
		int arePresent = 0;
		int areInRightPosition = 0;
		

		Map<Character,Integer> propositionMap;
		
		for (int i = 0; i < config.getSolutionLength(); i++) {

			for (int j = 0; j < config.getSolutionLength(); j++) {
				
				if (proposition.charAt(i) == solution.charAt(j)) {
					if (i == j) {
						arePresent++;
					}
				}
			}
		}
		
		/*
		 * Creation d'une map du qui associe chiffre et occurence du chiffre
		 * 			[ 0,1,1,1,2,0] devient l'ensemble (clé,valeur) (0,2)(1,3)(2,1) 
		 * 
		 */
		solutionMap = this.createMap(solution);
		propositionMap = this.createMap(proposition);
		
		
		/*
		 * On test chaque entrée de la map solution
		 * Si on retrouve un chiffre en commun on va chercher les valeurs associés
		 * 		on garde la plus petite des deux valeur (qui correspond au nombre de correspondances solution / proposition)
		 *  
		 */
		for (Map.Entry<Character, Integer> entry : solutionMap.entrySet()) {
			if(propositionMap.containsKey(entry.getKey())) {
				areInRightPosition += getTheLesser(propositionMap.get(entry.getKey()), entry.getValue());
			}
		}
		
		/*
		 * La boucle si dessus nous a renvoyé tout le nombre de chiffre present dans les deux solutions,
		 * 		il faut les distingué des valeurs bien placé, déterminée plus haut
		 *  
		 */
		areInRightPosition -= arePresent;
		
		str = "Nombre bien placé : " + arePresent + "\tNombre present : " + areInRightPosition;
		System.out.println(str);
		return str;
	}

	@Override
	public String play(String reponse) {		
		return null;
	}

	// avec un foreach?
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

	public int getTheLesser(int a, int b) {
		return (a > b) ? b : a;
	}
}
