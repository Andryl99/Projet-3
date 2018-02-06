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
			return new MoreLessGame(config.getNbTurns(), config.getSolutionLength(), player1, player2);
			break;
		case 2 :
			return new Mastermind(config.getNbTurns(), config.getSolutionLength(), config.getNbColors(), player1, player2);
			break;
		default :
			return null;
			break;
		}
	}
}
