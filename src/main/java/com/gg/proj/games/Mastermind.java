package com.gg.proj.games;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gg.proj.players.HumanPlayerMastermind;
import com.gg.proj.players.Player;

public class Mastermind extends Game {

	static final Logger logger = LogManager.getLogger();
	
	public Mastermind(int nbTurns, int solutionLength, int nbColors, Player attacker, Player defensor) {
		super(nbTurns, solutionLength, attacker, defensor);
		this.solution = "";
		this.answer = "";
		this.proposition = "";
	}

	@Override
	public void defensorSelectSolution() {
		solution = defensor.selectSolution();
	}

	@Override
	public void attackerPlay() {
		this.proposition = attacker.play(answer);
	}

	@Override
	public void defensorAnswer() {
		if (defensor instanceof HumanPlayerMastermind) {
			String verification = Mastermind.Corrector(proposition, solution);
			do {
				this.answer = defensor.giveAnswer(proposition, solution);
				if (!verification.equals(answer)) {
					logger.warn("Erreur sur la correction, recommencez.\n");
				}
			} while (!verification.equals(answer));
		} else {
			this.answer = defensor.giveAnswer(proposition, solution);
		}
	}
	
	public static String Corrector(String guess, String code) {

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
		 * Creation d'une map du qui associe chiffre et occurence du chiffre :
		 * [0,1,1,1,2,0] devient l'ensemble (clé,valeur) : (0,2)(1,3)(2,1)
		 */
		solutionMap = Mastermind.createMap(code);
		propositionMap = Mastermind.createMap(guess);

		/*
		 * On test chaque entrée de la map solution, si on retrouve un chiffre en commun
		 * on va chercher les valeurs associées on garde la plus petite des deux valeurs
		 * (qui correspond au nombre de correspondances solution / proposition)
		 */
		for (Map.Entry<Character, Integer> entry : solutionMap.entrySet()) {
			if (propositionMap.containsKey(entry.getKey())) {
				correct += getTheLesser(propositionMap.get(entry.getKey()), entry.getValue());
			}
		}

		
		/*
		 * La boucle ci-dessus nous a renvoyé combien de chiffres sont presents dans les
		 * deux solutions : [0,0,0,2] [4,4,0,0] (0,3) (0,2)
		 * 
		 * nombre "presents" => la plus petite des deux valeur 2
		 * 
		 * Il faut ensuite les distingués des valeurs "bien placé", déterminées plus
		 * haut
		 */
		correct -= wellPlaced;

		return correct + " " + wellPlaced;
	}
	
	private static int getTheLesser(int a, int b) {
		return (a > b) ? b : a;
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

}
