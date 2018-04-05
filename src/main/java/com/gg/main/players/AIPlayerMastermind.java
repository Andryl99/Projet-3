package com.gg.main.players;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gg.main.ConfigurationClass;
import com.gg.main.Launcher;

public class AIPlayerMastermind extends Player {

	static final Logger logger = LogManager.getLogger();
	private Random rand = new Random();
	private String guess;
	private ArrayList<String> candidateList;
	// Variables dédiées à makeAGuess()
	private ArrayList<String> fullCodeList;
	private Map<String, Integer> minimumEliminationMap;
	private ArrayList<String> possibleGuessesList;
	private ArrayList<String> pegsList;
	private boolean useMinimaxMethod;

	public AIPlayerMastermind(ConfigurationClass config) {
		super(config);
		ListBuilder list = new ListBuilder(config);
		candidateList = list.getList();
		// Spécifique a WithMinMAx
		fullCodeList = new ArrayList<String>(candidateList);
		createPegsList();
		minimumEliminationMap = new HashMap<String, Integer>();
		possibleGuessesList = new ArrayList<String>();
		// On utilise la méthode Mini Maxi si le nombre de couleur/digit n'est pas trop
		// élevé;
		useMinimaxMethod = (Math.pow(config.getNbColors(), config.getSolutionLength()) < 5000);
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
		logger.info("L'AI a selectionné une combinaison de " + config.getSolutionLength() + " chiffres.\n");
		logger.info("Cette combinaison contient des chiffres entre 0 et " + (config.getNbColors() - 1) + ".\n");
		return solution;
	}

	@Override
	public String giveAnswer(String proposition, String solution) {
		String correction = AIPlayerMastermind.Corrector(proposition, solution);
		String correct = correction.substring(0, 1);
		String wellPlaced = correction.substring(2, 3);
		logger.info("Correcte(s)   : " + correct + "\n");
		logger.info("Bien placé(s) : " + wellPlaced + "\n");
		logger.debug("Solution : "  + solution + "\n");
		return correction;
	}

	@Override
	public String play(String correction) {
		if (correction.equals(""))
			// Premier coup a jouer
			return this.makeAStarterGuess();
		this.memoryCleaner(correction);
		this.makeAGuess();
		return guess;

	}

	private static Map<Character, Integer> createMap(String str) {
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			if (!map.containsKey(str.charAt(i))) {
				map.put(str.charAt(i), 1);
			} else {
				map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
			}
		}
		return map;
	}

	private static int getTheLesser(int a, int b) {
		return (a > b) ? b : a;
	}

	private int getTheLesser(ArrayList<Integer> list) {
		// minimum très grand
		int minimum = 10000;
		for (Integer amount : list) {
			if (amount < minimum)
				minimum = amount;
		}
		return minimum;
	}

	private int getTheBigger(ArrayList<Integer> list) {
		int maximum = -1;
		for (Integer amount : list) {
			if (amount > maximum)
				maximum = amount;
		}
		return maximum;
	}

	// methodes de play
	private void memoryCleaner(String correction) {

		// int correct = Integer.parseInt(correction.substring(0, 1));
		// int wellPlaced = Integer.parseInt(correction.substring(2, 3));

		// Cette fonction retire de la Map candidateList l'ensemble des combinaisons qui
		// ne coincide pas avec la correction si le dernier guess était solution
		for (Iterator<String> iter = candidateList.listIterator(); iter.hasNext();) {
			String candidateCode = iter.next();
			if (!matchWithGuess(guess, candidateCode, correction)) {
				iter.remove();
			}
		}
		// Affichage de la liste
		// System.out.println("**** candidateList ****");
		// for (String string : candidateList) {
		// System.out.println(string);
		// }
	}

	private boolean matchWithGuess(String guess, String code, String correction) {
		return AIPlayerMastermind.Corrector(guess, code).equals(correction);
	}

	private String makeAStarterGuess() {
		// On joue toujours 1122, 11222 ou 112222
		guess = "11";
		for (int i = 2; i < config.getSolutionLength(); i++)
			guess += "2";
		return guess;
	}

	private void makeAGuess() {
		if (useMinimaxMethod) {
			// Méthode Minimax
			resetMapsAndStuff();
			setMinimumEliminationMap();
			setPossibleGuessesList();
			pickTheBestGuess();
		} else {
			// Méthode Random
			Random random = new Random();
			guess = candidateList.get(random.nextInt(candidateList.size()));
			// Une autre méthode consiste à prendre simplement le premier code de la liste
			// guess = candidateList.get(0);
		}
	}

	private void createPegsList() {

		int index = 0;
		pegsList = new ArrayList<String>();
		for (int i = 0; i <= config.getSolutionLength(); i++)
			for (int j = 0; j <= config.getSolutionLength(); j++)
				if ((i + j) <= config.getSolutionLength()) {
					pegsList.add(index, i + " " + j);
					index++;
				}
		pegsList.remove("0 " + config.getSolutionLength());

		// affiche pegs liste
		// for (String string : pegsList) {
		// System.out.println(string);
		// }
	}

	private void resetMapsAndStuff() {
		minimumEliminationMap.clear();
		possibleGuessesList.clear();
	}

	private void setMinimumEliminationMap() {

		int amountOfElimitatedCodes = 0;
		ArrayList<Integer> listOfAmounts = new ArrayList<Integer>();
		// suppression du dernier guess
		fullCodeList.remove(guess);
		for (String code : fullCodeList) {
			for (String pegs : pegsList) {
				amountOfElimitatedCodes = 0;
				for (String scode : candidateList) {
					if (!matchWithGuess(code, scode, pegs)) {
						amountOfElimitatedCodes++;
					}
				}
				listOfAmounts.add(amountOfElimitatedCodes);
			}
			minimumEliminationMap.put(code, getTheLesser(listOfAmounts));
			listOfAmounts.clear();
		}
	}

	private void setPossibleGuessesList() {
		int minimax = 0;
		ArrayList<Integer> minimumEliminationList = new ArrayList<Integer>();

		for (Map.Entry<String, Integer> entry : minimumEliminationMap.entrySet()) {
			// System.out.println(entry.getKey() + "/" + entry.getValue());
			minimumEliminationList.add(entry.getValue());
		}
		// minimumEliminationSet = new
		// ArrayList<Integer>(minimumEliminationMap.values());

		minimax = getTheBigger(minimumEliminationList);

		for (Map.Entry<String, Integer> entry : minimumEliminationMap.entrySet()) {
			if (entry.getValue() == minimax)
				possibleGuessesList.add(entry.getKey());
		}
	}

	private void pickTheBestGuess() {
		ArrayList<String> bestGuessesList = new ArrayList<String>();
		for (String code : possibleGuessesList) {
			if (candidateList.contains(code)) {
				bestGuessesList.add(code);
				// System.out.println("a solution from the candidate list will be played");
			}
		}
		if (bestGuessesList.isEmpty()) {
			bestGuessesList = new ArrayList<String>(possibleGuessesList);
			// System.out.println("a solution from full code list will be played");
		}

		// System.out.println("**** possibleList ****");
		// for (String string : possibleGuessesList) {
		// System.out.println(string);
		// }
		// System.out.println("**** minimaxedList ****");
		// for (String string : bestGuessesList) {
		// System.out.println(string);
		// }
		
		guess = bestGuessesList.get(0);
	}

	public static String Corrector(String guess, String code) {

		String str = "";
		int wellPlaced = 0;
		int correct = 0;

		Map<Character, Integer> propositionMap;
		Map<Character, Integer> solutionMap;

		for (int i = 0; i < code.length(); i++) {

			for (int j = 0; j < code.length(); j++) {

				if (guess.charAt(i) == code.charAt(j)) {
					if (i == j) {
						wellPlaced++;
					}
				}
			}
		}

		/*
		 * Creation d'une map du qui associe chiffre et occurence du chiffre [
		 * 0,1,1,1,2,0] devient l'ensemble (clé,valeur) (0,2)(1,3)(2,1)
		 * 
		 */
		solutionMap = AIPlayerMastermind.createMap(code);
		propositionMap = AIPlayerMastermind.createMap(guess);

		/*
		 * On test chaque entrée de la map solution Si on retrouve un chiffre en commun
		 * on va chercher les valeurs associés on garde la plus petite des deux valeur
		 * (qui correspond au nombre de correspondances solution / proposition)
		 * 
		 */
		for (Map.Entry<Character, Integer> entry : solutionMap.entrySet()) {
			if (propositionMap.containsKey(entry.getKey())) {
				correct += getTheLesser(propositionMap.get(entry.getKey()), entry.getValue());
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
		correct -= wellPlaced;

		str = correct + " " + wellPlaced;
		return str;
	}
	
	public String toString() {
		return "joueur AI";
	}

}