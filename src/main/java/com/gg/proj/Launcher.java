package com.gg.proj;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import com.gg.proj.games.Game;
import com.gg.proj.games.GameFactory;

public class Launcher {

	static final Logger logger = LogManager.getLogger();
	private ConfigurationClass config;
	private GameFactory gameFactory;
	private Game lastGame;

	public Launcher(boolean devMod) {
		ConfigurationClassFactory configclassfacto = new ConfigurationClassFactory();
		this.config = configclassfacto.getConfigurationClass();
		this.gameFactory = new GameFactory(config);
		if (devMod)
			Configurator.setRootLevel(Level.DEBUG);
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

		// On enchaine les tours (nextTurn)
		do {
			for (Game game : gameList) {
				lastGame = game;
				isLastTurn = game.nextTurn();  
				if (isLastTurn)
					break;
			}
		} while (!isLastTurn);

		//
		switch (lastGame.stateOfGame()) {
		case NOWINNER:
			break;
		case ATTACKERWIN:
			logger.info("Le " + lastGame.getAttacker().toString() + " l'emporte en " + lastGame.getTurnCounter()
					+ " tour(s)!\n");
			break;
		case DEFENSORWIN:
			logger.info("Le " + lastGame.getDefensor().toString() + " l'emporte à l'issu du " + lastGame.getTurnCounter()
			+ "ème tour(s)!\n");
			break;
		default:
			break;
		}
	}
}
