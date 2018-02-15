package com.gg.main.players;

import com.gg.main.ConfigurationClass;

public class PlayerFactory {

	private ConfigurationClass config;

	public PlayerFactory(ConfigurationClass config) {
		this.config = config;
	}

	// Voir si j'implemente ça...
	// public Player getPlayer(int gameChoice, int modChoice) {
	// return createPlayer(gameChoice, modChoice);
	// }
	//
	// private Player createPlayer(int gameChoice, int modChoice) {
	// return new Player();
	// }

	public APairOfPlayer getAPairOfPlayer(int gameChoice, int modChoice) {
		return createAPairOfPlayer(gameChoice, modChoice);
	}

	private APairOfPlayer createAPairOfPlayer(int gameChoice, int modChoice) {
		if (gameChoice == 1) {
			switch (modChoice) {
			case 1:
				return new APairOfPlayer(new HumanPlayerMoreLess(config), new AIPlayerMoreLess(config));
			case 2:
				return new APairOfPlayer(new AIPlayerMoreLess(config), new HumanPlayerMoreLess(config));
			case 3:
				return new APairOfPlayer(new HumanPlayerMoreLess(config), new AIPlayerMoreLess(config));
			default:
				throw new NullPointerException();
			}
		}
		else {
			switch (modChoice) {
			case 1 :
				return new APairOfPlayer(new HumanPlayerMastermind(config), new AIPlayerMastermind(config));
			case 2 :
				return new APairOfPlayer(new AIPlayerMastermind(config), new HumanPlayerMastermind(config));
			case 3 :
				return new APairOfPlayer(new HumanPlayerMastermind(config), new AIPlayerMastermind(config));
			default :
				throw new NullPointerException();
			}
		}
	}
}
