package com.gg.main;

import java.util.List;

import com.gg.main.games.Game;
import com.gg.main.games.GameFactory;

public class Launcher {
	
	private ConfigurationClass config;
	private GameFactory gameFactory;
	private Game lastGame;
	
	public Launcher() {
		this.config = new ConfigurationClass(6, 4, 5, false);
		this.gameFactory = new GameFactory(config);
	}
	
	public void runGame(int gameChoice, int modChoice) {
		
		boolean isLastTurn = false;
		// Création du jeu
		List<Game> gameList = gameFactory.getAListOfGames(gameChoice, modChoice);
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

			// TODO ... est buggé en duel
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
