package com.gg.main;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gg.main.games.Game;
import com.gg.main.games.GameFactory;

public class Launcher {
	
	static final Logger logger = LogManager.getLogger();
	private ConfigurationClass config;
	private GameFactory gameFactory;
	private Game lastGame;
	
	public Launcher(boolean devMod) {
		ConfigurationClassFactory configclassfacto = new ConfigurationClassFactory();
		this.config = configclassfacto.getConfigurationClass();
//		this.config = new ConfigurationClass(8, 4, 7, devMod);
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
				logger.info("Attaquant gagne!");
				break;
			case DEFENSEURGAGNE:
				logger.info("Défenseur gagne!");
				break;
			default :
				break; 
		}
	}
}
