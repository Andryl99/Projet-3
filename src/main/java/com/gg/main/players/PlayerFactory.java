package com.gg.main.players;

import com.gg.main.ConfigurationClass;

public class PlayerFactory {

	private ConfigurationClass config;
	
	public Player getPlayer(int gameChoice, int modChoice)	{
		return createPlayer(gameChoice, modChoice);
	}

	private Player createPlayer(int gameChoice, int modChoice) {
		return new Player();
	}
	
	public APairOfPlayer getAPairOfPlayer(int gameChoice, int modChoice) {
		return createAPairOfPlayer(gameChoice, modChoice);
	}

	private APairOfPlayer createAPairOfPlayer(int gameChoice, int modChoice) {
		config = new ConfigurationClass();
		switch(modChoice) {
		case 1 :
			return new APairOfPlayer(new HumanPlayer(), new AIPlayer(config.getSolutionLength()));
		case 2 :
			return new APairOfPlayer(new AIPlayer(config.getSolutionLength()), new HumanPlayer());
		case 3 :
			return new APairOfPlayer(new HumanPlayer(), new AIPlayer(config.getSolutionLength()));
		}
		return null;
	}
	
}
