package com.gg.main.players;

import com.gg.main.ConfigurationClass;

public class PlayerFactory {

	private ConfigurationClass config;

	public PlayerFactory(ConfigurationClass config) {
		this.config = config;
	}

	public APairOfPlayer getAPairOfPlayer(int gameChoice, int modChoice) {
		return createAPairOfPlayer(gameChoice, modChoice);
	}

	private APairOfPlayer createAPairOfPlayer(int gameChoice, int modChoice) throws IllegalArgumentException {
		if (gameChoice == 1) {
			switch (modChoice) {
			case 1:
				return new APairOfPlayer(new HumanPlayerMoreLess(config), new AIPlayerMoreLess(config));
			case 2:
				return new APairOfPlayer(new AIPlayerMoreLess(config), new HumanPlayerMoreLess(config));
			case 3:
				return new APairOfPlayer(new HumanPlayerMoreLess(config), new AIPlayerMoreLess(config));
			default:
				throw new IllegalArgumentException("Programming error, modChoice is "+ modChoice + " and should be 1, 2 or 3. Game received a null pointer");
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
				throw new IllegalArgumentException("Programming error, modChoice is "+ modChoice + " and should be 1, 2 or 3. Game received a null pointer");
			}
		}
	}
}
