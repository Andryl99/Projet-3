package com.gg.main;

public class GameFactory {
	
	private ConfigurationClass config;
	
	public Game getGame(int gameChoice) {
		return createGame(gameChoice);
	}

	private Game createGame(int gameChoice) {
		config = new ConfigurationClass();
		switch (gameChoice) {
		case 1 :
			return new MoreLessGame(config.getNbCoups(), config.getNbCases(), player1, player2);
			break;
		case 2 :
			return new Mastermind(config.getNbCoups(), config.getNbCases(), config.getNbCouleurs(), player1, player2);
			break;
		}
	}
}
