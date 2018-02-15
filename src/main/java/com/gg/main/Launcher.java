package com.gg.main;

import java.util.List;

import com.gg.main.games.Game;
import com.gg.main.games.GameFactory;

public class Launcher {
	
	private ConfigurationClass config;
	private GameFactory gameFactory;
	private Game lastGame;
	
	public Launcher(boolean devMod) {
		// constructeur (Nb de tours, Nb de chiffre dans la solution, nombre de couleurs dans le mastermind)
		this.config = new ConfigurationClass(8, 4, 7, devMod);
		this.gameFactory = new GameFactory(config);
	}
	
	public void runGame(int gameChoice, int modChoice) {
		
		boolean isLastTurn = false;
		// Création du jeu
		List<Game> gameList = gameFactory.getAListOfGames(gameChoice, modChoice);
		// initialisation
		lastGame = gameList.get(0);
		// Boucle du jeu
		
			for (Game game : gameList) {
				game.reset();
				game.defensorSelectSolution();
			}

			// On enchaine les tours ...
			do {
				for (Game game : gameList) {
					lastGame = game;
					isLastTurn = game.nextTurn();
					if (isLastTurn)
						break;
				}
			} while (!isLastTurn);

			// TODO ... est buggé en duel, récupérer des données sur le derniers jeu, pour determiné le nom du vainqueur
			switch (lastGame.stateOfGame()) {
			case AUCUNGAGNANT:
				break;
			case ATTAQUANTGAGNE:
				System.out.println("Attaquant gagne!");
				break;
			case DEFENSEURGAGNE:
				System.out.println("Défenseur gagne!");
				break;
			default :
				break; 
		}
	}
}
